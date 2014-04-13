package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


public class LogicalPeopleGroups {

    @XmlElement(required = true)
    protected List<LogicalPeopleGroup> logicalPeopleGroup;

    public List<LogicalPeopleGroup> getLogicalPeopleGroup() {
        if (logicalPeopleGroup == null) {
            logicalPeopleGroup = new ArrayList<LogicalPeopleGroup>();
        }
        return this.logicalPeopleGroup;
    }

}
