package com.bpel4mobile.internal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.bean.UserGroupData;
import com.bpel4mobile.internal.definition.Argument;
import com.bpel4mobile.internal.definition.PeopleAssignment;
import com.bpel4mobile.internal.definition.PeopleAssignments;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.model.Task.State;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.bpel4mobile.internal.service.QueryFactory;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service
public class QueryFactoryImpl implements QueryFactory {
	
	@Autowired
	private DefinitionProvider definitionProvider;

	@Override
	public Query tasksToPerformByUserQuery(UserData userData) {
		
		Map<String, PeopleAssignments> posibbleAssigments = definitionProvider.getTaskPosibleAssignments();
		
		List<Criteria> findTaskCriterias = Lists.newArrayList();
		for(String task : posibbleAssigments.keySet()){
			if(!isUserInTaskPosibleAssigmentGroups(posibbleAssigments.get(task), userData.getGroups())){
				continue;
			}
			
			Criteria findTaskCriteria = Criteria.where(Task.NAME_FIELD).is(task).and(Task.STATE_FIELD).is(State.ready);
			
			Criteria posibleAssigmentCriteria = preparePossibleAssigementCriteria(posibbleAssigments.get(task), userData);
			if(posibleAssigmentCriteria != null){
				findTaskCriteria = new Criteria().andOperator(findTaskCriteria, posibleAssigmentCriteria);
			}
			
			
			findTaskCriterias.add(findTaskCriteria);
		}
		Criteria[] allCriteria = new Criteria[findTaskCriterias.size() + 1];
		findTaskCriterias.toArray(allCriteria);
		allCriteria[findTaskCriterias.size()] = Criteria.where(Task.UUID_FIELD).exists(false);
		Query query = new Query(new Criteria().orOperator(allCriteria)); 
		excludeSystemFields(query);
		return query;
	}

	private boolean isUserInTaskPosibleAssigmentGroups(
			final PeopleAssignments peopleAssignments, final List<UserGroupData> groups) {
		return Iterables.find(peopleAssignments.getPotentialOwners(), new Predicate<PeopleAssignment>() {
			@Override
			public boolean apply(PeopleAssignment assigment) {
				return findUserGroupData(groups, assigment.getFrom().getLogicalPeopleGroup()) != null;
			}
		}, null) != null;
	}

	private UserGroupData findUserGroupData(List<UserGroupData> groups,final String group){
		return Iterables.find(groups, new Predicate<UserGroupData>() {
			@Override
			public boolean apply(UserGroupData groupData) {
				return Objects.equal(groupData.getName(), group);
			}
		}, null);
	}
	
	private Criteria preparePossibleAssigementCriteria(
			PeopleAssignments peopleAssignments, UserData userData) {
		List<Criteria> groupsCriteria = Lists.newArrayList();
		for(PeopleAssignment peopleAssignment : peopleAssignments.getPotentialOwners()){
			
			UserGroupData groupData = findUserGroupData(userData.getGroups(), peopleAssignment.getFrom().getLogicalPeopleGroup());
			
			List<Criteria> groupArgumentCriteria = Lists.newArrayList();
			if(peopleAssignment.getFrom().getArguments().isEmpty()){
				
				for(Argument argument : peopleAssignment.getFrom().getArguments()){
					groupArgumentCriteria.add(evaluateExpression(argument.getExpression().trim(),
							groupData.getArguments().get(argument.getName())));
				}
			}
			if(!groupArgumentCriteria.isEmpty()){
				groupsCriteria.add(concatWithAndOperator(groupArgumentCriteria));
			}
		}
		if(!groupsCriteria.isEmpty()){
			Criteria[] criteriaArray = new Criteria[groupsCriteria.size()];
			groupsCriteria.toArray(criteriaArray);
			return new Criteria().orOperator(criteriaArray);
		}
		return null;
	}

	private Criteria concatWithAndOperator(List<Criteria> criteriaToConcat) {
		Criteria[] criteriaArray = new Criteria[criteriaToConcat.size()];
		criteriaToConcat.toArray(criteriaArray);
		return new Criteria().andOperator(criteriaArray);
	}

	private Criteria evaluateExpression(String expression, Object argument) {
		String path = expression.substring(expression.indexOf(':') + 1, expression.length());
		path = path.trim();
		path = path.replace("/", ".");
		
		Criteria criteria;
		if(expression.startsWith("lt")) {
			criteria = Criteria.where(path).gt(argument);
		} else if(expression.startsWith("gt")){
			criteria = Criteria.where(path).lt(argument);
		} else {
			criteria = Criteria.where(path).is(argument);
		}
		
		return criteria;
	}

	@Override
	public Query tasksInProgresByUserQuery(UserData userData) {
		Query query = new Query(Criteria.where(Task.ASSIGNEEE_FIELD).is(userData.getUsername()).and(Task.STATE_FIELD).is(State.climed));
		excludeSystemFields(query);
		return query;
	}
	
	private void excludeSystemFields(Query query){
		query.fields().exclude("callbackUrl").exclude("result");
	}

}
