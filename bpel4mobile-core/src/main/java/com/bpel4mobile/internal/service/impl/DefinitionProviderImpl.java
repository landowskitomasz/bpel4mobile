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


	
	/*PeopleAssignments peopleAssignments = new PeopleAssignments();
	PotentialOwners potentialOwners = new PotentialOwners();
	
	PeopleAssignment peopleAssignment = new PeopleAssignment();
	From from = new From();
	from.setLogicalPeopleGroup("projectManagers");
	Argument argument = new Argument();
	argument.setName("username");
	argument.setExpression("eq:request/employee");
	from.getArguments().add(argument);
	peopleAssignment.setFrom(from);
	potentialOwners.getPeopleAssigment().add(peopleAssignment);
	peopleAssignments.setPotentialOwners(potentialOwners);
	
	Map<String,PeopleAssignments> result = Maps.newHashMap();
	result.put("holidayRequestTask", peopleAssignments);*/
}
