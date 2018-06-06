package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.vo.StatusCpfCnpjVO;

public class StatusCpfCnpjForm extends BaseForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterNumberEM;
	private String filterCpfCnpj;
	private String filterName;
	private String filterStatus;
	private String filterMonthYear;
	
	private StatusCpfCnpjVO selectedRegister;
	private ArrayList<StatusCpfCnpjVO> resultList;
	
	private boolean isUpdate;
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isFromApprove;
	private String idDiffList;
	
	//Combos da tela 
	private ArrayList<ResultTableBean> statusValues;
	
	public ArrayList<StatusCpfCnpjVO> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<StatusCpfCnpjVO> resultList) {
		this.resultList = resultList;
	}
	public boolean isApprove() {
		return isApprove;
	}
	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}
	public boolean isOnlyView() {
		return isOnlyView;
	}
	public void setOnlyView(boolean isOnlyView) {
		this.isOnlyView = isOnlyView;
	}
	public String getIdDiffList() {
		return idDiffList;
	}
	public void setIdDiffList(String idDiffList) {
		this.idDiffList = idDiffList;
	}
	public String getFilterNumberEM() {
		return filterNumberEM;
	}
	public void setFilterNumberEM(String filterNumberEM) {
		this.filterNumberEM = filterNumberEM;
	}
	public String getScreenTitle() {
		return screenTitle;
	}
	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}
	public boolean isUpdate() {
		return isUpdate || isOnlyView;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getFilterCpfCnpj() {
		return filterCpfCnpj;
	}
	public void setFilterCpfCnpj(String filterCpfCnpj) {
		this.filterCpfCnpj = filterCpfCnpj;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public boolean isFromApprove() {
		return isFromApprove;
	}
	public void setFromApprove(boolean isFromApprove) {
		this.isFromApprove = isFromApprove;
	}
	public String getFilterStatus() {
		return filterStatus;
	}
	public void setFilterStatus(String filterStatus) {
		this.filterStatus = filterStatus;
	}
	public String getFilterMonthYear() {
		return filterMonthYear;
	}
	public void setFilterMonthYear(String filterMonthYear) {
		this.filterMonthYear = filterMonthYear;
	}
	public ArrayList<ResultTableBean> getStatusValues() {
		return statusValues;
	}
	public void setStatusValues(ArrayList<ResultTableBean> statusValues) {
		this.statusValues = statusValues;
	}
	public StatusCpfCnpjVO getSelectedRegister() {
		return selectedRegister;
	}
	public void setSelectedRegister(StatusCpfCnpjVO selectedRegister) {
		this.selectedRegister = selectedRegister;
	}
}
