package com.bpel4mobile.example.hotel.repository;

import com.bpel4mobile.example.hotel.entity.RoomReservation;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = RoomReservation.class)
public interface RoomReservationRepository {
}
