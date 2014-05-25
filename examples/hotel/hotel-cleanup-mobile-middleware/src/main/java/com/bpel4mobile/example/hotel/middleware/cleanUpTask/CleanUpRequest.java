package com.bpel4mobile.example.hotel.middleware.cleanUpTask;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;


@XmlRootElement(name="request", namespace= XMLNamespace.CLEAN_UP)
public class CleanUpRequest {
	
	private Date deadline;
	 
	private Room room;

	@XmlElement(name="deadline",namespace=XMLNamespace.CLEAN_UP)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@XmlElement(name="room", namespace=XMLNamespace.CLEAN_UP)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
