package com.bpel4mobile.internal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.bean.TaskResult;
import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.model.Task.State;
import com.bpel4mobile.internal.service.MobileRequestDispatcher;
import com.bpel4mobile.internal.service.TaskRepository;
import com.bpel4mobile.internal.service.TaskResultResolver;

@Service
public class MobileRequestDispatcherImpl implements MobileRequestDispatcher{

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskResultResolver taskResultResolver;
	
	@Override
	public List<Task> findUserTasks(UserData userData) {
		Query query = new Query(Criteria.where(Task.NAME_FIELD).is("holidayRequestTask").and(Task.STATE_FIELD).is(State.ready));
		return taskRepository.find(query);
	}

	@Override
	public boolean clime(String taskName, String taskUUID, UserData userData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean release(String taskName, String taskUUID, UserData userData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resolve(String taskName, String taskUUID, String result,
			UserData userData) {
		Task<?,?> task = taskRepository.findOne(taskName, taskUUID);
		
		taskResultResolver.resolveTask(taskName, createResultWrapper(task.getUuid(), task.getCallbackUrl(),
				task.getResult(), task.getResult().getClass()));
		
		task.setState(State.completed);
		taskRepository.updateTask(task);
		return true;
	}
	
	private <R> TaskResult<R> createResultWrapper(String taskUUID,String callbackUrl, Object result, Class<R> resultClass){
		TaskResult<R> taskResult = new TaskResult<R>();
		taskResult.setResult((R)result);
		taskResult.setCallbackUrl(callbackUrl);
		taskResult.setTaskUUID(taskUUID);
		return taskResult;
	}

	
}
