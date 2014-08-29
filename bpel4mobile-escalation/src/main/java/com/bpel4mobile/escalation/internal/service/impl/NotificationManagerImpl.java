package com.bpel4mobile.escalation.internal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.internal.listeners.OnSendNotificationListener;
import com.bpel4mobile.escalation.internal.model.Notification;
import com.bpel4mobile.escalation.internal.service.NotificationManager;
import com.bpel4mobile.escalation.internal.service.impl.data.NotificationEscalation;
import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.TaskRepository;
import com.bpel4mobile.internal.service.UserDataProvider;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class NotificationManagerImpl implements NotificationManager {

	private List<OnSendNotificationListener> notificationListeners = Lists.newArrayList();
	
	@Autowired
	private UserDataProvider userDataProvider;
	
	@Autowired
	private TaskRepository taskRepository; 
	
	@Override
	public void addOnSendNotificationListener(
			OnSendNotificationListener listener) {
		notificationListeners.add(listener);
	}

	@Override
	public void perform(NotificationEscalation escalation, List<Task> tasks) {
		
		for(Task task : tasks){
			Map<String, Object> notification = Maps.newHashMap();
			notification.put("name", escalation.getName());
			notification.put("taskUUID", task.getUuid());
			notification.put("taskName", task.getName());
			List<UserData> users = Lists.newArrayList();
			// TODO: get users from user data provider userDataProvider
			for(OnSendNotificationListener listener : notificationListeners){
				listener.onSendNotification(notification, users);
			}
			
			Notification notificationHistoryItem = new Notification();
			//notificationHistoryItem.setTaskUUID(task.getUuid());
			notificationHistoryItem.setName(escalation.getName());
			notificationHistoryItem.setDate(new Date());
			
			taskRepository.updateTask(task);
			//notificationRepository.save(notificationHistoryItem);
		}
	}

}
