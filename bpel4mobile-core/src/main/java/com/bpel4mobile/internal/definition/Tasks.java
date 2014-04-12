package com.bpel4mobile.internal.definition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


public class Tasks {

    @XmlElement(name="task", namespace=WS_HT_Namespace.URL, required = true)
    protected List<TaskDefinition> task;

    public List<TaskDefinition> getTask() {
        if (task == null) {
            task = new ArrayList<TaskDefinition>();
        }
        return this.task;
    }

}
