package com.bpel4mobile.ws.service;

import java.util.UUID;

import com.bpel4mobile.ws.argument.TaskRequest;
import com.bpel4mobile.ws.argument.TaskResponse;

public class TaskService {

	private String callbackWsdl;
	
	public <T, R> TaskResponse handleTaskRequest(final TaskRequest<T> taskRequest, final Class<R> resultClazz){
		TaskResponse taskResponse = new TaskResponse();
		taskResponse.setTaskUUID(UUID.randomUUID().toString());
		
		System.out.println(taskResponse.getTaskUUID());
		
		return taskResponse;
	}
	
	public void setCallbackWsdl(final String callbackWsdl){
		this.callbackWsdl = callbackWsdl;
	}
}
