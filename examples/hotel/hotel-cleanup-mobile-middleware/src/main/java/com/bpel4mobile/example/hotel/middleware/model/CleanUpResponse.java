package com.bpel4mobile.example.hotel.middleware.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result", namespace="http://bpel4mobile.com/example/hotel/schemas")
public class CleanUpResponse {
	
	private boolean success;

	@XmlElement(name="success", namespace="http://bpel4mobile.com/example/hotel/schemas")
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
