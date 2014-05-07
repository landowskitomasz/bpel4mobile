package com.bpel4mobile.example.hotel.repository;

import java.util.Date;
import java.util.List;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomReservation;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = RoomReservation.class)
public interface RoomReservationRepository {


	List<RoomReservation> findByRoom(Room room);
}
