package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


public class Tasks {

    @XmlElement(name="task", namespace=WS_HT_Namespace.URL, required = true)
    protected List<Task> task;

    public List<Task> getTask() {
        if (task == null) {
            task = new ArrayList<Task>();
        }
        return this.task;
    }

}
