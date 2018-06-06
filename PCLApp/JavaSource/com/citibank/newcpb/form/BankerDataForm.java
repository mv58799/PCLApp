package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.vo.OfficerBankerVO;

public class BankerDataForm extends BaseForm implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterEmployeeSOEID;
	private String filterEmployeeName;

	
	private ArrayList<OfficerBankerVO> resultListOfficerBanker;
	
	private OfficerBankerVO selectRegisterOfficerBanker;	
	
	
	private String selectBankerEmployeeSOEID;

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
	

	

	public String getFilterEmployeeSOEID() {
		return filterEmployeeSOEID;
	}
	public void setFilterEmployeeSOEID(String filterEmployeeSOEID) {
		this.filterEmployeeSOEID = filterEmployeeSOEID;
	}
	public String getFilterEmployeeName() {
		return filterEmployeeName;
	}
	public void setFilterEmployeeName(String filterEmployeeName) {
		this.filterEmployeeName = filterEmployeeName;
	}
	public String getSelectBankerEmployeeSOEID() {
		return selectBankerEmployeeSOEID;
	}
	public void setSelectBankerEmployeeSOEID(String selectBankerEmployeeSOEID) {
		this.selectBankerEmployeeSOEID = selectBankerEmployeeSOEID;
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
	public ArrayList<OfficerBankerVO> getResultListOfficerBanker() {
		return resultListOfficerBanker;
	}
	public void setResultListOfficerBanker(
			ArrayList<OfficerBankerVO> resultListOfficerBanker) {
		this.resultListOfficerBanker = resultListOfficerBanker;
	}
	public OfficerBankerVO getSelectRegisterOfficerBanker() {
		return selectRegisterOfficerBanker;
	}
	public void setSelectRegisterOfficerBanker(
			OfficerBankerVO selectRegisterOfficerBanker) {
		this.selectRegisterOfficerBanker = selectRegisterOfficerBanker;
	}


}
