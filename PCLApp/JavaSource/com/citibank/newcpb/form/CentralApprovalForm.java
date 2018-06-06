package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.vo.RegisterMovPendingToApproveVO;

public class CentralApprovalForm extends BaseForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userIdFilter;	
	private String moduleProcessTextFilter;	
	private String selectedCode;
	private ArrayList<RegisterMovPendingToApproveVO> resultList;
	private ArrayList<String> moduleProcessDomainList;

	public String getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public String getUserIdFilter() {
		return userIdFilter;
	}

	public void setUserIdFilter(String userIdFilter) {
		this.userIdFilter = userIdFilter;
	}

	public String getModuleProcessTextFilter() {
		return moduleProcessTextFilter;
	}

	public void setModuleProcessTextFilter(String moduleProcessTextFilter) {
		this.moduleProcessTextFilter = moduleProcessTextFilter;
	}

	public ArrayList<RegisterMovPendingToApproveVO> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<RegisterMovPendingToApproveVO> resultList) {
		this.resultList = resultList;
	}

	public ArrayList<String> getModuleProcessDomainList() {
		return moduleProcessDomainList;
	}

	public void setModuleProcessDomainList(ArrayList<String> moduleProcessDomainList) {
		this.moduleProcessDomainList = moduleProcessDomainList;
	}
}
