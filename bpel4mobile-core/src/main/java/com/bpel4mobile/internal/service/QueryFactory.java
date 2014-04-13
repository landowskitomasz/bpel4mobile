package com.bpel4mobile.internal.service;

import org.springframework.data.mongodb.core.query.Query;

import com.bpel4mobile.internal.bean.UserData;

public interface QueryFactory {

	Query tasksToPerformByUserQuery(UserData userData);
	
	Query tasksInProgresByUserQuery(UserData userData);
}
