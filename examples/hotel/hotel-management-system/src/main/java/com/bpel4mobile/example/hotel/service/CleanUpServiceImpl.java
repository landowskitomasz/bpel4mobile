package com.bpel4mobile.example.hotel.service;

import javax.annotation.PostConstruct;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomReservation;
import com.bpel4mobile.example.hotel.entity.RoomState;
import com.bpel4mobile.example.hotel.repository.RoomRepository;
import com.bpel4mobile.example.hotel.ws.data.Category;
import com.bpel4mobile.example.hotel.ws.data.CleanUpRequest;
import com.bpel4mobile.example.hotel.ws.data.ComplexRoom;
import com.bpel4mobile.example.hotel.ws.data.ProcessResponseStatus;

@Service
public class CleanUpServiceImpl implements CleanUpService {

	private static final String PROCESS_URL = "http://localhost:8080/ode/processes/cleanUpProcess";

	private WebServiceTemplate wsTemplate;

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	
	@PostConstruct
	public final void createWebServiceTemplate(){
		this.wsTemplate = new WebServiceTemplate();
		marshaller.setClassesToBeBound(new Class<?>[] {CleanUpRequest.class, ComplexRoom.class});
	}

	@Autowired
    private RoomRepository roomRepository;
	
	@Override
	public void handleCleanUpProcessResponse(long roomId, ProcessResponseStatus status) {
		
		Room room = roomRepository.findOne(roomId);
		if(room == null){
			throw new IllegalArgumentException(String.format("Room with id %d not exists.", roomId));
		}
		
		if(ProcessResponseStatus.success.equals(status)){
			room.setState(RoomState.ready);
		} else {
			room.setState(RoomState.broken);
		}
		
		roomRepository.save(room);
	}

	@Override
	public void startCleanUpProcess(Room room, RoomReservation nextReservation) {
		
		ComplexRoom complexRoom = new ComplexRoom();
		complexRoom.setId(room.getId());
		complexRoom.setFloor(room.getFloor());
		complexRoom.setNumber(room.getFloor());
		
		Category category = new Category();
		category.setName(room.getCategory().getName());
		category.setStandard(room.getCategory().getStandard());
		complexRoom.setCategory(category);
		
		CleanUpRequest request = new CleanUpRequest();
		request.setRoom(complexRoom);
		if(nextReservation != null){
			request.setDeadline(nextReservation.getStartDate());
		}
		
		DOMResult domResult = new DOMResult();
		marshaller.marshal(request, domResult);
		
		wsTemplate.sendSourceAndReceiveToResult(PROCESS_URL, new DOMSource(domResult.getNode()), new DOMResult());

	}

}
