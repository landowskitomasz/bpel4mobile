package com.bpel4mobile.example.hotel.management.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.bpel4mobile.example.hotel.management.service.RoomCleanUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Endpoint
public class CleanUpServiceCallbackEndpoint {
	
	private static Logger logger = Logger.getLogger(CleanUpServiceCallbackEndpoint.class.getName());

    public static final String NAMESPACE = "http://bpel4mobile.com/example/hotel/schemas";

    @Autowired
    private RoomCleanUpService cleanUpService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "cleanUpResponse")
    public void handleProcessResult(@RequestPayload Element request) throws Exception {
    	logger.info("Process callback invoked");

    	try{
	    	NodeList statusNode = request.getElementsByTagNameNS(NAMESPACE, "status");
	    	
	    	NodeList roomNode = request.getElementsByTagNameNS(NAMESPACE, "room");
	    	Node roomIdNode = ((Element)roomNode.item(0)).getElementsByTagNameNS(NAMESPACE, "id").item(0);
	    	
	    	int id = Integer.parseInt(roomIdNode.getTextContent());
	    	
	    	cleanUpService.completeRoom(id, statusNode.item(0).getTextContent());
    	} catch(Exception e){
    		logger.log(Level.WARNING, "Unable to complete room.", e);
    	}

    }
}
