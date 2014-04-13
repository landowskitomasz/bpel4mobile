package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class LogicalPeopleGroup{

    protected List<Parameter> parameter;
    
    protected String name;
    
    @XmlElement(name="parameter", namespace=WS_HT_Namespace.URL)
    public List<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return this.parameter;
    }

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

}
