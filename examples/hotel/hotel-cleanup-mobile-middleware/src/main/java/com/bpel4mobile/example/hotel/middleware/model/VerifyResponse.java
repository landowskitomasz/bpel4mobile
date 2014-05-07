package com.bpel4mobile.example.hotel.middleware.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result", namespace="http://bpel4mobile.com/example/hotel/schemas")
public class VerifyResponse {

	public enum Status {
		success, toRepeat
	}
	
	private Status status;

	@XmlElement(name="status", namespace="http://bpel4mobile.com/example/hotel/schemas")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
