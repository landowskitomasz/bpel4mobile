package com.bpel4mobile.escalation.internal.service.impl.data;

import com.bpel4mobile.internal.definition.PeopleAssignments;

public class NotificationEscalation extends Escalation {
	
	private PeopleAssignments peopleAssignments;
	
	private String name;
	
	public PeopleAssignments getPeopleAssignments(){
		return peopleAssignments;
	}

	public void setPeopleAssignments(PeopleAssignments peopleAssignments) {
		this.peopleAssignments = peopleAssignments;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
