package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class OfficerBankerVO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String employeeSOEID;
	private String employeeName;
	private String supervisorSOEID;
	private String supervisorName;
	private String associateSOEID;
	private String associateName;
	private Date lastAuthDate; 
	private String lastAuthUser; 
	private Date lastUpdDate; 
	private String lastUpdUser;
	private String recStatCode; 
	private TableTypeEnum tableOrigin; 
	
	
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
	public String getSupervisorSOEID() {
		return supervisorSOEID;
	}
	public void setSupervisorSOEID(String supervisorSOEID) {
		this.supervisorSOEID = supervisorSOEID;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getAssociateSOEID() {
		return associateSOEID;
	}
	public void setAssociateSOEID(String associateSOEID) {
		this.associateSOEID = associateSOEID;
	}
	public String getAssociateName() {
		return associateName;
	}
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}
	public Date getLastAuthDate() {
		return lastAuthDate;
	}
	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	public String getLastAuthUser() {
		return lastAuthUser;
	}
	public void setLastAuthUser(String lastAuthUser) {
		this.lastAuthUser = lastAuthUser;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}
	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}
	
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
		}else{
			return "";
		}
	}
	
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("U")){
				return "Alteração";
			}else if(recStatCode.equals("A")){
				return "Inclusão";
			}else if(recStatCode.equals("I")){
				return "Exclusão";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public ArrayList<String> equals(OfficerBankerVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.employeeSOEID != null && other.employeeSOEID != null) {
				if (!this.employeeSOEID.equals(other.employeeSOEID)) {
					idDiffList.add("employeeSOEID");
				}
			} else if ((this.employeeSOEID == null && other.employeeSOEID != null) || (other.employeeSOEID == null && this.employeeSOEID != null)) {
				idDiffList.add("employeeSOEID");
			}
			
			if (this.employeeName != null && other.employeeName != null) {
				if (!this.employeeName.equals(other.employeeName)) {
					idDiffList.add("employeeName");
				}
			} else if ((this.employeeName == null && other.employeeName != null) || (other.employeeName == null && this.employeeName != null)) {
				idDiffList.add("employeeName");
			}
			
			if (this.supervisorSOEID != null && other.supervisorSOEID != null) {
				if (!this.supervisorSOEID.equals(other.supervisorSOEID)) {
					idDiffList.add("supervisorSOEID");
				}
			} else if ((this.supervisorSOEID == null && other.supervisorSOEID != null) || (other.supervisorSOEID == null && this.supervisorSOEID != null)) {
				idDiffList.add("supervisorSOEID");
			}
			
			if (this.supervisorName != null && other.supervisorName != null) {
				if (!this.supervisorName.equals(other.supervisorName)) {
					idDiffList.add("supervisorName");
				}
			} else if ((this.supervisorName == null && other.supervisorName != null) || (other.supervisorName == null && this.supervisorName != null)) {
				idDiffList.add("supervisorName");
			}
			
			
			if (this.associateSOEID != null && other.associateSOEID != null) {
				if (!this.associateSOEID.equals(other.associateSOEID)) {
					idDiffList.add("associateSOEID");
				}
			} else if ((this.associateSOEID == null && other.associateSOEID != null) || (other.associateSOEID == null && this.associateSOEID != null)) {
				idDiffList.add("associateSOEID");
			}
			
			if (this.associateName != null && other.associateName != null) {
				if (!this.associateName.equals(other.associateName)) {
					idDiffList.add("associateName");
				}
			} else if ((this.associateName == null && other.associateName != null) || (other.associateName == null && this.associateName != null)) {
				idDiffList.add("associateName");
			}
			

				
			
			
		}else{
			idDiffList.add("employeeSOEID");
			idDiffList.add("employeeName");
			idDiffList.add("supervisorSOEID");	
			idDiffList.add("supervisorName");	
			idDiffList.add("associateSOEID");	
			idDiffList.add("associateName");	
			
		}
		return idDiffList;
	}
	
	
}
