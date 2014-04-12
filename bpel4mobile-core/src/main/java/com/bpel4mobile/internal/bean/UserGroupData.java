package com.bpel4mobile.internal.bean;

import java.util.HashMap;
import java.util.Map;

public class UserGroupData {

	private String name;
	
	private Map<String, Object> arguments = new HashMap<String, Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getArguments() {
		return arguments;
	}

	public void setArguments(Map<String, Object> arguments) {
		this.arguments = arguments;
	}
	
	
}
