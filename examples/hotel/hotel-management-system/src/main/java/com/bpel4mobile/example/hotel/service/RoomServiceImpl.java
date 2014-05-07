package com.bpel4mobile.example.hotel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomReservation;
import com.bpel4mobile.example.hotel.entity.RoomState;
import com.bpel4mobile.example.hotel.repository.RoomRepository;
import com.bpel4mobile.example.hotel.repository.RoomReservationRepository;


public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private CleanUpService cleanUpService;
	
	@Autowired
    private RoomRepository roomRepository;
	
	@Autowired 
	private RoomReservationRepository roomReservationRepository;

	@Override
	public Room updateRoom(Room room) {
		
		if(RoomState.toCleanUp.equals(room.getState())){
			Room existingRoom = roomRepository.findOne(room.getId());
			if(existingRoom != null && !room.getState().equals(existingRoom.getState())){
				
				RoomReservation nextReservation = getNextReservation(existingRoom);
				cleanUpService.startCleanUpProcess(room, nextReservation);
			}
		}
		
        return roomRepository.save(room);
    }

	private RoomReservation getNextReservation(Room room) {
		
		//List<RoomReservation> featureRoomReservation = 
		//		roomReservationRepository.findByRoom(room);
		
		//if(!featureRoomReservation.isEmpty()){
		//	return featureRoomReservation.get(0);
		//}
		
		return null;
	}
	
	
}
