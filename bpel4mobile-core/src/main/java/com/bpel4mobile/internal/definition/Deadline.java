package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlElement;

public class Deadline {

	private String forCondition;
	
	private Escalation escalation;

    @XmlElement(name="for", namespace=WS_HT_Namespace.URL)
	public String getForCondition() {
		return forCondition;
	}

	public void setForCondition(String forCondition) {
		this.forCondition = forCondition;
	}

    @XmlElement(name="escalation", namespace=WS_HT_Namespace.URL)
	public Escalation getEscalation() {
		return escalation;
	}

	public void setEscalation(Escalation escalation) {
		this.escalation = escalation;
	}
}
