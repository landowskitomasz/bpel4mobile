package com.bpel4mobile.example.hotel.middleware.verifyTask;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;

public class Category {

	private String name;
	
	private int standard;

	@XmlElement(name="name",namespace=XMLNamespace.VERIFY)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="standard",namespace=XMLNamespace.VERIFY)
	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}
	
	
}
