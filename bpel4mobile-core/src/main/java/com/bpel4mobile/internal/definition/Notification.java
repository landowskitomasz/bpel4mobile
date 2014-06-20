package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Notification {

	private String name;
	
	private PeopleAssignments peopleAssignments;
	

    @XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @XmlElement(name="peopleAssignments", namespace=WS_HT_Namespace.URL)
	public PeopleAssignments getPeopleAssignments() {
		return peopleAssignments;
	}

	public void setPeopleAssignments(PeopleAssignments peopleAssignments) {
		this.peopleAssignments = peopleAssignments;
	}
}
