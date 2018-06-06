package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;


public class QuestionsKeVO {		
	
	private String acctNbr;
	private String cpfCnpjNbr;
	private Date lastAuthDate;
	private String lastAuthUser;
	private Date lastUpdDate;
	private String lastUpdUser;
	private String recStatCode;
	private TableTypeEnum tableOrigin;	
	private boolean hasQuestionsKe;
	private ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList;
	private AcctCmplVO acctCmplVO;	
	private ArrayList<KeCustFileVO> fileList;
	private boolean hasQuestionsKeVO;
	private String lastAuthDateFormatedDDMMYYYYAndUser;
	
	/**
	 * @return the acctNbr
	 */
	public String getAcctNbr() {
		return acctNbr;
	}
	/**
	 * @param acctNbr the acctNbr to set
	 */
	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}
	public Date getLastAuthDate() {
		return lastAuthDate;
	}
	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	public String getLastAuthUser() {
		return lastAuthUser;
	}
	public void setLastAuthUser(String lastAuthUser) {
		this.lastAuthUser = lastAuthUser;
	}
	
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
		}else{
			return "";
		}
	}
	
	public String getLastAuthUserSafe() {
		if(lastAuthUser!=null){
			return lastAuthUser;
		}else{
			return "";
		}
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdUser() {
		return lastUpdUser;
	}
	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
	}
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("A")){
				return "Inclusão";
			}else if(recStatCode.equals("U")){
				return "Alteração";
			}else if(recStatCode.equals("I")){
				return "Exclusão";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public String getLastAuthDateFormatedDDMMYYYYAndUser() {
		if(!StringUtils.isBlank(getLastAuthDateFormatedDDMMYYYY()) || !StringUtils.isBlank(getLastAuthUser()) || !StringUtils.isBlank(getLastUpdUserSafe())){
			return getLastAuthDateFormatedDDMMYYYY() + " - " + getLastUpdUserSafe() + " - " + getLastAuthUserSafe();
		}else{
			return "";
		}
		
	}
	
	public String getLastAuthDateFormatedDDMMYYYY() {
		if(lastAuthDate!=null){
			return FormatUtils.dateToString(lastAuthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}
	

	/**
	 * @return the tableOrigin
	 */
	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}
	/**
	 * @param tableOrigin the tableOrigin to set
	 */
	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}
	/**
	 * @return the questionsKeAnswVOList
	 */
	public ArrayList<QuestionsKeAnswVO> getQuestionsKeAnswVOList() {
		return questionsKeAnswVOList;
	}
	/**
	 * @param questionsKeAnswVOList the questionsKeAnswVOList to set
	 */
	public void setQuestionsKeAnswVOList(ArrayList<QuestionsKeAnswVO> questionsKeAnswVOList) {
		this.questionsKeAnswVOList = questionsKeAnswVOList;
	}
	/**
	 * @return the acctCmplVO
	 */
	public AcctCmplVO getAcctCmplVO() {
		return acctCmplVO;
	}
	/**
	 * @param acctCmplVO the acctCmplVO to set
	 */
	public void setAcctCmplVO(AcctCmplVO acctCmplVO) {
		this.acctCmplVO = acctCmplVO;
	}
	/**
	 * @return the hasQuestionsKe
	 */
	public boolean isHasQuestionsKe() {
		return hasQuestionsKe;
	}
	/**
	 * @param hasQuestionsKe the hasQuestionsKe to set
	 */
	public void setHasQuestionsKe(boolean hasQuestionsKe) {
		this.hasQuestionsKe = hasQuestionsKe;
	}
	
	/**
	 * @return the fileList
	 */
	public ArrayList<KeCustFileVO> getFileList() {
		return fileList;
	}
	/**
	 * @param fileList the fileList to set
	 */
	public void setFileList(ArrayList<KeCustFileVO> fileList) {
		this.fileList = fileList;
	}
	/**
	 * @return the hasQuestionsKeVO
	 */
	public boolean isHasQuestionsKeVO() {
		if(fileList!=null && fileList.size()>0){
			hasQuestionsKeVO = true;
		}else{
			hasQuestionsKeVO = false;
		}		
		return hasQuestionsKeVO;
	}
	/**
	 * @param hasQuestionsKeVO the hasQuestionsKeVO to set
	 */
	public void setHasQuestionsKeVO(boolean hasQuestionsKeVO) {
		this.hasQuestionsKeVO = hasQuestionsKeVO;
	}
	/**
	 * @return the cpfCnpjNbr
	 */
	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}
	/**
	 * @param cpfCnpjNbr the cpfCnpjNbr to set
	 */
	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}
}