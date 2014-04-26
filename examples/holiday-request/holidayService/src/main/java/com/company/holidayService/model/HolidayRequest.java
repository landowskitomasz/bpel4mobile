package com.company.holidayService.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bpel4mobile.ws.service.B4MNamespace;


@XmlRootElement(name="request", namespace= B4MNamespace.URL)
public class HolidayRequest {
	
	private String holiday;
	 
	private String employee;

	@XmlElement(name="Holiday",namespace=B4MNamespace.URL)
	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	@XmlElement(name="Employee", namespace=B4MNamespace.URL)
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

}
