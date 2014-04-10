package com.bpel4mobile.ws.argument;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = TaskRequest.ELEMENT_NAME, namespace = B4MNamespace.URL)
public class TaskRequest<T> {

	public static final String ELEMENT_NAME = "TaskRequest";
	
	private static final String CALLBACK_URL_ELEMENT = "callbackUrl";

	private static final String REQUEST_ELEMENT = "request";

	private String callbackUrl;
	
	private List<T> request;

	@XmlElement(name = CALLBACK_URL_ELEMENT, namespace = B4MNamespace.URL)
	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	//@XmlElement(name = REQUEST_ELEMENT, namespace = B4MNamespace.URL)
	@XmlElementWrapper(name = REQUEST_ELEMENT, namespace = B4MNamespace.URL)
    @XmlAnyElement(lax=true)
	public List<T> getRequest() {
		return request;
	}

	public void setRequest(List<T> request) {
		this.request = request;
	}
}
