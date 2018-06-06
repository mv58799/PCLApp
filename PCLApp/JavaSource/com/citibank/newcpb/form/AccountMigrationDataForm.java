package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.vo.AcctMgrtVO;

public class AccountMigrationDataForm extends BaseForm implements Serializable {
	
	private String selectAccountId;
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterAccountCitiBankNumber;
	private String filterAccountCustodiaNumber;
	private String filterAccountCustodiaCpfCnpjNumber;
	private String filterCustomerNumber;
	private String filterEmNumber;
	
	private ArrayList<AcctMgrtVO> resultListAcctMgrt;
	
	private AcctMgrtVO selectRegisterAccountMigrate;	
	
	
	private String selectRegisterAccountGrbNumber;
	private String selectRegisterAccountCustodiaNumber;
	private String selectRegisterAccountCustodiaCpfCnpjNumber;
	
	
	private boolean fromApprove;
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isEdit;
	private String idDiffList;
	private boolean showConfirmInsertPopup;
	private boolean showConfirmRedirectPageRiskPopup;
	private boolean showConfirmDeletePopup;
	private boolean approvedDisapproved;
	private boolean isUpdateFromApprove;
	

	
	public String getSelectRegisterAccountGrbNumber() {
		return selectRegisterAccountGrbNumber;
	}
	public void setSelectRegisterAccountGrbNumber(
			String selectRegisterAccountGrbNumber) {
		this.selectRegisterAccountGrbNumber = selectRegisterAccountGrbNumber;
	}
	public String getSelectRegisterAccountCustodiaNumber() {
		return selectRegisterAccountCustodiaNumber;
	}
	public void setSelectRegisterAccountCustodiaNumber(
			String selectRegisterAccountCustodiaNumber) {
		this.selectRegisterAccountCustodiaNumber = selectRegisterAccountCustodiaNumber;
	}
	public String getSelectRegisterAccountCustodiaCpfCnpjNumber() {
		return selectRegisterAccountCustodiaCpfCnpjNumber;
	}
	public void setSelectRegisterAccountCustodiaCpfCnpjNumber(
			String selectRegisterAccountCustodiaCpfCnpjNumber) {
		this.selectRegisterAccountCustodiaCpfCnpjNumber = selectRegisterAccountCustodiaCpfCnpjNumber;
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

	public String getScreenTitle() {
		return screenTitle;
	}
	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}

	public boolean isShowConfirmInsertPopup() {
		return showConfirmInsertPopup;
	}
	public void setShowConfirmInsertPopup(boolean showConfirmInsertPopup) {
		this.showConfirmInsertPopup = showConfirmInsertPopup;
	}
	public boolean getShowConfirmRedirectPageRiskPopup() {
		return showConfirmRedirectPageRiskPopup;
	}
	public void setShowConfirmRedirectPageRiskPopup(boolean showConfirmRedirectPageRiskPopup) {
		this.showConfirmRedirectPageRiskPopup = showConfirmRedirectPageRiskPopup;
	}

	public String getSelectAccountId() {
		return selectAccountId;
	}
	public void setSelectAccountId(String selectAccountId) {
		this.selectAccountId = selectAccountId;
	}

	public boolean isEdit() {
		return isEdit;
	}
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	
	public boolean isApprovedDisapproved() {
		return approvedDisapproved;
	}
	public void setApprovedDisapproved(boolean approvedDisapproved) {
		this.approvedDisapproved = approvedDisapproved;
	}
	public boolean isShowConfirmDeletePopup() {
		return showConfirmDeletePopup;
	}
	public void setShowConfirmDeletePopup(boolean showConfirmDeletePopup) {
		this.showConfirmDeletePopup = showConfirmDeletePopup;
	}
	public boolean isUpdateFromApprove() {
		return isUpdateFromApprove;
	}
	public void setUpdateFromApprove(boolean isUpdateFromApprove) {
		this.isUpdateFromApprove = isUpdateFromApprove;
	}

	public boolean isFromApprove() {
		return fromApprove;
	}
	public void setFromApprove(boolean fromApprove) {
		this.fromApprove = fromApprove;
	}
	public String getFilterAccountCitiBankNumber() {
		return filterAccountCitiBankNumber;
	}
	public void setFilterAccountCitiBankNumber(String filterAccountCitiBankNumber) {
		this.filterAccountCitiBankNumber = filterAccountCitiBankNumber;
	}
	public String getFilterAccountCustodiaNumber() {
		return filterAccountCustodiaNumber;
	}
	public void setFilterAccountCustodiaNumber(String filterAccountCustodiaNumber) {
		this.filterAccountCustodiaNumber = filterAccountCustodiaNumber;
	}

	public String getFilterCustomerNumber() {
		return filterCustomerNumber;
	}
	public void setFilterCustomerNumber(String filterCustomerNumber) {
		this.filterCustomerNumber = filterCustomerNumber;
	}
	public String getFilterEmNumber() {
		return filterEmNumber;
	}
	public void setFilterEmNumber(String filterEmNumber) {
		this.filterEmNumber = filterEmNumber;
	}
	public ArrayList<AcctMgrtVO> getResultListAcctMgrt() {
		return resultListAcctMgrt;
	}
	public void setResultListAcctMgrt(ArrayList<AcctMgrtVO> resultListAcctMgrt) {
		this.resultListAcctMgrt = resultListAcctMgrt;
	}
	public AcctMgrtVO getSelectRegisterAccountMigrate() {
		return selectRegisterAccountMigrate;
	}
	public void setSelectRegisterAccountMigrate(
			AcctMgrtVO selectRegisterAccountMigrate) {
		this.selectRegisterAccountMigrate = selectRegisterAccountMigrate;
	}
	public String getFilterAccountCustodiaCpfCnpjNumber() {
		return filterAccountCustodiaCpfCnpjNumber;
	}
	public void setFilterAccountCustodiaCpfCnpjNumber(
			String filterAccountCustodiaCpfCnpjNumber) {
		this.filterAccountCustodiaCpfCnpjNumber = filterAccountCustodiaCpfCnpjNumber;
	}
}
