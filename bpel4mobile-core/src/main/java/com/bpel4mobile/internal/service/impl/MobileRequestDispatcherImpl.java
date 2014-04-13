package com.bpel4mobile.internal.service.impl;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.bean.TaskResult;
import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.model.Task.State;
import com.bpel4mobile.internal.service.MobileRequestDispatcher;
import com.bpel4mobile.internal.service.QueryFactory;
import com.bpel4mobile.internal.service.TaskRepository;
import com.bpel4mobile.internal.service.TaskResultResolver;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Service
public class MobileRequestDispatcherImpl implements MobileRequestDispatcher{

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskResultResolver taskResultResolver;
	
	@Autowired
	private QueryFactory queryFactory;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public List<Task> findUserTasks(UserData userData) {
		Query tasksToPerformQuery = queryFactory.tasksToPerformByUserQuery(userData);
		Query tasksInProgressQuery = queryFactory.tasksInProgresByUserQuery(userData);

		List<Task> tasks = Lists.newArrayList();
		try{
			tasks.addAll(taskRepository.find(tasksInProgressQuery));
			tasks.addAll(taskRepository.find(tasksToPerformQuery));
		}catch(Exception e){
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		return tasks;
	}

	@Override
	public boolean clime(String taskName, String taskUUID, UserData userData) {
		Task<?,?> task = taskRepository.findOne(taskName, taskUUID);

		Preconditions.checkArgument(task != null, "Task not exists.");
		Preconditions.checkState(State.ready.equals(task.getState()), "Task already climend or resolved.");
		// TODO: check if user still has access
		
		task.setClimeDate(new Date());
		task.setAssignee(userData.getUsername());
		task.setState(State.climed);
		taskRepository.updateTask(task);
		
		return true;
	}

	@Override
	public boolean release(String taskName, String taskUUID, UserData userData) {
		Task<?,?> task = taskRepository.findOne(taskName, taskUUID);

		Preconditions.checkArgument(task != null, "Task not exists.");
		Preconditions.checkState(State.climed.equals(task.getState()), "Task is in state diffarent then climend.");
		Preconditions.checkState(userData.getUsername().equals(task.getAssignee()), "Task is not assigned to you.");
		// TODO: check if user still has access
		
		task.setAssignee(null);
		task.setClimeDate(null);
		task.setState(State.ready);
		taskRepository.updateTask(task);
		return true;
	}

	@Override
	public boolean resolve(String taskName, String taskUUID, String result,
			UserData userData) {
		Task<?,?> task = taskRepository.findOne(taskName, taskUUID);

		Preconditions.checkArgument(task != null, "Task not exists.");
		Preconditions.checkState(State.climed.equals(task.getState()), "Task is in state diffarent then climend.");
		Preconditions.checkState(userData.getUsername().equals(task.getAssignee()), "Task is not assigned to you.");
		// TODO: check if user still has access
		
		taskResultResolver.resolveTask(taskName, wrappResultByTaskData((Task<?, Object>)task, result, task.getResult().getClass()));
		
		task.setCompleteDate(new Date());
		task.setState(State.completed);
		taskRepository.updateTask(task);

		return true;
	}
	
	private <R> TaskResult<R> wrappResultByTaskData(Task<?, Object> task, String result, Class<R> resultClass){
		TaskResult<R> taskResult = new TaskResult<R>();
		try {
			taskResult.setResult(mapper.readValue(result, resultClass));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		taskResult.setCallbackUrl(task.getCallbackUrl());
		taskResult.setTaskUUID(task.getUuid());
		Object resultObject = taskResult.getResult();
		task.setResult(resultObject);
		return taskResult;
	}

	
}
