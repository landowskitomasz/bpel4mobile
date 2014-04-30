package com.bpel4mobile.example.hotel.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bpel4mobile.example.hotel.service.CleanUpService;
import com.bpel4mobile.example.hotel.ws.data.CleanUpResponse;

@Endpoint
public class CleanUpServiceCallbackEndpoint {
	
	public static final String NAMESPACE = "http://bpel4mobile.com/example/hotel/schemas";
	
	@Autowired
	private CleanUpService cleanUpService;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "cleanUpResponse")      
	@ResponsePayload
	public void handleHolidayRequest(@RequestPayload CleanUpResponse request) throws Exception {
		
		cleanUpService.handleCleanUpProcessResponse(request.getRoom().getId(), request.getStatus());
		
	}
		  
	
}
