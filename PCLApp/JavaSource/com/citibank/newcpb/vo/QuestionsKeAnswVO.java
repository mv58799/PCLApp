package com.citibank.newcpb.vo;

import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;


public class QuestionsKeAnswVO {	
	
	private String acctNbr;
	private String cpfCnpjNbr;
	private String prodKeCode;
	private String prodFamlKeCode;
	private String prodSeq;
	private String prodFamlSeq;
	private String prodFamlKeText;
	private String prodKeText;
	private String questionName;
	private String prodAnswer;
	private String fmaAnswer;
	private String vmaAnswer;
	private boolean needAnswer;
	private String colorBackground;
	private String questTypeCode;
	
		
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	
	private TableTypeEnum tableOrigin;
	
	public String getProdKeCode() {
		return prodKeCode;
	}
	public void setProdKeCode(String prodKeCode) {
		this.prodKeCode = prodKeCode;
	}
	public String getProdFamlKeCode() {
		return prodFamlKeCode;
	}
	public void setProdFamlKeCode(String prodFamlKeCode) {
		this.prodFamlKeCode = prodFamlKeCode;
	}
	public String getProdSeq() {
		return prodSeq;
	}
	public void setProdSeq(String prodSeq) {
		this.prodSeq = prodSeq;
	}
	public String getAcctNbr() {
		return acctNbr;
	}
	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getProdAnswer() {
		return prodAnswer;
	}
	public void setProdAnswer(String prodAnswer) {
		this.prodAnswer = prodAnswer;
	}
	public String getFmaAnswer() {
		return fmaAnswer;
	}
	public void setFmaAnswer(String fmaAnswer) {
		this.fmaAnswer = fmaAnswer;
	}
	public String getVmaAnswer() {
		return vmaAnswer;
	}
	public void setVmaAnswer(String vmaAnswer) {
		this.vmaAnswer = vmaAnswer;
	}
	public boolean isNeedAnswer() {
		return needAnswer;
	}
	public void setNeedAnswer(boolean needAnswer) {
		this.needAnswer = needAnswer;
	}
	/**
	 * @return the colorBackground
	 */
	public String getColorBackground() {
		return colorBackground;
	}
	/**
	 * @param colorBackground the colorBackground to set
	 */
	public void setColorBackground(String colorBackground) {
		this.colorBackground = colorBackground;
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
	public Date getLastUpdDate() {
		return lastUpdDate;
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
	/**
	 * @return the questTypeCode
	 */
	public String getQuestTypeCode() {
		return questTypeCode;
	}
	/**
	 * @param questTypeCode the questTypeCode to set
	 */
	public void setQuestTypeCode(String questTypeCode) {
		this.questTypeCode = questTypeCode;
	}
	public String getProdFamlSeq() {
		return prodFamlSeq;
	}
	public void setProdFamlSeq(String prodFamlSeq) {
		this.prodFamlSeq = prodFamlSeq;
	}
	public String getProdFamlKeText() {
		return prodFamlKeText;
	}
	public void setProdFamlKeText(String prodFamlKeText) {
		this.prodFamlKeText = prodFamlKeText;
	}
	public String getProdKeText() {
		return prodKeText;
	}
	public void setProdKeText(String prodKeText) {
		this.prodKeText = prodKeText;
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
}