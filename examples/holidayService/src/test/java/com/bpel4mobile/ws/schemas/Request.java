package com.bpel4mobile.ws.schemas;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="HolidayProcessResponse")
public class Request {
	
	private String result;

	@XmlElement(name="result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
