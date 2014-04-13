package com.bpel4mobile.ws.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.definition.LogicalPeopleGroups;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.bpel4mobile.internal.service.UserDataProvider;

public abstract class AbstractUserDataProvider implements UserDataProvider {

	@Autowired
	private DefinitionProvider definitionProvider;
	
	public LogicalPeopleGroups getLogicalPeopleGroups(){
		return definitionProvider.getLogicalPeopleGroups();
	}
	
	public abstract UserData getUserData(String username, String password);
	 
	public abstract boolean authenticate(String username, String password);
	
}
