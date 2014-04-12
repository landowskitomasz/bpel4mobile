package com.bpel4mobile.internal.controller;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.MobileRequestDispatcher;

@Service
public class MobileRequestController {

	private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	
	@Autowired
	private MobileRequestDispatcher mobileRequestDispatcher;
	
	public void scheduleMobileUserRequests(){
		
		Runnable resolveTaskComment = new Runnable() {
			@Override
			public void run() {
				try{
					UserData user = new UserData();
					user.setUsername("tlandowski");
					
					System.out.println("Looking for tasks to complete.");
					List<Task> tasksToComplete = mobileRequestDispatcher.findUserTasks(new UserData());
					System.out.println("Found "+tasksToComplete.size());
					
					for(Task task : tasksToComplete){
						System.out.println("Resolving task "+task.getName()+ " "+ task.getUuid());
						mobileRequestDispatcher.resolve(task.getName(), task.getUuid(), "", user);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		worker.schedule(resolveTaskComment, 10, TimeUnit.SECONDS);
	}
	
}
