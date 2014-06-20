package com.bpel4mobile.internal.service.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.definition.Deadlines;
import com.bpel4mobile.internal.definition.HumanInteractions;
import com.bpel4mobile.internal.definition.LogicalPeopleGroups;
import com.bpel4mobile.internal.definition.PeopleAssignments;
import com.bpel4mobile.internal.definition.Task;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.google.common.collect.Maps;

@Service
public class DefinitionProviderImpl implements DefinitionProvider {

	@Value("${bpel4mobile.humanInteractions.file}")
	private String humanInteractionsDefinition;
	
	private HumanInteractions humanInteractions;
	
	private Map<String, PeopleAssignments> taskPosibleAssignments = Maps.newHashMap();
	
	private Map<String, Task> tasks = Maps.newHashMap();
	
	private Map<String, Deadlines> deadlines = Maps.newHashMap();
	
	@PostConstruct
	public void loadHumanInteractions(){
		
		InputStream io = null;
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			if (classLoader == null) {
			    classLoader = Class.class.getClassLoader();
			}

			io = new BufferedInputStream(classLoader.getResourceAsStream(humanInteractionsDefinition));
			JAXBContext jc = JAXBContext.newInstance(HumanInteractions.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			humanInteractions = (HumanInteractions)unmarshaller.unmarshal(new StreamSource(io));
			
			for(Task task : humanInteractions.getTasks().getTask()){
				tasks.put(task.getName(), task);
				if(task.getDeadlines() != null){
					deadlines.put(task.getName(), task.getDeadlines());
				}
				taskPosibleAssignments.put(task.getName(), task.getPeopleAssignments());
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Can't read human interactions definiton.",e);
		} finally{
			try {
				if(io != null){
					io.close();
				}
			} catch (IOException e) {}
		}
	}
	

	@Override
	public LogicalPeopleGroups getLogicalPeopleGroups() {
		return humanInteractions.getLogicalPeopleGroups();
	}

	@Override
	public Map<String, PeopleAssignments> getTaskPosibleAssignments() {
		return taskPosibleAssignments; 
	}

	@Override
	public Task getTaskDefinition(String taskName) {
		return tasks.get(taskName);
	}


	@Override
	public Map<String, Deadlines> getTasksDeadLines() {
		return deadlines;
	}

}
