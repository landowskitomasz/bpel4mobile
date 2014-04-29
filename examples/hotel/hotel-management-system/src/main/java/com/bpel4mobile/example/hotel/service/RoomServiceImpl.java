package com.bpel4mobile.example.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomState;
import com.bpel4mobile.example.hotel.repository.RoomRepository;


public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private CleanUpService cleanUpService;
	
	@Autowired
    private RoomRepository roomRepository;

	@Override
	public Room updateRoom(Room room) {
		
		if(RoomState.toCleanUp.equals(room.getState())){
			Room existingRoom = roomRepository.findOne(room.getId());
			if(existingRoom != null && !room.getState().equals(existingRoom.getState())){
				cleanUpService.startCleanUpProcess(room);
			}
		}
		
        return roomRepository.save(room);
    }
	
	
}
