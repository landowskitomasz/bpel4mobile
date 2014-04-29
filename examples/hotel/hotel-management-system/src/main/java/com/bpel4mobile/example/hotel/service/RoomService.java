package com.bpel4mobile.example.hotel.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.bpel4mobile.example.hotel.entity.Room;

@RooService(domainTypes = { com.bpel4mobile.example.hotel.entity.Room.class })
public interface RoomService {
	
	public Room updateRoom(Room room);
}
