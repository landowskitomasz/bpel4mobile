package com.bpel4mobile.example.hotel.middleware.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

import com.bpel4mobile.example.hotel.middleware.cleanUpTask.CleanUpRequest;
import com.bpel4mobile.example.hotel.middleware.cleanUpTask.CleanUpResponse;
import com.bpel4mobile.example.hotel.middleware.constants.XMLNamespace;
import com.bpel4mobile.ws.service.TaskService;

@Endpoint
public class CleanUpServiceEndpoint {
	
	@Autowired
	@Qualifier(value="cleanUpTaskService")
	private TaskService tasService;
	
	
	@PayloadRoot(namespace = XMLNamespace.CLEAN_UP, localPart = "CleanUpTaskRequest")      
	@ResponsePayload
	public Element handleHolidayRequest(@RequestPayload Element request) throws Exception {
		return tasService.handleTaskRequest(request, CleanUpRequest.class, CleanUpResponse.class);
	}
	  
}
