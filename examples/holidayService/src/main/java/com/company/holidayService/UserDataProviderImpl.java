package com.company.holidayService;

import org.springframework.stereotype.Service;

import com.bpel4mobile.internal.bean.UserData;
import com.bpel4mobile.internal.bean.UserGroupData;
import com.bpel4mobile.ws.service.AbstractUserDataProvider;

@Service
public class UserDataProviderImpl extends AbstractUserDataProvider {

	@Override
	public boolean authenticate(String username, String password) {
		return "Tomasz".equals(username) && "password".equals(password);
	}

	@Override
	public UserData getUserData(String username, String password) {
		
		UserData user = new UserData();
		user.setUsername("Tomasz");
		UserGroupData userGroupData = new UserGroupData();
		userGroupData.setName("projectManagers");
		userGroupData.getArguments().put("username", "Tomasz");
		user.getGroups().add(userGroupData);
		return user;
	}

}
