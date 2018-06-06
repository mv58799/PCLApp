package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplDataSubscptMovEntityVO extends BaseEntityVO {
	private String fundNameText;
	private BigInteger fileImportCode;
	private BigInteger acctNbr;
	private Date subscptOpenDate;
	private String subjectText;
	private String eventText;
	private Date subscptSolDate;
	private String subjectPhaseText;
	private Date phaseRsltnDate;
	private String commentText;
	private String incpatCnfrmInd;
	private String reserveInd;
	private String trainingTermInd;
	private String documentCheckInd;
	private String trfOrderInd;
	private String bkrCodeText;
	private String prodKnwlgText;
	private String plyrCpfNbr;
	private String prodRiskLvlQty;
	private String incpatApprvNameText;
	private String prodNameText;
	private String transactionType;
	private Date lastApprvDate;
	private String lastApprvUserId;
	private Date lastUpdDate;
	private String lastUpdUserId;
	
	public String getFundNameText() {
		return fundNameText;
	}
	public void setFundNameText(String fundNameText) {
		this.fundNameText = fundNameText;
	}
	public BigInteger getFileImportCode() {
		return fileImportCode;
	}
	public void setFileImportCode(BigInteger fileImportCode) {
		this.fileImportCode = fileImportCode;
	}
	public BigInteger getAcctNbr() {
		return acctNbr;
	}
	public void setAcctNbr(BigInteger acctNbr) {
		this.acctNbr = acctNbr;
	}
	public Date getSubscptOpenDate() {
		return subscptOpenDate;
	}
	public void setSubscptOpenDate(Date subscptOpenDate) {
		this.subscptOpenDate = subscptOpenDate;
	}
	public String getSubjectText() {
		return subjectText;
	}
	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}
	public String getEventText() {
		return eventText;
	}
	public void setEventText(String eventText) {
		this.eventText = eventText;
	}
	public Date getSubscptSolDate() {
		return subscptSolDate;
	}
	public void setSubscptSolDate(Date subscptSolDate) {
		this.subscptSolDate = subscptSolDate;
	}
	public String getSubjectPhaseText() {
		return subjectPhaseText;
	}
	public void setSubjectPhaseText(String subjectPhaseText) {
		this.subjectPhaseText = subjectPhaseText;
	}
	public Date getPhaseRsltnDate() {
		return phaseRsltnDate;
	}
	public void setPhaseRsltnDate(Date phaseRsltnDate) {
		this.phaseRsltnDate = phaseRsltnDate;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getIncpatCnfrmInd() {
		return incpatCnfrmInd;
	}
	public void setIncpatCnfrmInd(String incpatCnfrmInd) {
		this.incpatCnfrmInd = incpatCnfrmInd;
	}
	public String getReserveInd() {
		return reserveInd;
	}
	public void setReserveInd(String reserveInd) {
		this.reserveInd = reserveInd;
	}
	public String getTrainingTermInd() {
		return trainingTermInd;
	}
	public void setTrainingTermInd(String trainingTermInd) {
		this.trainingTermInd = trainingTermInd;
	}
	public String getDocumentCheckInd() {
		return documentCheckInd;
	}
	public void setDocumentCheckInd(String documentCheckInd) {
		this.documentCheckInd = documentCheckInd;
	}
	public String getTrfOrderInd() {
		return trfOrderInd;
	}
	public void setTrfOrderInd(String trfOrderInd) {
		this.trfOrderInd = trfOrderInd;
	}
	public String getBkrCodeText() {
		return bkrCodeText;
	}
	public void setBkrCodeText(String bkrCodeText) {
		this.bkrCodeText = bkrCodeText;
	}
	public String getProdKnwlgText() {
		return prodKnwlgText;
	}
	public void setProdKnwlgText(String prodKnwlgText) {
		this.prodKnwlgText = prodKnwlgText;
	}
	public String getPlyrCpfNbr() {
		return plyrCpfNbr;
	}
	public void setPlyrCpfNbr(String plyrCpfNbr) {
		this.plyrCpfNbr = plyrCpfNbr;
	}
	public String getProdRiskLvlQty() {
		return prodRiskLvlQty;
	}
	public void setProdRiskLvlQty(String prodRiskLvlQty) {
		this.prodRiskLvlQty = prodRiskLvlQty;
	}
	public String getIncpatApprvNameText() {
		return incpatApprvNameText;
	}
	public void setIncpatApprvNameText(String incpatApprvNameText) {
		this.incpatApprvNameText = incpatApprvNameText;
	}
	public String getProdNameText() {
		return prodNameText;
	}
	public void setProdNameText(String prodNameText) {
		this.prodNameText = prodNameText;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getLastApprvDate() {
		return lastApprvDate;
	}
	public void setLastApprvDate(Date lastApprvDate) {
		this.lastApprvDate = lastApprvDate;
	}
	public String getLastApprvUserId() {
		return lastApprvUserId;
	}
	public void setLastApprvUserId(String lastApprvUserId) {
		this.lastApprvUserId = lastApprvUserId;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdUserId() {
		return lastUpdUserId;
	}
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}
}