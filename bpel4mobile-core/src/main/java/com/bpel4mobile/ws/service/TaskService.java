package com.bpel4mobile.ws.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bpel4mobile.config.TaskServiceConfig;
import com.bpel4mobile.internal.bean.TaskRequest;
import com.bpel4mobile.internal.service.TaskDispatcher;
import com.bpel4mobile.internal.service.impl.TaskResultResolverImpl;
import com.google.common.base.Preconditions;

public class TaskService implements InitializingBean {
	
	private TaskServiceConfig config;
	
	@Autowired
	private TaskResultResolverImpl taskResultResolver;
	
	@Autowired
	private TaskDispatcher taskDispatcher;

	public TaskServiceConfig getConfig() {
		return config;
	}

	public void setConfig(TaskServiceConfig config) {
		Preconditions.checkNotNull(config);
		Preconditions.checkState(this.config == null, "Service already configured.");
		this.config = config;
		taskResultResolver.registerTask(config.getName(), config.getResultTypes());
	}
	
	public <T, R> Element handleTaskRequest(Element requestData, final Class<T> requestClass, final Class<R> resultClazz){
	
		TaskRequest<T> taskRequest = converToObject(requestData, requestClass);
		String taskUUID = taskDispatcher.createNewTask(config.getName(), taskRequest.getCallbackUrl(), 
				taskRequest.getRequest(), resultClazz);

		Element response = prepareResponse(taskUUID);
	
		return response;
	}

	private Element prepareResponse(String taskUUID) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unable to create xml document builder.", e);
		}
		Document document = builder.newDocument();
		Element taskResultElement = document.createElementNS(B4MNamespace.URL,"TaskResponse");
		Element taskUUIDElement = document.createElementNS(B4MNamespace.URL, "taskUUID");
		
		taskUUIDElement.setTextContent(taskUUID);
		taskResultElement.appendChild(taskUUIDElement);
		document.appendChild(taskResultElement);
		return document.getDocumentElement();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Preconditions.checkState(config!=null, "Service require configuration property to be set.");
	}
	
	private <T> TaskRequest<T> converToObject(Element requestData, Class<T> requestClass){
		
		TaskRequest<T> requestObject = new TaskRequest<T>();
		
		NodeList requestNode = requestData.getElementsByTagNameNS(requestData.getNamespaceURI(), "request");
		Preconditions.checkArgument(requestNode != null && requestNode.getLength() > 0, "Web service request must contains 'request' element.");
		NodeList callbackUrlNode = requestData.getElementsByTagNameNS(requestData.getNamespaceURI(), "callbackUrl");
		Preconditions.checkArgument(callbackUrlNode != null && callbackUrlNode.getLength() > 0, "Web service request must contains 'callbackUrl' element.");
		requestObject.setCallbackUrl(callbackUrlNode.item(0).getTextContent());
		
		try {
			JAXBContext jc = JAXBContext.newInstance(requestClass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			T request = (T)unmarshaller.unmarshal(new DOMSource(requestNode.item(0)));
			requestObject.setRequest(request);
		 
		} catch (XmlMappingException e) {
			e.printStackTrace();
			throw new IllegalStateException("Can't convert request data to given reqest type.",e);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalStateException("Can't convert request data to given reqest type.",e);
		}
		return requestObject;
	}
}
