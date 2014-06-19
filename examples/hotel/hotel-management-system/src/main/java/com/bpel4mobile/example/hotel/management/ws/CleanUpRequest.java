package com.bpel4mobile.example.hotel.management.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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