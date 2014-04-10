package com.bpmn4mobile.ws.argument;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name=TaskResult.ELEMENT_NAME, namespace=B4MNamespace.URL)
public class TaskResult<T> {

	public static final String ELEMENT_NAME = "TaskResult";
	
	private static final String TASK_UUID_ELEMENT = "taskUUID";
	
	private static final String RESULT_ELEMENT = "result";

	private String taskUUID;
	
	private T result;

	@XmlElement(name=TASK_UUID_ELEMENT, namespace = B4MNamespace.URL)
	public String getTaskUUID() {
		return taskUUID;
	}

	public void setTaskUUID(String taskUUID) {
		this.taskUUID = taskUUID;
	}

	@XmlElement(name=RESULT_ELEMENT, namespace = B4MNamespace.URL)
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	
	
}
