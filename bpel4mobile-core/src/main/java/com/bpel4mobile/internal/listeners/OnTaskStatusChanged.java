package com.bpel4mobile.internal.listeners;

import com.bpel4mobile.internal.model.Task;

public interface OnTaskStatusChanged {

	void onStatusChanged(Task<?, ?> task, Task.State oldState, Task.State newState);
}
