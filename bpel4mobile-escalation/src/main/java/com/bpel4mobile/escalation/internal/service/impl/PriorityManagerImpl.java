package com.bpel4mobile.escalation.internal.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.internal.service.PriorityManager;
import com.bpel4mobile.escalation.internal.service.impl.data.PriorityEscalation;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.TaskRepository;

@Service
public class PriorityManagerImpl implements PriorityManager {
	
	private static Logger logger = Logger.getLogger(PriorityManagerImpl.class.getName());

	@Autowired
	private TaskRepository repository;
	
	@Override
	public void perform(PriorityEscalation escalation, List<Task> tasks) {
		for(Task task : tasks){
			if(task.getPriority() !=  escalation.getValue()){
				task.setPriority(escalation.getValue());
				repository.updateTask(task);
				logger.info(String.format("Task %s priority raised with value %d", task.getUuid(), escalation.getValue()));
			}
		}
	}
}
