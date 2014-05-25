package com.bpel4mobile.example.hotel.middleware.cleanUpTask;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;

public class Room {

	private long id;
	
	private int number;
	
	private int floor;
	
	private Category category;
	
	@XmlElement(name="id",namespace=XMLNamespace.CLEAN_UP)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(name="number",namespace=XMLNamespace.CLEAN_UP)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@XmlElement(name="floor",namespace=XMLNamespace.CLEAN_UP)
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@XmlElement(name="category",namespace=XMLNamespace.CLEAN_UP)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
