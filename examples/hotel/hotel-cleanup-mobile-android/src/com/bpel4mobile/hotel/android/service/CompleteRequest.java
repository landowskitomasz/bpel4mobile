package com.bpel4mobile.hotel.android.service;


import com.bpel4mobile.hotel.android.model.TaskName;

public class CompleteRequest {

    private TaskName taskName;

    private String taskUUID;

    private String result;

    public TaskName getTaskName() {
        return taskName;
    }

    public void setTaskName(TaskName taskName) {
        this.taskName = taskName;
    }

    public String getTaskUUID() {
        return taskUUID;
    }

    public void setTaskUUID(String taskUUID) {
        this.taskUUID = taskUUID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
