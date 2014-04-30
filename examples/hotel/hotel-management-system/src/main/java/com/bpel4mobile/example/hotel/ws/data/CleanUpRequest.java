package com.bpel4mobile.example.hotel.ws.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.example.hotel.ws.CleanUpServiceCallbackEndpoint;

@XmlRootElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="cleanUpRequest")
public class CleanUpRequest {

	private ComplexRoom room;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="room")
	public ComplexRoom getRoom() {
		return room;
	}

	public void setRoom(ComplexRoom room) {
		this.room = room;
	}
	
}
