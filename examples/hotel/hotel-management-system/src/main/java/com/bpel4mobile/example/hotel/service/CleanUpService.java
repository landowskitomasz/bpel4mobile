package com.bpel4mobile.example.hotel.service;

import com.bpel4mobile.example.hotel.entity.Room;

public interface CleanUpService {

	void handleCleanUpProcessResponse(long roomId, String status);

	void startCleanUpProcess(Room room);

}
