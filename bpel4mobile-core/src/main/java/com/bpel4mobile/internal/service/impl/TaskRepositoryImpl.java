package com.bpel4mobile.internal.service.impl;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.model.Task;
import com.bpel4mobile.internal.service.TaskRepository;
import com.google.common.base.Preconditions;
import com.mongodb.MongoClient;

@Service
public class TaskRepositoryImpl implements TaskRepository {

	private MongoTemplate mongoTemplate;
	
	@Value("${bpel4mobile.mongodb.database}")
	private String databaseName;
	
	@Value("${bpel4mobile.mongodb.username}")
	private String username;

	@Value("${bpel4mobile.mongodb.password}")
	private String password;

	@Value("${bpel4mobile.mongodb.host}")
	private String host;
	
	@PostConstruct
	public void createMongoTemplate(){
		
		UserCredentials userCredentials = new UserCredentials(username, password);
		try {
			MongoDbFactory dbFactory = new SimpleMongoDbFactory(new MongoClient(host), databaseName, userCredentials);
			this.mongoTemplate = new MongoTemplate(dbFactory);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Unable to establish mongo db connection. Wrong database parameters.", e);
		}
		Set<String> collections = mongoTemplate.getCollectionNames();
		if(!collections.contains(Task.COLLECTION_NAME)){
			mongoTemplate.createCollection(Task.class);
		}
	}

	@Override
	public <T, R> void createTask(Task<T, R> task) {
		Task<?, ?> persistantTask = findOne(task.getName(), task.getUuid());
		Preconditions.checkArgument(persistantTask == null , "Task already exists.");
		
		mongoTemplate.save(task, Task.COLLECTION_NAME);
	}

	@Override
	public <T, R> void updateTask(Task<T, R> task) {
		Task<?, ?> persistantTask = findOne(task.getName(), task.getUuid());
		Preconditions.checkArgument(persistantTask != null , "Task not exists.");

		mongoTemplate.save(task, Task.COLLECTION_NAME);
	}

	@Override
	public Task<?, ?> findOne(String name, String uuid) {
		Query query = new Query(Criteria.where(Task.NAME_FIELD).is(name).and(Task.UUID_FIELD).is(uuid));
		return mongoTemplate.findOne(query, Task.class);
	}

	@Override
	public List<Task> find(Query query) {
		return mongoTemplate.find(query, Task.class);
	}

}
