package com.bpel4mobile.internal.service;

import com.bpel4mobile.internal.bean.TaskResult;

public interface TaskResultResolver {

	public void registerTask(final String name, final Class<?>[] resultTypes);
	
	public <R> void resolveTask(final String name, final TaskResult<R> result);
}
