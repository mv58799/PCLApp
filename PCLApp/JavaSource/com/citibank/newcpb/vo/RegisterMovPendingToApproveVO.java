package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.Date;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.util.FormatUtils;

public class RegisterMovPendingToApproveVO implements Serializable{	

	private static final long serialVersionUID = 1L;
	private String processCode;
	private String moduleProcessText;
	private String processText;
	private String lastUpdUserId;
	private Date lastUpdDate;
	private String recStatCode;
	
	public String getProcessCode() {
		return processCode;
	}
	public String getProcessCodeSafe() {
		if(processCode!=null){
			return processCode;
		}else{
			return "";
		}
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getModuleProcessText() {
		return moduleProcessText;
	}
	public String getModuleProcessTextSafe() {
		if(moduleProcessText!=null){
			return moduleProcessText;
		}else{
			return "";
		}
	}
	public void setModuleProcessText(String moduleProcessText) {
		this.moduleProcessText = moduleProcessText;
	}
	public String getProcessText() {
		return processText;
	}
	public void setProcessText(String processText) {
		this.processText = processText;
	}
	public String getLastUpdUserId() {
		return lastUpdUserId;
	}
	public String getLastUpdUserIdSafe() {
		if(lastUpdUserId!=null){
			return lastUpdUserId;
		}else{
			return "";
		}
	}
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public String getLastUpdDateFormated() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMM);
		}else{
			return "";
		}
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			
			if(moduleProcessText!=null && moduleProcessText.equalsIgnoreCase(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome())){
				if(recStatCode.equals("A")){
					return "Alteração";
				}else{
					return "";
				}
			}else{
				if(recStatCode.equals("U")){
					return "Alteração";
				}else if(recStatCode.equals("R") || recStatCode.equals("A")){
					return "Inclusão";
				}else if(recStatCode.equals("I")){
					return "Exclusão";
				}else{
					return "";
				}	
			}					
		}else{
			return "";
		}
	}
	public String getRecStatCodeSafe() {
		if(recStatCode!=null){
			return recStatCode;
		}else{
			return "";
		}
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
}
