package com.bpel4mobile.example.hotel.middleware.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

import com.bpel4mobile.example.hotel.middleware.model.VerifyRequest;
import com.bpel4mobile.example.hotel.middleware.model.VerifyResponse;
import com.bpel4mobile.ws.service.B4MNamespace;
import com.bpel4mobile.ws.service.TaskService;

@Endpoint
public class VerifyServiceEndpoint {

	@Autowired
	@Qualifier(value="verifyTaskService")
	private TaskService tasService;
	
	@PayloadRoot(namespace = B4MNamespace.URL, localPart = "VerifyTaskRequest")      
	@ResponsePayload
	public Element handleHolidayRequest(@RequestPayload Element request) throws Exception {
		return tasService.handleTaskRequest(request, VerifyRequest.class, VerifyResponse.class);
	}
}
