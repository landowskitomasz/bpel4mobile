package com.bpel4mobile.internal.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.bpel4mobile.internal.model.Task;

public interface TaskRepository {

	<T, R> void createTask(Task<T, R> task);
	
	<T, R> void updateTask(Task<T, R> task);
	
	Task<?, ?> findOne(String name, String uuid);

	List<Task> find(Query quuery);
}
