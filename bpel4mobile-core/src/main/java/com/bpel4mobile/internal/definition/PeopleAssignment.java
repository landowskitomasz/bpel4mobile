package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlElement;

public class PeopleAssignment{

    private From from;
   
    @XmlElement(name="from", namespace=WS_HT_Namespace.URL)
    public From getFrom() {
        return from;
    }

    public void setFrom(From value) {
        this.from = value;
    }
}
