package com.bpel4mobile.internal.service;

import com.bpel4mobile.internal.bean.UserData;

public interface QueryFactory {

	String restrictTasksByUserQuery(UserData userData);
}
