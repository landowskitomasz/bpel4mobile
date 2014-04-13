package com.bepl4mobile.holiday.android;

import java.util.Date;


public class Task {

	public enum State {
		ready, climed, completed, failed
	}
	
	private String name;
	
	private String uuid;
	
	private State state;
	
	private Date createDate;
	
	private Date climeDate;
	
	private Date completeDate;
	
	private int priority;
	
	private HolidayRequest request;
	
	private String assignee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getClimeDate() {
		return climeDate;
	}

	public void setClimeDate(Date climeDate) {
		this.climeDate = climeDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public HolidayRequest getRequest() {
		return request;
	}

	public void setRequest(HolidayRequest request) {
		this.request = request;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	
}
