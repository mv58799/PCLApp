package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.AcctEgVO;
import com.citibank.newcpb.vo.EgVO;

public class AccountComplementDataForm extends BaseForm implements Serializable {
	
	private String selectAccountId;
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterNumberAccount;
	private String filterNumberCpfCnpj;
	private String filterAccountType;
	
	private ArrayList<AcctCmplVO> resultListAcct;
	
	private ArrayList<ResultTableBean> accountTypeValues;
	private ArrayList<ResultTableBean> accountClosingReasonValues;
	private ArrayList<ResultTableBean> accountTypeRDIPValues;
	
	
	private AcctCmplVO selectRegisterAccountComplement;	
	
	private String filterNumberEM;
	private String filterNumberER;
	private String filterNumberEG;
	
	
	private String selectRegisterAcctNbr;
	private String selectRegisterCpfCnpjNbr;
	private String selectRegisterAccountType;
	private String selectRegisterRiskLevelCode;
	
	private AcctEgVO acctEgVO;
	
	private boolean hasAccountAss;
	private boolean hasEmList;
	
	private String filterNameCustomer;
	
	private String selectRegisterID;	
	private EgVO selectRegister;	
	private ArrayList<AcctEgVO> accountAssociationList;
	
	
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
	public String getFilterNumberCpfCnpj() {
		return filterNumberCpfCnpj;
	}
	public void setFilterNumberCpfCnpj(String filterNumberCpfCnpj) {
		this.filterNumberCpfCnpj = filterNumberCpfCnpj;
	}
	public String getFilterAccountType() {
		return filterAccountType;
	}
	public void setFilterAccountType(String filterAccountType) {
		this.filterAccountType = filterAccountType;
	}
	public ArrayList<AcctCmplVO> getResultListAcct() {
		return resultListAcct;
	}
	public void setResultListAcct(ArrayList<AcctCmplVO> resultListAcct) {
		this.resultListAcct = resultListAcct;
	}
	public String getSelectRegisterAcctNbr() {
		return selectRegisterAcctNbr;
	}
	public void setSelectRegisterAcctNbr(String selectRegisterAcctNbr) {
		this.selectRegisterAcctNbr = selectRegisterAcctNbr;
	}
	public String getSelectRegisterCpfCnpjNbr() {
		return selectRegisterCpfCnpjNbr;
	}
	public void setSelectRegisterCpfCnpjNbr(String selectRegisterCpfCnpjNbr) {
		this.selectRegisterCpfCnpjNbr = selectRegisterCpfCnpjNbr;
	}
	public String getSelectRegisterAccountType() {
		return selectRegisterAccountType;
	}
	public void setSelectRegisterAccountType(String selectRegisterAccountType) {
		this.selectRegisterAccountType = selectRegisterAccountType;
	}
	public AcctCmplVO getSelectRegisterAccountComplement() {
		return selectRegisterAccountComplement;
	}
	public void setSelectRegisterAccountComplement(
			AcctCmplVO selectRegisterAccountComplement) {
		this.selectRegisterAccountComplement = selectRegisterAccountComplement;
	}
	public ArrayList<ResultTableBean> getAccountTypeValues() {
		return accountTypeValues;
	}
	public void setAccountTypeValues(ArrayList<ResultTableBean> accountTypeValues) {
		this.accountTypeValues = accountTypeValues;
	}
	public ArrayList<ResultTableBean> getAccountClosingReasonValues() {
		return accountClosingReasonValues;
	}
	public void setAccountClosingReasonValues(
			ArrayList<ResultTableBean> accountClosingReasonValues) {
		this.accountClosingReasonValues = accountClosingReasonValues;
	}
	public boolean isFromApprove() {
		return fromApprove;
	}
	public void setFromApprove(boolean fromApprove) {
		this.fromApprove = fromApprove;
	}
	
	public ArrayList<ResultTableBean> getAccountTypeRDIPValues() {
		return accountTypeRDIPValues;
	}
	public void setAccountTypeRDIPValues(ArrayList<ResultTableBean> accountTypeRDIPValues) {
		this.accountTypeRDIPValues = accountTypeRDIPValues;
	}
	public String getSelectRegisterRiskLevelCode() {
		return selectRegisterRiskLevelCode;
	}
	public void setSelectRegisterRiskLevelCode(String selectRegisterRiskLevelCode) {
		this.selectRegisterRiskLevelCode = selectRegisterRiskLevelCode;
	}
	
	
}
