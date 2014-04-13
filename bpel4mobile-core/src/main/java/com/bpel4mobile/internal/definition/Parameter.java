package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.namespace.QName;

public class Parameter {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    
    @XmlAttribute(name = "type", required = true)
    protected QName type;

    public String getName() {
        return name;
    }
    
    public void setName(String value) {
        this.name = value;
    }

    public QName getType() {
        return type;
    }

    public void setType(QName value) {
        this.type = value;
    }

}
