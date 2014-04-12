package com.bpel4mobile.internal.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Task.COLLECTION_NAME)
public class Task <T, R> {
	
	public enum State {
		ready, climed, completed, failed
	}
	
	public static final String COLLECTION_NAME = "tasks";

	public static final String NAME_FIELD = "name";

	public static final String UUID_FIELD = "_id";

	public static final String STATE_FIELD = "state";

	private String name;
	
	@Id
	private String uuid;
	
	private String callbackUrl;
	
	private State state;
	
	private Date createDate;
	
	private Date climeDate;
	
	private int priority;
	
	private T request;
	
	private R result;
	
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

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}
