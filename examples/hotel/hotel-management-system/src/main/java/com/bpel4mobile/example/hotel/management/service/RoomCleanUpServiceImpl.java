package com.bpel4mobile.example.hotel.management.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.example.hotel.management.model.CleanUpStatus;
import com.bpel4mobile.example.hotel.management.model.Room;
import com.bpel4mobile.example.hotel.management.repository.RoomRepository;

@Service
public class RoomCleanUpServiceImpl implements RoomCleanUpService {

	private static Logger logger = Logger.getLogger(RoomCleanUpServiceImpl.class.getName());
	
    @Autowired
    private RoomRepository roomRepository;
	
    @Transactional
	public void completeRoom(int id, String status) {
    	
    	Room room = roomRepository.findById(id);
    	if(room == null){
    		logger.log(Level.WARNING, "Room with id " +id+ "not exists.");
    		return ;
    	}
    	
    	if("success".equals(status)){
    		room.setStatus(CleanUpStatus.finished);
    	} else {
    		room.setStatus(CleanUpStatus.failed);
    	}
		
    	roomRepository.save(room);
	}

}
