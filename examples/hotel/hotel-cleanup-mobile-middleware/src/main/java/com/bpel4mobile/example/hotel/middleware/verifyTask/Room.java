package com.bpel4mobile.example.hotel.middleware.verifyTask;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;

public class Room {

	private long id;
	
	private int number;
	
	private int floor;
	
	private Category category;
	
	@XmlElement(name="id",namespace=XMLNamespace.VERIFY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(name="number",namespace=XMLNamespace.VERIFY)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@XmlElement(name="floor",namespace=XMLNamespace.VERIFY)
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@XmlElement(name="category",namespace=XMLNamespace.VERIFY)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
