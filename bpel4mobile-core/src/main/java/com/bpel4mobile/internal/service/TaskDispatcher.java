package com.bpel4mobile.internal.service;

public interface TaskDispatcher {

	<T, R> String createNewTask(String taskName, String callbackUrl, T request, Class<R> resultType);
}
