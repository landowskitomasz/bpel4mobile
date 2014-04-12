package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlElement;

public class PeopleAssignments {

	private PotentialOwners potentialOwners;

	public PotentialOwners getPotentialOwners() {
		return potentialOwners;
	}

	@XmlElement(name="potentialOwners", namespace = WS_HT_Namespace.URL)
	public void setPotentialOwners(PotentialOwners potentialOwners) {
		this.potentialOwners = potentialOwners;
	}

}
