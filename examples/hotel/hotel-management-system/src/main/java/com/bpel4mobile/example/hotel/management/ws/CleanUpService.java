package com.bpel4mobile.example.hotel.management.ws;

import javax.annotation.PostConstruct;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bpel4mobile.example.hotel.management.model.Room;

@Service
public class CleanUpService {

    private static final String PROCESS_URL = "http://192.168.0.10:8080/ode/processes/cleanUpProcess";

    private WebServiceTemplate wsTemplate;

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @PostConstruct
    public final void createWebServiceTemplate() {
        this.wsTemplate = new WebServiceTemplate();
        marshaller.setClassesToBeBound(new Class<?>[] { ComplexRoom.class, Category.class, CleanUpRequest.class });
    }

    public void startProcess(Room room) {

        ComplexRoom complexRoom = new ComplexRoom();
        complexRoom.setId(room.getId());
        complexRoom.setFloor(room.getFloor());
        complexRoom.setNumber(room.getNumber());

        Category category = new Category();
        category.setName(room.getCategory().getName());
        category.setStandard(room.getCategory().getRoomStandard());
        complexRoom.setCategory(category);

        CleanUpRequest request = new CleanUpRequest();
        request.setRoom(complexRoom);

        DOMResult domResult = new DOMResult();
        marshaller.marshal(request, domResult);

        wsTemplate.sendSourceAndReceiveToResult(PROCESS_URL, new DOMSource(domResult.getNode()), new DOMResult());
    }
}
