package com.bpel4mobile.example.hotel.service;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.ws.data.ProcessResponseStatus;

public interface CleanUpService {

	void handleCleanUpProcessResponse(long roomId, ProcessResponseStatus processResponseStatus);

	void startCleanUpProcess(Room room);

}
