package com.bpel4mobile.example.hotel.repository;

import com.bpel4mobile.example.hotel.entity.Room;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Room.class)
public interface RoomRepository {
}
