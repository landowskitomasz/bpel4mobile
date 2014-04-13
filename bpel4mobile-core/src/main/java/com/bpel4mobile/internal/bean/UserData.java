package com.bpel4mobile.internal.bean;

import java.util.List;

import com.google.common.collect.Lists;

public class UserData {

	private String username;
	
	private List<UserGroupData> groups;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserGroupData> getGroups() {
		if(groups == null){
			groups = Lists.newArrayList();
		}
		return groups;
	}

	public void setGroups(List<UserGroupData> groups) {
		this.groups = groups;
	}
	
}
