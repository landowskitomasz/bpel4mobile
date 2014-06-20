package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Escalation {

	private String condition;
	
	private String name;
	
	private Notification notification;
	
	private RaisePriority raisePriority;

    @XmlElement(name="condition", namespace=WS_HT_Namespace.URL)
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

    @XmlElement(name="notification", namespace=WS_HT_Namespace.URL)
	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

    @XmlElement(name="raisePriority", namespace=WS_HT_Namespace.URL)
	public RaisePriority getRaisePriority() {
		return raisePriority;
	}

	public void setRaisePriority(RaisePriority raisePriority) {
		this.raisePriority = raisePriority;
	}

    @XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
