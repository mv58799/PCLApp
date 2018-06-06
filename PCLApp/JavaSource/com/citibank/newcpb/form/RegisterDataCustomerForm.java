package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;

public class RegisterDataCustomerForm extends BaseForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numberEM;
	private String numberGFCID;
	private String userIdFilter;	
	private String moduleProcessTextFilter;
	private String customerType;
	private String name;
	
	private String selectCustomeGFCID;
	
	private RegisterDataCustomerVO registerConsumer;
	private ArrayList<RegisterDataCustomerVO> resultList;
	
	
	//Combos da tela 
	private ArrayList<ResultTableBean> customerStatusValues;
	private ArrayList<ResultTableBean> customerTituloValues;
	private ArrayList<ResultTableBean> genderTypeValues;
	private ArrayList<ResultTableBean> civilStateValues;
	private ArrayList<ResultTableBean> countryValues;
	private ArrayList<ResultTableBean> emitTypeValues;
	private ArrayList<ResultTableBean> ufValues;
	private ArrayList<ResultTableBean> occupationNatureValues;
	private ArrayList<ResultTableBean> activityMainValues;
	private ArrayList<ResultTableBean> constTypeValues;
	private ArrayList<ResultTableBean> customerRoleRelationshipValues;
	
	
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isFromApprove;
	private String idDiffList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumberEM() {
		return numberEM;
	}
	public void setNumberEM(String numberEM) {
		this.numberEM = numberEM;
	}
	public String getNumberGFCID() {
		return numberGFCID;
	}
	public void setNumberGFCID(String numberGFCID) {
		this.numberGFCID = numberGFCID;
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
	public ArrayList<RegisterDataCustomerVO> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<RegisterDataCustomerVO> resultList) {
		this.resultList = resultList;
	}
	public ArrayList<ResultTableBean> getCustomerStatusValues() {
		return customerStatusValues;
	}
	public void setCustomerStatusValues(
			ArrayList<ResultTableBean> customerStatusValues) {
		this.customerStatusValues = customerStatusValues;
	}
	
	public ArrayList<ResultTableBean> getCustomerTituloValues() {
		return customerTituloValues;
	}
	public void setCustomerTituloValues(
			ArrayList<ResultTableBean> customerTituloValues) {
		this.customerTituloValues = customerTituloValues;
	}
	
	public ArrayList<ResultTableBean> getGenderTypeValues() {
		return genderTypeValues;
	}
	public void setGenderTypeValues(ArrayList<ResultTableBean> genderTypeValues) {
		this.genderTypeValues = genderTypeValues;
	}
	public ArrayList<ResultTableBean> getCivilStateValues() {
		return civilStateValues;
	}
	public void setCivilStateValues(ArrayList<ResultTableBean> civilStateValues) {
		this.civilStateValues = civilStateValues;
	}
	public ArrayList<ResultTableBean> getCountryValues() {
		return countryValues;
	}
	public void setCountryValues(ArrayList<ResultTableBean> countryValues) {
		this.countryValues = countryValues;
	}
	public ArrayList<ResultTableBean> getEmitTypeValues() {
		return emitTypeValues;
	}
	public void setEmitTypeValues(ArrayList<ResultTableBean> emitTypeValues) {
		this.emitTypeValues = emitTypeValues;
	}
	public ArrayList<ResultTableBean> getUfValues() {
		return ufValues;
	}
	public void setUfValues(ArrayList<ResultTableBean> ufValues) {
		this.ufValues = ufValues;
	}
	public ArrayList<ResultTableBean> getOccupationNatureValues() {
		return occupationNatureValues;
	}
	public void setOccupationNatureValues(
			ArrayList<ResultTableBean> occupationNatureValues) {
		this.occupationNatureValues = occupationNatureValues;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public RegisterDataCustomerVO getRegisterConsumer() {
		return registerConsumer;
	}
	public void setRegisterConsumer(RegisterDataCustomerVO registerConsumer) {
		this.registerConsumer = registerConsumer;
	}
	public ArrayList<ResultTableBean> getActivityMainValues() {
		return activityMainValues;
	}
	public void setActivityMainValues(ArrayList<ResultTableBean> activityMainValues) {
		this.activityMainValues = activityMainValues;
	}
	public ArrayList<ResultTableBean> getConstTypeValues() {
		return constTypeValues;
	}
	public void setConstTypeValues(ArrayList<ResultTableBean> constTypeValues) {
		this.constTypeValues = constTypeValues;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelectCustomeGFCID() {
		return selectCustomeGFCID;
	}
	public void setSelectCustomeGFCID(String selectCustomeGFCID) {
		this.selectCustomeGFCID = selectCustomeGFCID;
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
	public ArrayList<ResultTableBean> getCustomerRoleRelationshipValues() {
		return customerRoleRelationshipValues;
	}
	public void setCustomerRoleRelationshipValues(
			ArrayList<ResultTableBean> customerRoleRelationshipValues) {
		this.customerRoleRelationshipValues = customerRoleRelationshipValues;
	}
	
	public void resetCheckBoxs(){
		if(getRegisterConsumer()!=null){
			
			getRegisterConsumer().setIsFatca(null);
			getRegisterConsumer().setIsCrs(null);
			getRegisterConsumer().setIsAnnualReview(null);
			
			if(getRegisterConsumer().getResidentialAddress()!=null){
				getRegisterConsumer().getResidentialAddress().setIsCorrespondence(null);
			}
			
			if(getRegisterConsumer().getOtherAddress()!=null){
				getRegisterConsumer().getOtherAddress().setIsCorrespondence(null);
			}
			
			if(getRegisterConsumer().getHeadOfficeAddress()!=null){
				getRegisterConsumer().getHeadOfficeAddress().setIsCorrespondence(null);
			}
			
			if(getRegisterConsumer().getBusinessAddress()!=null){
				getRegisterConsumer().getBusinessAddress().setIsCorrespondence(null);
			}

		}

	}
	public boolean isFromApprove() {
		return isFromApprove;
	}
	public void setFromApprove(boolean isFromApprove) {
		this.isFromApprove = isFromApprove;
	}
}
