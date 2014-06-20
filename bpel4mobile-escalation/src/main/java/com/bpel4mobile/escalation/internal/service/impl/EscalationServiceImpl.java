package com.bpel4mobile.escalation.internal.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.internal.service.EscalationService;
import com.bpel4mobile.escalation.internal.service.NotificationManager;
import com.bpel4mobile.escalation.internal.service.PriorityManager;
import com.bpel4mobile.escalation.internal.service.impl.data.DeadlineType;
import com.bpel4mobile.escalation.internal.service.impl.data.Escalation;
import com.bpel4mobile.escalation.internal.service.impl.data.EscalationCriteria;
import com.bpel4mobile.escalation.internal.service.impl.data.NotificationEscalation;
import com.bpel4mobile.escalation.internal.service.impl.data.PriorityEscalation;
import com.bpel4mobile.internal.definition.Deadline;
import com.bpel4mobile.internal.definition.Deadlines;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.bpel4mobile.internal.service.TaskRepository;
import com.google.common.collect.Lists;

@Service
public class EscalationServiceImpl implements EscalationService {

	private static Logger logger = Logger.getLogger(EscalationServiceImpl.class.getName());
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private DefinitionProvider definitionProvider;
	
	@Autowired
	private NotificationManager notificationManager;
	
	@Autowired
	private PriorityManager priorityManager;
	
	private List<Escalation> escalations = Lists.newArrayList();
	
	@PostConstruct
	public void prepareDeadlineCriteria(){
		Map<String, Deadlines> deadlines = definitionProvider.getTasksDeadLines();
		for(String taskName: deadlines.keySet()){
			for(Deadline startDeadline: deadlines.get(taskName).getStartDeadlines()){
				if(startDeadline.getEscalation() != null){
					Escalation escalation = prepareEscalation(DeadlineType.start, taskName, startDeadline.getForCondition(), startDeadline.getEscalation());
					if(escalation != null){
						escalations.add(escalation);
					}
				}
			}
			for(Deadline completionDeadline: deadlines.get(taskName).getCompletionDeadlines()){
				if(completionDeadline.getEscalation() != null){
					Escalation escalation = prepareEscalation(DeadlineType.complete, taskName, completionDeadline.getForCondition(), completionDeadline.getEscalation());
					if(escalation != null){
						escalations.add(escalation);
					}
				}
			}
		}
	}
	
	private Escalation prepareEscalation(DeadlineType deadlineType, String taskName, String forCondition, 
			com.bpel4mobile.internal.definition.Escalation escalation) {
		EscalationCriteria criteria = new EscalationCriteria(taskName, deadlineType,
				forCondition, escalation.getCondition());
		if(escalation.getNotification() != null){
			NotificationEscalation notificationEscalation = new NotificationEscalation();
			notificationEscalation.setEscalationCriteria(criteria);
			notificationEscalation.setPeopleAssignments(escalation.getNotification().getPeopleAssignments());
			notificationEscalation.setName(escalation.getNotification().getName());
			return notificationEscalation;
		}
		if(escalation.getRaisePriority() != null){
			PriorityEscalation priorityEscalation = new PriorityEscalation();
			priorityEscalation.setEscalationCriteria(criteria);
			priorityEscalation.setValue(escalation.getRaisePriority().getValue());
			return priorityEscalation;
		}
		return null;
	}

	@Override
	public void checkDeadlines() {
		for(Escalation escalation : escalations){
			List<Task> tasks = taskRepository.find(escalation.getEscalationCriteria().query());
			performEscalation(escalation, tasks);
		}
	}

	private void performEscalation(Escalation escalation, List<Task> tasks) {
		if(escalation instanceof PriorityEscalation){
			priorityManager.perform((PriorityEscalation) escalation, tasks);
		} else if (escalation instanceof NotificationEscalation){
			notificationManager.perform((NotificationEscalation) escalation, tasks);
		} else {
			logger.log(Level.FINEST, "Unknown escalation type.");
		}
		
	}

}
