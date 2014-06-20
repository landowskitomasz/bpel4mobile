package com.bpel4mobile.internal.definition;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Deadlines {
	
	private List<Deadline> startDeadlines;
	
	private List<Deadline> completionDeadlines;

    @XmlElement(name="startDeadline", namespace=WS_HT_Namespace.URL)
	public List<Deadline> getStartDeadlines() {
		return startDeadlines;
	}

	public void setStartDeadlines(List<Deadline> startDeadlines) {
		this.startDeadlines = startDeadlines;
	}

    @XmlElement(name="completionDeadline", namespace=WS_HT_Namespace.URL)
	public List<Deadline> getCompletionDeadlines() {
		return completionDeadlines;
	}

	public void setCompletionDeadlines(List<Deadline> completionDeadlines) {
		this.completionDeadlines = completionDeadlines;
	}
}
