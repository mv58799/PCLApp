package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.newcpb.vo.EgVO;

public class AssociationAccountForm extends BaseForm implements Serializable {
	
	private String selectAccountId;
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterNumberEM;
	private String filterNumberER;
	private String filterNumberEG;
	private String filterNumberAccount;
	
	private String selectNumberER;
	private String selectNumberEG;
	private String selectNumberAccount;
	
	private AcctEgVO acctEgVO;
	
	private boolean hasAccountAss;
	private boolean hasEmList;
	
	private String filterNameCustomer;
	
	private String selectRegisterID;	
	private EgVO selectRegister;	
	private ArrayList<AcctEgVO> accountAssociationList;
	private ArrayList<AcctEgVO> accountAssociationListRemoved;
	
	
	private AcctEgVO selectRegisterForAprover;	
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isEdit;
	private String idDiffList;
	private boolean showConfirmInsertPopup;
	private boolean showConfirmRedirectPageRiskPopup;
	private boolean showConfirmDeletePopup;
	private boolean approvedDisapproved;
	private boolean isUpdateFromApprove;
	private boolean isOpenUpdateAssociationEgAccount;
	

	
	public ArrayList<AcctEgVO> getAccountAssociationList() {
		return accountAssociationList;
	}
	public void setAccountAssociationList(ArrayList<AcctEgVO> accountAssociationList) {
		this.accountAssociationList = accountAssociationList;
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
	public String getFilterNumberER() {
		return filterNumberER;
	}
	public void setFilterNumberER(String filterNumberER) {
		this.filterNumberER = filterNumberER;
	}
	public String getFilterNumberEG() {
		return filterNumberEG;
	}
	public void setFilterNumberEG(String filterNumberEG) {
		this.filterNumberEG = filterNumberEG;
	}
	public String getSelectRegisterID() {
		return selectRegisterID;
	}
	public void setSelectRegisterID(String selectRegisterID) {
		this.selectRegisterID = selectRegisterID;
	}
	public String getScreenTitle() {
		return screenTitle;
	}
	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}
	public EgVO getSelectRegister() {
		return selectRegister;
	}
	public void setSelectRegister(EgVO selectRegister) {
		this.selectRegister = selectRegister;
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
	public String getFilterNumberAccount() {
		return filterNumberAccount;
	}
	public void setFilterNumberAccount(String filterNumberAccount) {
		this.filterNumberAccount = filterNumberAccount;
	}
	public String getFilterNameCustomer() {
		return filterNameCustomer;
	}
	public void setFilterNameCustomer(String filterNameCustomer) {
		this.filterNameCustomer = filterNameCustomer;
	}
	public boolean getHasEmList() {
		return hasEmList;
	}
	public void setHasEmList(boolean hasEmList) {
		this.hasEmList = hasEmList;
	}
	public AcctEgVO getAcctEgVO() {
		return acctEgVO;
	}
	public void setAcctEgVO(AcctEgVO acctEgVO) {
		this.acctEgVO = acctEgVO;
	}
	public String getSelectAccountId() {
		return selectAccountId;
	}
	public void setSelectAccountId(String selectAccountId) {
		this.selectAccountId = selectAccountId;
	}
	public boolean getHasAccountAss() {
		
		if(getAccountAssociationList()!=null && getAccountAssociationList().size()>0){
			hasAccountAss = true;
		}else{
			hasAccountAss = false;
		}
		return hasAccountAss;
	}
	public void setHasAccountAss(boolean hasAccountAss) {
		this.hasAccountAss = hasAccountAss;
	}
	public String getSelectNumberER() {
		return selectNumberER;
	}
	public void setSelectNumberER(String selectNumberER) {
		this.selectNumberER = selectNumberER;
	}
	public String getSelectNumberEG() {
		return selectNumberEG;
	}
	public void setSelectNumberEG(String selectNumberEG) {
		this.selectNumberEG = selectNumberEG;
	}
	public String getSelectNumberAccount() {
		return selectNumberAccount;
	}
	public void setSelectNumberAccount(String selectNumberAccount) {
		this.selectNumberAccount = selectNumberAccount;
	}
	public boolean isEdit() {
		return isEdit;
	}
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	public AcctEgVO getSelectRegisterForAprover() {
		return selectRegisterForAprover;
	}
	public void setSelectRegisterForAprover(AcctEgVO selectRegisterForAprover) {
		this.selectRegisterForAprover = selectRegisterForAprover;
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
	public ArrayList<AcctEgVO> getAccountAssociationListRemoved() {
		return accountAssociationListRemoved;
	}
	public void setAccountAssociationListRemoved(ArrayList<AcctEgVO> accountAssociationListRemoved) {
		this.accountAssociationListRemoved = accountAssociationListRemoved;
	}
	public boolean isOpenUpdateAssociationEgAccount() {
		return isOpenUpdateAssociationEgAccount;
	}
	public void setOpenUpdateAssociationEgAccount(
			boolean isOpenUpdateAssociationEgAccount) {
		this.isOpenUpdateAssociationEgAccount = isOpenUpdateAssociationEgAccount;
	}
}
