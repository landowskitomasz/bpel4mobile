package com.bpel4mobile.internal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class QueryFactoryImpl implements QueryFactory {
	
	@Autowired
	private DefinitionProvider definitionProvider;

	@Override
	public Query tasksToPerformByUserQuery(UserData userData) {
		
		Map<String, PeopleAssignments> posibbleAssigments = definitionProvider.getTaskPosibleAssignments();
		
		posibbleAssigments = restrictTaskByUserGroup(posibbleAssigments, userData);
		
		List<Criteria> findTaskCriterias = Lists.newArrayList();
		for(String task : posibbleAssigments.keySet()){
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

	private Criteria preparePossibleAssigementCriteria(
			PeopleAssignments peopleAssignments, UserData userData) {
		List<Criteria> groupsCriteria = Lists.newArrayList();
		for(PeopleAssignment peopleAssignment : peopleAssignments.getPotentialOwners().getPeopleAssigment()){
			if(peopleAssignment.getFrom().getArguments().isEmpty()){
				continue;
			}
			final String group = peopleAssignment.getFrom().getLogicalPeopleGroup();
			UserGroupData groupData = (UserGroupData)CollectionUtils.find(userData.getGroups(), new Predicate() {
				public boolean evaluate(Object object) {
					return ((UserGroupData)object).getName().equals(group);
				}
			});
			
			List<Criteria> groupArgumentCriteria = Lists.newArrayList();
			for(Argument argument : peopleAssignment.getFrom().getArguments()){
				groupArgumentCriteria.add(evaluateExpression(argument.getExpression().trim(),
						groupData.getArguments().get(argument.getName())));
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

	private Map<String, PeopleAssignments> restrictTaskByUserGroup(
			Map<String, PeopleAssignments> posibbleAssigments, UserData userData) {
		Map<String, PeopleAssignments> result = Maps.newHashMap();
		
		for(String task : posibbleAssigments.keySet()){
			PeopleAssignments peopleAssignments = posibbleAssigments.get(task);
			
			for(final PeopleAssignment peopleAssignment : peopleAssignments.getPotentialOwners().getPeopleAssigment()){
				Object any = CollectionUtils.find(userData.getGroups(), new Predicate() {
					public boolean evaluate(Object object) {
						return ((UserGroupData)object).getName().equals(peopleAssignment.getFrom().getLogicalPeopleGroup());
					}
				});
				if(any != null){
					result.put(task, peopleAssignments);
					continue;
				}
			}
		}
		return result;
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
