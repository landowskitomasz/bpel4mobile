package com.bpel4mobile.example.hotel.management.ws;

import com.bpel4mobile.example.hotel.management.model.CleanUpStatus;
import com.bpel4mobile.example.hotel.management.model.Room;
import com.bpel4mobile.example.hotel.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CleanUpServiceCallbackEndpoint {

    public static final String NAMESPACE = "http://bpel4mobile.com/example/hotel/schemas";

    @Autowired
    private RoomRepository roomRepository;

    @PayloadRoot(namespace = NAMESPACE, localPart = "cleanUpResponse")
    @ResponsePayload
    public void handleHolidayRequest(@RequestPayload CleanUpResponse request) throws Exception {

        if(CleanUpResponse.ProcessResponseStatus.success.equals(request.getStatus())){
            Room room = roomRepository.getOne(request.getRoom().getId());
            if(room != null){
                room.setStatus(CleanUpStatus.finished);
                roomRepository.save(room);
            }
        }

    }
}
