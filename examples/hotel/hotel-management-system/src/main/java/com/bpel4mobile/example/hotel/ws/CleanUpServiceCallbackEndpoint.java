package com.bpel4mobile.example.hotel.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bpel4mobile.example.hotel.service.CleanUpService;

@Endpoint
public class CleanUpServiceCallbackEndpoint {
	
	private static final String NAMESPACE = "http://bpel4mobile.com/example/hotel/schemas";
	
	@Autowired
	private CleanUpService cleanUpService;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "cleanUpResponse")      
	@ResponsePayload
	public void handleHolidayRequest(@RequestPayload Element request) throws Exception {
		 NodeList roomNodeList = request.getElementsByTagNameNS(NAMESPACE, "room");
		 NodeList statusNodeList = request.getElementsByTagNameNS(NAMESPACE, "status");
		 if(roomNodeList == null || roomNodeList.getLength() != 1){
			 throw new IllegalArgumentException("Request have to contains one room element.");
		 }
		 if(statusNodeList == null || statusNodeList.getLength() != 1){
			 throw new IllegalArgumentException("Request have to contains status element");
		 }
			 
		 NodeList roomIdNodeList = ((Element)roomNodeList.item(0)).getElementsByTagNameNS(NAMESPACE, "id");
			
		if(roomIdNodeList == null || roomIdNodeList.getLength() != 1){
			throw new IllegalArgumentException("Room element have to contains one id element.");
		}
		
		long roomId = 0L;
		try{
			roomId = Long.parseLong(roomIdNodeList.item(0).getTextContent());
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("Room id have to be long type. But have value: " + roomIdNodeList.item(0).getTextContent());
		}
		cleanUpService.handleCleanUpProcessResponse(roomId, statusNodeList.item(0).getTextContent());
		
	}
		  
	
}
