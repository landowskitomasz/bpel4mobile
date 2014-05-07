package com.bpel4mobile.example.hotel.middleware.model;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.ws.service.B4MNamespace;

public class Room {

	private long id;
	
	private int number;
	
	private int floor;
	
	private Category category;
	
	@XmlElement(name="id",namespace=B4MNamespace.URL)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(name="number",namespace=B4MNamespace.URL)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@XmlElement(name="floor",namespace=B4MNamespace.URL)
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@XmlElement(name="category",namespace=B4MNamespace.URL)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
