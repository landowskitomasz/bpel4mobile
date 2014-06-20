package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PeopleAssignments {

	private List<PeopleAssignment> potentialOwners;
	
	private List<PeopleAssignment> recipients;

	@XmlElement(name="potentialOwners", namespace = WS_HT_Namespace.URL)
	public List<PeopleAssignment> getPotentialOwners() {
		if (potentialOwners == null) {
        	potentialOwners = new ArrayList<PeopleAssignment>();
        }
		return potentialOwners;
	}

	public void setPotentialOwners(List<PeopleAssignment> potentialOwners) {
		this.potentialOwners = potentialOwners;
	}

	@XmlElement(name="recipients", namespace = WS_HT_Namespace.URL)
	public List<PeopleAssignment> getRecipients() {
		if (recipients == null) {
			recipients = new ArrayList<PeopleAssignment>();
        }
		return recipients;
	}

	public void setRecipients(List<PeopleAssignment> recipients) {
		this.recipients = recipients;
	}

}
