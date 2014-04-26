package com.company.holidayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

import com.bpel4mobile.ws.service.B4MNamespace;
import com.bpel4mobile.ws.service.TaskService;
import com.company.holidayService.model.HolidayRequest;
import com.company.holidayService.model.HolidayResponse;

@Endpoint
public class HolidayEndpoint {
	
	@Autowired
	@Qualifier(value="holidayTaskService")
	private TaskService tasService;
	
	
	@PayloadRoot(namespace = B4MNamespace.URL, localPart = "TaskRequest")      
	@ResponsePayload
	public Element handleHolidayRequest(@RequestPayload Element request) throws Exception {
		return tasService.handleTaskRequest(request, HolidayRequest.class, HolidayResponse.class);
	}
	  
}
