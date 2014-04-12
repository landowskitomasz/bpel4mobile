package com.bpel4mobile.internal.bean;

import java.util.ArrayList;
import java.util.List;

public class UserData {

	private String username;
	
	private List<UserGroupData> groups = new ArrayList<UserGroupData>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserGroupData> getGroups() {
		return groups;
	}

	public void setGroups(List<UserGroupData> groups) {
		this.groups = groups;
	}
	
}
