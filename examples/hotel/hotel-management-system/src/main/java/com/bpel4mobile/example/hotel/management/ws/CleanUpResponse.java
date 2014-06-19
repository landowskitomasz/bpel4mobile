package com.bpel4mobile.example.hotel.management.ws;

import com.bpel4mobile.example.hotel.management.model.Room;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace=CleanUpServiceCallbackEndpoint.NAMESPACE, name="cleanUpResponse")
public class CleanUpResponse {

    public enum ProcessResponseStatus{
        success, failed
    }

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