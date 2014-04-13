package com.bpel4mobile.internal.service;

import com.bpel4mobile.internal.bean.UserData;

public interface UserDataProvider {

	UserData getUserData(String username, String password);
	 
	boolean authenticate(String username, String password);
}
