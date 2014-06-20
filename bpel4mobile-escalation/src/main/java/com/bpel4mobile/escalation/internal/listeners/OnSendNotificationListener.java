package com.bpel4mobile.escalation.internal.listeners;

import java.util.List;
import java.util.Map;

import com.bpel4mobile.internal.bean.UserData;

public interface OnSendNotificationListener {

	void onSendNotification(Map<String, Object> notification, List<UserData> recipients);
}
