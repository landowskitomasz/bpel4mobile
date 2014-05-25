package com.bpel4mobile.example.hotel.middleware.verifyTask;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;

@XmlRootElement(name="request", namespace= XMLNamespace.VERIFY)
public class VerifyRequest {

	private Date deadline;
	
	private String cleanUpPerformer;
	 
	private Room room;

	@XmlElement(name="deadline",namespace=XMLNamespace.VERIFY)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@XmlElement(name="cleanUpPerformer", namespace=XMLNamespace.VERIFY)
	public String getCleanUpPerformer() {
		return cleanUpPerformer;
	}

	public void setCleanUpPerformer(String cleanUpPerformer) {
		this.cleanUpPerformer = cleanUpPerformer;
	}
	
	@XmlElement(name="room", namespace=XMLNamespace.VERIFY)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
