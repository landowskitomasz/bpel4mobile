package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;

public class PotentialOwners {
	
    protected List<PeopleAssignment> potentialOwners;

    public List<PeopleAssignment> getPeopleAssigment() {
        if (potentialOwners == null) {
        	potentialOwners = new ArrayList<PeopleAssignment>();
        }
        return this.potentialOwners;
    }
}
