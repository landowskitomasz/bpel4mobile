package com.bpel4mobile.internal.bean;


public class TaskRequest<T> {

	private String callbackUrl;
	
	private T request;
	
	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

}
