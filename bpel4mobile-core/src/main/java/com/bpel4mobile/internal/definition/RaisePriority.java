package com.bpel4mobile.internal.definition;

import javax.xml.bind.annotation.XmlAttribute;

public class RaisePriority {

	private int value;

    @XmlAttribute(name="value")
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
