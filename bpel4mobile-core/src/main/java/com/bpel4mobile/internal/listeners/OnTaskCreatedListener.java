package com.bpel4mobile.internal.listeners;

import com.bpel4mobile.internal.model.Task;


public interface OnTaskCreatedListener {

	void onCreated(Task<?, ?> task);

}
