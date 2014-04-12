package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TaskDefinition {
    
    protected String priority;
    
    protected PeopleAssignments peopleAssignments;
    
    protected Deadlines deadlines;
   
    protected String name;
  
    @XmlElement(name="priority", namespace=WS_HT_Namespace.URL)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String value) {
        this.priority = value;
    }

    @XmlElement(name="peopleAssignments", namespace=WS_HT_Namespace.URL)
    public PeopleAssignments getPeopleAssignments() {
        return peopleAssignments;
    }

    public void setPeopleAssignments(PeopleAssignments value) {
        this.peopleAssignments = value;
    }

    @XmlElement(name="deadlines", namespace=WS_HT_Namespace.URL)
    public Deadlines getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(Deadlines value) {
        this.deadlines = value;
    }

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
