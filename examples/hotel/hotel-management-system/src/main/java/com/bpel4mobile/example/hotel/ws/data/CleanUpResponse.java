package com.bpel4mobile.example.hotel.ws.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.example.hotel.ws.CleanUpServiceCallbackEndpoint;

@XmlRootElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="cleanUpResponse")
public class CleanUpResponse {

	private ProcessResponseStatus status;
	
	private Room room;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="status")
	public ProcessResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessResponseStatus status) {
		this.status = status;
	}

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="room")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
