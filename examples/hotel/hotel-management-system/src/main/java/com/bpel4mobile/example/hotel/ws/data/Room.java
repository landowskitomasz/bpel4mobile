package com.bpel4mobile.example.hotel.ws.data;

import javax.xml.bind.annotation.XmlElement;

import com.bpel4mobile.example.hotel.ws.CleanUpServiceCallbackEndpoint;

public class Room {

	private Long id;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
