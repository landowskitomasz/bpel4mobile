package com.bpel4mobile.example.hotel.management.ws;

import java.io.StringWriter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Node;

import com.bpel4mobile.example.hotel.management.model.Room;

@Service
public class CleanUpService {

    private static final String PROCESS_URL = "http://localhost:8080/ode/processes/cleanUpProcess";

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
        request.setDeadline(new Date());
        request.setRoom(complexRoom);

        DOMResult domResult = new DOMResult();
        marshaller.marshal(request, domResult);

        System.out.println(transformDocumentToString(domResult.getNode()));
        
        wsTemplate.sendSourceAndReceiveToResult(PROCESS_URL, new DOMSource(domResult.getNode()), new DOMResult());
    }
    
    public String transformDocumentToString(Node doc)
    {
        DOMSource dom = new DOMSource(doc);
        StringWriter writer = new StringWriter();  
        StreamResult result = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer;
		try {
			transformer = factory.newTransformer();
			transformer.transform(dom, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

        return writer.toString();
    }
}
