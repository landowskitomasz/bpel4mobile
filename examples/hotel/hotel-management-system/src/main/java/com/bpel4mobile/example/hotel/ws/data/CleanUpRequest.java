package com.bpel4mobile.example.hotel.ws.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.example.hotel.ws.CleanUpServiceCallbackEndpoint;

@XmlRootElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="cleanUpRequest")
public class CleanUpRequest {

	private Date deadline;
	
	private ComplexRoom room;

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="room")
	public ComplexRoom getRoom() {
		return room;
	}

	public void setRoom(ComplexRoom room) {
		this.room = room;
	}

	@XmlElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="deadline")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
}
