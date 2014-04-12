package com.bpel4mobile.internal.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.definition.LogicalPeopleGroups;
import com.bpel4mobile.internal.definition.PeopleAssignments;
import com.bpel4mobile.internal.definition.TaskDefinition;
import com.bpel4mobile.internal.service.DefinitionProvider;

@Service
public class DefinitionProviderImpl implements DefinitionProvider {

	@Override
	public LogicalPeopleGroups getLogicalPeopleGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, PeopleAssignments> getTaskPosibleAssignments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskDefinition getTaskDefinition(String taskName) {
		TaskDefinition taskDefinition = new TaskDefinition();
		taskDefinition.setName(taskName);
		taskDefinition.setPriority("3");
		return taskDefinition;
	}

	
}
