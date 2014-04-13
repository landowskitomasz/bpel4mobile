package com.bpel4mobile.internal.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.definition.Argument;
import com.bpel4mobile.internal.definition.LogicalPeopleGroups;
import com.bpel4mobile.internal.definition.PeopleAssignment;
import com.bpel4mobile.internal.definition.PeopleAssignments;
import com.bpel4mobile.internal.definition.PotentialOwners;
import com.bpel4mobile.internal.definition.From;
import com.bpel4mobile.internal.definition.Task;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.google.common.collect.Maps;

@Service
public class DefinitionProviderImpl implements DefinitionProvider {

	@Override
	public LogicalPeopleGroups getLogicalPeopleGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, PeopleAssignments> getTaskPosibleAssignments() {
		
		PeopleAssignments peopleAssignments = new PeopleAssignments();
		PotentialOwners potentialOwners = new PotentialOwners();
		
		PeopleAssignment peopleAssignment = new PeopleAssignment();
		From from = new From();
		from.setLogicalPeopleGroup("projectManagers");
		Argument argument = new Argument();
		argument.setName("username");
		argument.setExpression("eq:request/employee");
		from.getArguments().add(argument);
		peopleAssignment.setFrom(from);
		potentialOwners.getPeopleAssigment().add(peopleAssignment);
		peopleAssignments.setPotentialOwners(potentialOwners);
		
		Map<String,PeopleAssignments> result = Maps.newHashMap();
		result.put("holidayRequestTask", peopleAssignments);
		return result; 
	}

	@Override
	public Task getTaskDefinition(String taskName) {
		Task taskDefinition = new Task();
		taskDefinition.setName(taskName);
		taskDefinition.setPriority("3");
		return taskDefinition;
	}

	
}
