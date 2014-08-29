package com.bpel4mobile.escalation.internal.model;

import java.util.Date;

public class Notification {

	public static final String COLLECTION_NAME = "notifications";
	
	private String name;
	
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
