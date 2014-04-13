package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.namespace.QName;

public class Parameter {

    protected String name;
    
    protected QName type;

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }
    
    public void setName(String value) {
        this.name = value;
    }

    @XmlAttribute(name = "type", required = true)
    public QName getType() {
        return type;
    }

    public void setType(QName value) {
        this.type = value;
    }

}
