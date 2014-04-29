package com.bpel4mobile.example.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomState;
import com.bpel4mobile.example.hotel.repository.RoomRepository;

@Service
public class CleanUpServiceImpl implements CleanUpService {

	public enum ProcessResponseStatus{
		success, failed
	}
	

	@Autowired
    private RoomRepository roomRepository;
	
	@Override
	public void handleCleanUpProcessResponse(long roomId, String statusValue) {
		ProcessResponseStatus status = ProcessResponseStatus.valueOf(statusValue);
		
		Room room = roomRepository.findOne(roomId);
		if(room == null){
			throw new IllegalArgumentException(String.format("Room with id %d not exists.", roomId));
		}
		
		if(ProcessResponseStatus.success.equals(status)){
			room.setState(RoomState.ready);
		} else {
			room.setState(RoomState.broken);
		}
		
		roomRepository.save(room);
	}

	@Override
	public void startCleanUpProcess(Room room) {
		System.out.println(room.getId());
		
	}

}
