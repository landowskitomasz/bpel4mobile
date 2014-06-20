package com.bpel4mobile.internal.service.impl;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.listeners.OnTaskCreatedListener;
import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.model.Task.State;
import com.bpel4mobile.internal.service.DefinitionProvider;
import com.bpel4mobile.internal.service.TaskDispatcher;
import com.bpel4mobile.internal.service.TaskRepository;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Service
public class TaskDispatcherImpl implements TaskDispatcher {

	private static Logger LOG = Logger.getLogger(TaskDispatcherImpl.class);

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private DefinitionProvider definitionProvider;
	
	private List<OnTaskCreatedListener> onCreateListeners = Lists.newArrayList();
	
	public void addOnTaskCreatedListener(OnTaskCreatedListener createListener){
		onCreateListeners.add(createListener);
	}
	
	public void removeOnTaskCreatedListener(OnTaskCreatedListener createListener){
		int index = onCreateListeners.indexOf(createListener);
		if(index != -1){
			onCreateListeners.remove(index);
		}
	}
	
	@Override
	public <T, R> String createNewTask(String taskName, String callbackUrl, T request, Class<R> resultType) {

		com.bpel4mobile.internal.definition.Task definition = definitionProvider.getTaskDefinition(taskName);
		Preconditions.checkNotNull(definition, "Can't find task definition.");
		
		String uuid = UUID.randomUUID().toString();
		
		Task<T,R> task = new Task<T, R>();
		task.setUuid(uuid);
		task.setName(taskName);
		task.setCallbackUrl(callbackUrl);
		task.setCreateDate(new Date());
		task.setPriority(resolvePriority(definition.getPriority(), request));
		task.setRequest(request);
		task.setResult(tryToCreateInstance(resultType));
		task.setState(State.ready);
		
		taskRepository.createTask(task);
		
		callOnTaskCreatedListeners(task);
		
		return uuid;
	}
	
	private <R> R tryToCreateInstance(Class<R> resultType){
		try{
			Constructor<R> constructor = resultType.getConstructor();
			return constructor.newInstance();
		} catch (Exception e){
			LOG.error("Can't create instance of result class. This is realy fatal. "
						+ "Make sure class have default no argument constructor.");
		}
		
		return null;
	}
	
	private <T> int resolvePriority(String expression, T request){
		if(expression != null){
			if(expression.startsWith("exp:")){
				
			}
			else {
				try{
					return Integer.parseInt(expression.trim());
				} catch(NumberFormatException e){
					LOG.error("Priority expression not containt exp: prefix, so it shoud be integer type."
								+" Thare was a problem when trying to parse it.");
				}
			}
		}
		return 5;
	}

	
	private void callOnTaskCreatedListeners(Task task){
		for(OnTaskCreatedListener listener : onCreateListeners){
			listener.onCreated(task);
		}
	}
}
