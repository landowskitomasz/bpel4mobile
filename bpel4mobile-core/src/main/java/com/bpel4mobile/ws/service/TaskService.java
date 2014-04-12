package com.bpel4mobile.ws.service;

import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpel4mobile.config.TaskServiceConfig;
import com.bpel4mobile.internal.service.TaskDispatcher;
import com.bpel4mobile.internal.service.impl.TaskResultResolverImpl;
import com.bpel4mobile.ws.argument.TaskRequest;
import com.bpel4mobile.ws.argument.TaskResponse;
import com.google.common.base.Preconditions;

public class TaskService implements InitializingBean {
	
	private TaskServiceConfig config;
	
	@Autowired
	private TaskResultResolverImpl taskResultResolver;
	
	@Autowired
	private TaskDispatcher taskDispatcher;

	public TaskServiceConfig getConfig() {
		return config;
	}

	public void setConfig(TaskServiceConfig config) {
		Preconditions.checkNotNull(config);
		Preconditions.checkState(this.config == null, "Service already configured.");
		this.config = config;
		taskResultResolver.registerTask(config.getName(), config.getResultTypes());
	}
	
	//private static final ScheduledExecutorService worker = 
	//		  Executors.newSingleThreadScheduledExecutor();
	
	public <T, R> TaskResponse handleTaskRequest(final TaskRequest<T> taskRequest, final Class<R> resultClazz){
		
		
		String taskUUID = taskDispatcher.createNewTask(config.getName(), taskRequest.getCallbackUrl(), 
				taskRequest.getRequest(), resultClazz);
		
		final TaskResponse taskResponse = new TaskResponse();
		taskResponse.setTaskUUID(taskUUID);
		/*final TaskResultResolverImpl resolverInstance =  taskResultResolver;
		Runnable resolveTaskComment = new Runnable() {
			
			@Override
			public void run() {
				try{
					TaskResult<R> result = new TaskResult<R>();
					result.setTaskUUID(taskResponse.getTaskUUID());
					result.setCallbackUrl(taskRequest.getCallbackUrl());
					try {
						Constructor<R> constructor = resultClazz.getConstructor();
						result.setResult(constructor.newInstance());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					resolverInstance.resolveTask(config.getName(), result);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		worker.schedule(resolveTaskComment, 10, TimeUnit.SECONDS);
		*/
		return taskResponse;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Preconditions.checkState(config!=null, "Service require configuration property to be set.");
	}
}
