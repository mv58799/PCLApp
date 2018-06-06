package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseImportEntityVO;
import com.citibank.ods.common.util.Table;
import com.citibank.ods.common.util.TableTypeEnum;

@Table("TPL_DATA_SUBSCRIPTION")
public class BaseTplDataSubscptEntityVO extends BaseImportEntityVO {
	
	private String prodImpTypeCode;
	
	private String fundNameText;
	 
	private BigInteger acctNbr;
	 
	private BigInteger fileImportCode;
	 
	private Date subscptOpenDate;
	 
	private String subjectText;
	 
	private String eventText;
	 
	private Date subjectSolDate;
	 
	private String subjectPhaseText;
	 
	private Date phaseRsltnDate;
	 
	private String commentText;
	 
	private String cnfrmIncpatInd;
	 
	private String reserveInd;
	 
	private String trainingTermInd;
	 
	private String documentCheckInd;
	 
	private String stockTrfOrderInd;
	 
	private String bkrCodeText;
	 
	private String prodKnwlgText;
	 
	private String plyrCpfNbr;
	 
	private Long prodRiskLvlQty;
	 
	private String incpatApprvNameText;
	 
	private String prodNameText;
	 
	private String transactionTypeText;
	 
	private Date lastApprvDate;
	 
	private String lastApprvUserId;
	
	private Date lastUpdDate;
	 
	private String lastUpdUserId;
	
	private String enrollment;
	
	private BigDecimal addDiffAmt;
	
	private BigDecimal investimentAmt;
	
	private String opernTypeCode;
	
	public String getOpernTypeCode() {
		return opernTypeCode;
	}
	public void setOpernTypeCode(String opernTypeCode) {
		this.opernTypeCode = opernTypeCode;
	}
	public String getProdImpTypeCode() {
		return prodImpTypeCode;
	}
	public void setProdImpTypeCode(String prodImpTypeCode) {
		this.prodImpTypeCode = prodImpTypeCode;
	}
	public BigDecimal getAddDiffAmt() {
		return addDiffAmt;
	}
	public void setAddDiffAmt(BigDecimal addDiffAmt) {
		this.addDiffAmt = addDiffAmt;
	}
	public BigDecimal getInvestimentAmt() {
		return investimentAmt;
	}
	public void setInvestimentAmt(BigDecimal investimentAmt) {
		this.investimentAmt = investimentAmt;
	}
	public String getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	private TableTypeEnum tableType; 
	public TableTypeEnum getTableType() {
		return tableType;
	}
	public void setTableType(TableTypeEnum tableType) {
		this.tableType = tableType;
	}
	public String getFundNameText() {
		return fundNameText;
	}
	public void setFundNameText(String fundNameText) {
		this.fundNameText = fundNameText;
	}
	public BigInteger getAcctNbr() {
		return acctNbr;
	}
	public void setAcctNbr(BigInteger acctNbr) {
		this.acctNbr = acctNbr;
	}
	public BigInteger getFileImportCode() {
		return fileImportCode;
	}
	public void setFileImportCode(BigInteger fileImportCode) {
		this.fileImportCode = fileImportCode;
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
	public Date getSubjectSolDate() {
		return subjectSolDate;
	}
	public void setSubjectSolDate(Date subjectSolDate) {
		this.subjectSolDate = subjectSolDate;
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
	public String getCnfrmIncpatInd() {
		return cnfrmIncpatInd;
	}
	public void setCnfrmIncpatInd(String cnfrmIncpatInd) {
		this.cnfrmIncpatInd = cnfrmIncpatInd;
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
	public String getStockTrfOrderInd() {
		return stockTrfOrderInd;
	}
	public void setStockTrfOrderInd(String stockTrfOrderInd) {
		this.stockTrfOrderInd = stockTrfOrderInd;
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
	public Long getProdRiskLvlQty() {
		return prodRiskLvlQty;
	}
	public void setProdRiskLvlQty(Long prodRiskLvlQty) {
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
	public String getTransactionTypeText() {
		return transactionTypeText;
	}
	public void setTransactionTypeText(String transactionTypeText) {
		this.transactionTypeText = transactionTypeText;
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
	@Override
	public String toString() {
		return "BaseTplDataSubscptEntityVO [prodImpTypeCode=" + prodImpTypeCode
				+ ", fundNameText=" + fundNameText + ", acctNbr=" + acctNbr
				+ ", fileImportCode=" + fileImportCode + ", subscptOpenDate="
				+ subscptOpenDate + ", subjectText=" + subjectText
				+ ", eventText=" + eventText + ", subjectSolDate="
				+ subjectSolDate + ", subjectPhaseText=" + subjectPhaseText
				+ ", phaseRsltnDate=" + phaseRsltnDate + ", commentText="
				+ commentText + ", cnfrmIncpatInd=" + cnfrmIncpatInd
				+ ", reserveInd=" + reserveInd + ", trainingTermInd="
				+ trainingTermInd + ", documentCheckInd=" + documentCheckInd
				+ ", stockTrfOrderInd=" + stockTrfOrderInd + ", bkrCodeText="
				+ bkrCodeText + ", prodKnwlgText=" + prodKnwlgText
				+ ", plyrCpfNbr=" + plyrCpfNbr + ", prodRiskLvlQty="
				+ prodRiskLvlQty + ", incpatApprvNameText="
				+ incpatApprvNameText + ", prodNameText=" + prodNameText
				+ ", transactionTypeText=" + transactionTypeText
				+ ", lastApprvDate=" + lastApprvDate + ", lastApprvUserId="
				+ lastApprvUserId + ", lastUpdDate=" + lastUpdDate
				+ ", lastUpdUserId=" + lastUpdUserId + ", enrollment="
				+ enrollment + ", addDiffAmt=" + addDiffAmt
				+ ", investimentAmt=" + investimentAmt + ", tableType="
				+ tableType + "]";
	}
	
}