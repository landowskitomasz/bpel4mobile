package com.bpel4mobile.hotel.android.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Task {

    public static final String UUID = "uuid";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getClimeDate() {
        return climeDate;
    }

    public void setClimeDate(Date climeDate) {
        this.climeDate = climeDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }


    public enum TaskState {
        ready, climed, completed
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
	private String uuid;

	@DatabaseField(canBeNull = false)
	private TaskName name;

    @DatabaseField(canBeNull = false)
    private TaskState state;

    @DatabaseField(canBeNull = true)
    private Date createDate;

    @DatabaseField(canBeNull = true)
    private Date climeDate;

    @DatabaseField(canBeNull = true)
    private int priority;

    private String assignee;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, canBeNull = false)
    private Request request;

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public TaskName getName() {
		return name;
	}

	public void setName(TaskName name) {
		this.name = name;
	}

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
