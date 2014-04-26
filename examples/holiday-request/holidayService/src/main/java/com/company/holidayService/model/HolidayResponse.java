package com.company.holidayService.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result", namespace="http://bpel4mobile.holiday.process")
public class HolidayResponse {
	
	private Boolean approved = true;

	@XmlElement(name="Approved", namespace="http://bpel4mobile.holiday.process")
	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

}
