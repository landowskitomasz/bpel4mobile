package com.bpel4mobile.internal.service;

import java.util.Map;

import com.bpel4mobile.internal.definition.LogicalPeopleGroups;
import com.bpel4mobile.internal.definition.PeopleAssignments;
import com.bpel4mobile.internal.definition.Task;

public interface DefinitionProvider {

	LogicalPeopleGroups getLogicalPeopleGroups();
	
	Map<String, PeopleAssignments> getTaskPosibleAssignments();

	Task getTaskDefinition(String taskName);

}
