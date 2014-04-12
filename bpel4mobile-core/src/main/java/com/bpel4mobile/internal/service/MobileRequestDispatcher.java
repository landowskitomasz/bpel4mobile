package com.bpel4mobile.internal.service;

import java.util.List;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;

public interface MobileRequestDispatcher {

	public List<Task> findUserTasks(UserData userData);
	
	public boolean clime(String taskName, String taskUUID, UserData userData);
	
	public boolean release(String taskName, String taskUUID, UserData userData);
	
	public boolean resolve(String taskName, String taskUUID, String result, UserData userData);
	
}
