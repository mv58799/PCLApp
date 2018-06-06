package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.vo.AcctCmplVO;
import com.citibank.newcpb.vo.QuestionsKeVO;

public class QuestionsKeForm extends BaseForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String filterNumberEM;
	private String filterCpfCnpj;
	private String filterNumberAccount;
	private String filterNumberGFCID;
	private String filterName;
	private String userIdFilter;	
	private String moduleProcessTextFilter;
	private FormFile file;
	private Integer idAuxContFile = 0;
	
	private String selectNumberAccount;
	private String selectCpfCnpj;
	private Integer selectNumberFile;
	

	//Lista de Contas e Clientes
	private ArrayList<AcctCmplVO> resultList;

	//Controle Principal K&E
	private QuestionsKeVO questionsKeVO;
		
	//Combos da tela 
	private ArrayList<ResultTableBean> knowledgeExperienceProd;
	private ArrayList<ResultTableBean> averageFrequencyByYear;
	private ArrayList<ResultTableBean> averageVolumeByYear;

	
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isFromApprove;

	
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
	public ArrayList<AcctCmplVO> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<AcctCmplVO> resultList) {
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
	
	public String getFilterNumberEM() {
		return filterNumberEM;
	}
	public void setFilterNumberEM(String filterNumberEM) {
		this.filterNumberEM = filterNumberEM;
	}
	public String getFilterCpfCnpj() {
		return filterCpfCnpj;
	}
	public void setFilterCpfCnpj(String filterCpfCnpj) {
		this.filterCpfCnpj = filterCpfCnpj;
	}
	public String getFilterNumberAccount() {
		return filterNumberAccount;
	}
	public void setFilterNumberAccount(String filterNumberAccount) {
		this.filterNumberAccount = filterNumberAccount;
	}

	public boolean isFromApprove() {
		return isFromApprove;
	}
	public void setFromApprove(boolean isFromApprove) {
		this.isFromApprove = isFromApprove;
	}
	public String getSelectNumberAccount() {
		return selectNumberAccount;
	}
	public void setSelectNumberAccount(String selectNumberAccount) {
		this.selectNumberAccount = selectNumberAccount;
	}
	public ArrayList<ResultTableBean> getKnowledgeExperienceProd() {
		return knowledgeExperienceProd;
	}
	public void setKnowledgeExperienceProd(ArrayList<ResultTableBean> knowledgeExperienceProd) {
		this.knowledgeExperienceProd = knowledgeExperienceProd;
	}
	public ArrayList<ResultTableBean> getAverageFrequencyByYear() {
		return averageFrequencyByYear;
	}
	public void setAverageFrequencyByYear(
			ArrayList<ResultTableBean> averageFrequencyByYear) {
		this.averageFrequencyByYear = averageFrequencyByYear;
	}
	public ArrayList<ResultTableBean> getAverageVolumeByYear() {
		return averageVolumeByYear;
	}
	public void setAverageVolumeByYear(
			ArrayList<ResultTableBean> averageVolumeByYear) {
		this.averageVolumeByYear = averageVolumeByYear;
	}

	public QuestionsKeVO getQuestionsKeVO() {
		return questionsKeVO;
	}

	public void setQuestionsKeVO(QuestionsKeVO questionsKeVO) {
		this.questionsKeVO = questionsKeVO;
	}
	public String getFilterNumberGFCID() {
		return filterNumberGFCID;
	}
	public void setFilterNumberGFCID(String filterNumberGFCID) {
		this.filterNumberGFCID = filterNumberGFCID;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	/**
	 * @return the idAuxContFile
	 */
	public Integer getIdAuxContFile() {
		idAuxContFile = idAuxContFile - 1;
		return idAuxContFile;
	}
	/**
	 * @param idAuxContFile the idAuxContFile to set
	 */
	public void setIdAuxContFile(Integer idAuxContFile) {
		this.idAuxContFile = idAuxContFile;
	}
	/**
	 * @return the selectNumberFile
	 */
	public Integer getSelectNumberFile() {
		return selectNumberFile;
	}
	/**
	 * @param selectNumberFile the selectNumberFile to set
	 */
	public void setSelectNumberFile(Integer selectNumberFile) {
		this.selectNumberFile = selectNumberFile;
	}
	/**
	 * @return the selectCpfCnpj
	 */
	public String getSelectCpfCnpj() {
		return selectCpfCnpj;
	}
	/**
	 * @param selectCpfCnpj the selectCpfCnpj to set
	 */
	public void setSelectCpfCnpj(String selectCpfCnpj) {
		this.selectCpfCnpj = selectCpfCnpj;
	}

}
