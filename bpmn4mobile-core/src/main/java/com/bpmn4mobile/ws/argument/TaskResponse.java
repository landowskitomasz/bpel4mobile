package com.bpmn4mobile.ws.argument;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= TaskResponse.ELEMENT_NAME, namespace=B4MNamespace.URL)
public class TaskResponse {

	public static final String ELEMENT_NAME = "TaskResponse";
	
	private static final String TASK_UUID_ELEMENT = "taskUUID";

	private String taskUUID;

	@XmlElement(name=TASK_UUID_ELEMENT, namespace = B4MNamespace.URL)
	public String getTaskUUID() {
		return taskUUID;
	}

	public void setTaskUUID(String taskUUID) {
		this.taskUUID = taskUUID;
	}
}
