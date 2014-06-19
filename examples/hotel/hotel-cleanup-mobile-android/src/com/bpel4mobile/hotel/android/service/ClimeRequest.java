package com.bpel4mobile.hotel.android.service;


import com.bpel4mobile.hotel.android.model.TaskName;

public class ClimeRequest {

    private TaskName taskName;

    private String taskUUID;


    public String getTaskUUID() {
        return taskUUID;
    }

    public void setTaskUUID(String taskUUID) {
        this.taskUUID = taskUUID;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public void setTaskName(TaskName taskName) {
        this.taskName = taskName;
    }
}
