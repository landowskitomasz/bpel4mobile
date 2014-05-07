package com.bpel4mobile.example.hotel.middleware.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.ws.service.B4MNamespace;

@XmlRootElement(name="request", namespace= B4MNamespace.URL)
public class VerifyRequest {

	private Date deadline;
	
	private String cleanUpPerformer;
	 
	private Room room;

	@XmlElement(name="deadline",namespace=B4MNamespace.URL)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@XmlElement(name="cleanUpPerformer", namespace=B4MNamespace.URL)
	public String getCleanUpPerformer() {
		return cleanUpPerformer;
	}

	public void setCleanUpPerformer(String cleanUpPerformer) {
		this.cleanUpPerformer = cleanUpPerformer;
	}
	
	@XmlElement(name="room", namespace=B4MNamespace.URL)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
