package com.bpel4mobile.example.hotel.middleware.model;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.ws.service.B4MNamespace;

public class Category {

	private String name;
	
	private int standard;

	@XmlElement(name="name",namespace=B4MNamespace.URL)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="standard",namespace=B4MNamespace.URL)
	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}
	
	
}
