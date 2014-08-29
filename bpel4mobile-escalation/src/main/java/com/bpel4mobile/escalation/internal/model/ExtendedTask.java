package com.bpel4mobile.escalation.internal.model;

import java.util.List;

import com.bpel4mobile.internal.model.Task;

public class ExtendedTask<T, R> extends Task<T, R> {

	private List<Notification> notifications;

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
}
