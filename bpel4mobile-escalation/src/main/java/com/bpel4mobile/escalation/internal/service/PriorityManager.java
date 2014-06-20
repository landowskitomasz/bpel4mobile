package com.bpel4mobile.escalation.internal.service;

import java.util.List;

import com.bpel4mobile.escalation.internal.service.impl.data.NotificationEscalation;
import com.bpel4mobile.escalation.internal.service.impl.data.PriorityEscalation;
import com.bpel4mobile.internal.model.Task;

public interface PriorityManager {

	void perform(PriorityEscalation escalation, List<Task> tasks);

}
