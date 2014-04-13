package com.bpel4mobile.internal.controller;

import java.io.IOException;
import javax.annotation.PostConstruct;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.bean.UserGroupData;
import com.bpel4mobile.internal.service.MobileRequestDispatcher;

@Controller
@RequestMapping("bpel4mobile/rest/")
public class MobileRequestController {
	
	@Autowired
	private MobileRequestDispatcher mobileRequestDispatcher;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@PostConstruct
	public void postConstructor(){
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	private UserData getTestUserData(){
		UserData user = new UserData();
		user.setUsername("Tomasz");
		UserGroupData userGroupData = new UserGroupData();
		userGroupData.setName("projectManagers");
		userGroupData.getArguments().put("username", "Tomasz");
		user.getGroups().add(userGroupData);
		return user;
	}
	
	@RequestMapping(value="tasks")
	@ResponseBody
	public String getTaskForUser() throws JsonGenerationException, JsonMappingException, IOException{
		
		UserData user = getTestUserData();
		
		return mapper.writeValueAsString(mobileRequestDispatcher.findUserTasks(user));
	}
	

	@RequestMapping(value="clime", method=RequestMethod.POST)
	@ResponseBody
	public void clime(@RequestBody String requestBody) {
		
		TaskRequest request;
		try {
			request = mapper.readValue(requestBody, TaskRequest.class);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		UserData user = getTestUserData();
		
		mobileRequestDispatcher.clime(request.getTaskName(), request.getTaskUUID(), user);
	}
	
	@RequestMapping(value="release", method=RequestMethod.POST)
	@ResponseBody
	public void release(@RequestBody String requestBody) {
		System.out.println(requestBody);
		TaskRequest request;
		try {
			request = mapper.readValue(requestBody, TaskRequest.class);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		UserData user = getTestUserData();
		
		mobileRequestDispatcher.release(request.getTaskName(), request.getTaskUUID(), user);
	}
	
	@RequestMapping(value="resolve", method=RequestMethod.POST)
	@ResponseBody
	public void resolve(@RequestBody String requestBody) {
		
		ResolveTaskRequest request;
		try {
			request = mapper.readValue(requestBody, ResolveTaskRequest.class);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		UserData user = getTestUserData();
		
		mobileRequestDispatcher.resolve(request.getTaskName(), request.getTaskUUID(), request.getResult(), user);
	}
	
}
