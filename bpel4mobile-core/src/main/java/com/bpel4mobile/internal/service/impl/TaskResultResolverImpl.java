package com.bpel4mobile.internal.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bpel4mobile.internal.bean.TaskResult;
import com.bpel4mobile.internal.service.TaskResultResolver;
import com.google.common.base.Preconditions;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@Service
public class TaskResultResolverImpl implements TaskResultResolver {

	private static Logger log = Logger.getLogger(TaskResultResolverImpl.class.getName());
	
	private Map<String, Jaxb2Marshaller> resultMarshalers = new HashMap<String, Jaxb2Marshaller>();
	
	private WebServiceTemplate wsTemplate;
	
	@PostConstruct
	public void createWebServiceTemplate(){
		this.wsTemplate = new WebServiceTemplate();
	}
	
	@Override
	public void registerTask(final String taskIdentifier, final Class<?>[] resultTypes) {
		log.info("Task "+ taskIdentifier + "registered.");
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(resultTypes);
		
		resultMarshalers.put(taskIdentifier, marshaller);
	}
	
	@Override
	public <R> void resolveTask(final String taskIdentifier, final TaskResult<R> result){
		
		Jaxb2Marshaller taskResultMarshaller = resultMarshalers.get(taskIdentifier);
		Preconditions.checkState(taskIdentifier != null, String.format("Task with '%s' not registered.", taskIdentifier));
		
		DOMResult domResult = new DOMResult();
		taskResultMarshaller.marshal(result.getResult(), domResult);
		domResult = wrapResultWithTaskData(domResult, result.getTaskUUID(), result.getResolver(), taskIdentifier);
		
		log.info("Responding to process callback: " + result.getCallbackUrl());
		wsTemplate.sendSourceAndReceiveToResult(result.getCallbackUrl(), new DOMSource(domResult.getNode()), new DOMResult());
	}
	
	private DOMResult wrapResultWithTaskData(DOMResult domResult, String taskUUID, String resolver, String taskIdentifier) {
		
		Node nodeToWrap = domResult.getNode().getFirstChild();
		
		String namespace = nodeToWrap.getNamespaceURI();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unable to create xml document builder.", e);
		}
		Document document = builder.newDocument();
		Element taskResultElement = document.createElementNS(namespace, taskIdentifier + "Result");
		Element taskUUIDElement = document.createElementNS(namespace, "taskUUID");
        Element taskResolverElement = document.createElementNS(namespace, "resolver");
        taskResolverElement.setTextContent(resolver);
		Element resultElement = document.createElementNS(namespace, "result");
		
		taskUUIDElement.setTextContent(taskUUID);
		for(int i = 0; i< nodeToWrap.getChildNodes().getLength() ; ++i){
			resultElement.appendChild(document.importNode(nodeToWrap.getChildNodes().item(i), true));
		}
		taskResultElement.appendChild(taskResolverElement);
		taskResultElement.appendChild(taskUUIDElement);
		taskResultElement.appendChild(resultElement);
		document.appendChild(taskResultElement);
		
		DOMResult wrapper = new DOMResult(document);
		return wrapper;
	}
	

}
