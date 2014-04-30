package com.bpel4mobile.example.hotel.ws.data;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.example.hotel.ws.CleanUpServiceCallbackEndpoint;

public class ComplexRoom {

	private long id;
	
	private int number;
	
	private int floor;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="number")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="floor")
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
}
