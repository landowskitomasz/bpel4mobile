package com.bpel4mobile.example.hotel.management.ws;

import javax.xml.bind.annotation.XmlElement;

public class Category {

	private String name;

	private int standard;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="name")
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="standard")
	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}


}