package com.bpel4mobile.example.hotel.management.repository;

import com.bpel4mobile.example.hotel.management.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer> {
	
	Room findById(int id);
}
