package com.bpel4mobile.escalation.internal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.internal.listeners.OnSendNotificationListener;
import com.bpel4mobile.escalation.internal.service.NotificationManager;
import com.bpel4mobile.escalation.internal.service.impl.data.NotificationEscalation;
import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class NotificationManagerImpl implements NotificationManager {

	private List<OnSendNotificationListener> notificationListeners = Lists.newArrayList();
	
	@Override
	public void addOnSendNotificationListener(
			OnSendNotificationListener listener) {
		notificationListeners.add(listener);
	}

	@Override
	public void perform(NotificationEscalation escalation, List<Task> tasks) {
		
		for(Task task : tasks){
			for(OnSendNotificationListener listener : notificationListeners){
				Map<String, Object> notification = Maps.newHashMap();
				notification.put("name", escalation.getName());
				notification.put("taskUUID", task.getUuid());
				notification.put("taskName", task.getName());
				List<UserData> users = Lists.newArrayList();
				// TODO: get users from user data provider
				listener.onSendNotification(notification, users);
			}
		}
	}

}
