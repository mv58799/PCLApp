package com.citibank.newcpb.vo;

import java.io.Serializable;

public class EmployeeRegistrationVO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String employeeSOEID;
	private String employeeName;
	
	public String getEmployeeSOEID() {
		return employeeSOEID;
	}
	public void setEmployeeSOEID(String employeeSOEID) {
		this.employeeSOEID = employeeSOEID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
