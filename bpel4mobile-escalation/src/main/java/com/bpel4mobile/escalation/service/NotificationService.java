package com.bpel4mobile.escalation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpel4mobile.escalation.internal.listeners.OnSendNotificationListener;
import com.bpel4mobile.escalation.internal.service.NotificationManager;
import com.bpel4mobile.internal.bean.UserData;

public abstract class NotificationService implements InitializingBean{
	
	@Autowired
	private NotificationManager notificationManager;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		OnSendNotificationListenerImpl listener = new OnSendNotificationListenerImpl();
		notificationManager.addOnSendNotificationListener(listener);
	}
	
	protected abstract void sendNotification(Map<String, Object> notification, List<UserData> recipients);
	
	private class OnSendNotificationListenerImpl implements OnSendNotificationListener {

		@Override
		public void onSendNotification(Map<String, Object> notification,
				List<UserData> recipients) {
			sendNotification(notification, recipients);
			
		}
		
	}
}
