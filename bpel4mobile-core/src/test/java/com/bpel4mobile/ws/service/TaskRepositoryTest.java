package com.bpel4mobile.ws.service;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.TaskRepository;

public class TaskRepositoryTest {
	
	private static TaskRepository taskRepository;

	//@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/test-context.xml");
		taskRepository = (TaskRepository) appContext.getBean("taskRepositoryImpl");
	}
	
	//@Test
	public void createTaskTest(){
		Task<TaskRequest, TaskResult> task = new Task<TaskRequest, TaskResult>();
		
		String uuid = UUID.randomUUID().toString();
		String name = "testCaseTask";
		
		task.setUuid(uuid);
		task.setName("testCaseTask");
		task.setRequest(new TaskRequest());
		task.setResult(new TaskResult());
		task.setCreateDate(new Date());
		task.setCallbackUrl("http://bpel4mobile.tests/process/callback");
		
		taskRepository.createTask(task);
		try{
			Task<?, ?> persistantTask = taskRepository.findOne(name, uuid);
	
			System.out.println(persistantTask.getResult() instanceof TaskResult);
			Assert.assertTrue(persistantTask.getResult() instanceof TaskResult);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
