package com.bpel4mobile.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import com.google.common.base.Preconditions;

public class TaskServiceConfig  implements InitializingBean {

	private String name;
	
	private Class<?>[] resultTypes;
	
	
	public String getName() {
		return name;
	}

	public void setName(String taskIdentifier) {
		this.name = taskIdentifier;
	}

	public Class<?>[] getResultTypes() {
		return resultTypes;
	}

	public void setResultTypes(Class<?>[] resultTypes) {
		this.resultTypes = resultTypes;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		Preconditions.checkState(StringUtils.hasText(name), "'taskIdentifier' property must be set in TaskServiceConfig.");
		Preconditions.checkState(resultTypes != null && resultTypes.length > 0, "'resultTypes' property must be set in TaskServiceConfig.");
	}
}
