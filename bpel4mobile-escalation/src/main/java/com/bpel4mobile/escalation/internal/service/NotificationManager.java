package com.bpel4mobile.escalation.internal.service;

import java.util.List;

import com.bpel4mobile.escalation.internal.listeners.OnSendNotificationListener;
import com.bpel4mobile.escalation.internal.service.impl.data.NotificationEscalation;
import com.bpel4mobile.internal.model.Task;

public interface NotificationManager {

	void addOnSendNotificationListener(OnSendNotificationListener listener);

	void perform(NotificationEscalation escalation, List<Task> tasks);

}
