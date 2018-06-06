package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.vo.AuthorizationPersonVO;

public class RegisterAuthorizedForm extends BaseForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterNumberEM;
	private String filterCpfCnpj;
	private String filterName;
	private String filterAccountList;
	
	private String selectedEmNbr;	
	private String selectedAcctNbr;	
	private String selectedAuthInd;
	private AuthorizationPersonVO selectRegister;	
	private ArrayList<AuthorizationPersonVO> resultList;
	
	private boolean isUpdate;
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isFromApprove;
	private String idDiffList;
	
	private boolean hasAccountAss;
	private boolean showConfirmDeletePopup;
	
	public ArrayList<AuthorizationPersonVO> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<AuthorizationPersonVO> resultList) {
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
	public AuthorizationPersonVO getSelectRegister() {
		if(selectRegister == null){
			selectRegister = new AuthorizationPersonVO();
		}
		return selectRegister;
	}
	public void setSelectRegister(AuthorizationPersonVO selectRegister) {
		this.selectRegister = selectRegister;
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
	public String getSelectedEmNbr() {
		return selectedEmNbr;
	}
	public void setSelectedEmNbr(String selectedEmNbr) {
		this.selectedEmNbr = selectedEmNbr;
	}
	public boolean isHasAccountAss() {
		return hasAccountAss;
	}
	public void setHasAccountAss(boolean hasAccountAss) {
		this.hasAccountAss = hasAccountAss;
	}
	public String getFilterAccountList() {
		return filterAccountList;
	}
	public void setFilterAccountList(String filterAccountList) {
		this.filterAccountList = filterAccountList;
	}
	public String getSelectedAcctNbr() {
		return selectedAcctNbr;
	}
	public void setSelectedAcctNbr(String selectedAcctNbr) {
		this.selectedAcctNbr = selectedAcctNbr;
	}
	public boolean isFromApprove() {
		return isFromApprove;
	}
	public void setFromApprove(boolean isFromApprove) {
		this.isFromApprove = isFromApprove;
	}
	
	public void resetRadio(){
		if(selectRegister!=null){
			selectRegister.setAuthInd(null);
		}
	}
	public boolean isShowConfirmDeletePopup() {
		return showConfirmDeletePopup;
	}
	public void setShowConfirmDeletePopup(boolean showConfirmDeletePopup) {
		this.showConfirmDeletePopup = showConfirmDeletePopup;
	}
	/**
	 * @return the selectedAuthInd
	 */
	public String getSelectedAuthInd() {
		return selectedAuthInd;
	}
	/**
	 * @param selectedAuthInd the selectedAuthInd to set
	 */
	public void setSelectedAuthInd(String selectedAuthInd) {
		this.selectedAuthInd = selectedAuthInd;
	}
}
