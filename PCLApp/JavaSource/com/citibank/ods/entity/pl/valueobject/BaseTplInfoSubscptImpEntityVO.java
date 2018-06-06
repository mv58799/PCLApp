package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
import com.citibank.ods.common.util.TableTypeEnum;


public class BaseTplInfoSubscptImpEntityVO extends BaseEntityVO {
	private Long fileImportCode;
	private Date fileImportDate;
	private String importFileNameText;
	private BigInteger fileImportRecQty;
	private Date lastApprvDate;
	private String lastApprvUserId;
	private Date lastUpdDate;
	private String lastUpdUserId;
	private String opernTypeCode;
	private TableTypeEnum tableType;
	
	public Long getFileImportCode() {
		return fileImportCode;
	}
	public void setFileImportCode(Long fileImportCode) {
		this.fileImportCode = fileImportCode;
	}
	public Date getFileImportDate() {
		return fileImportDate;
	}
	public void setFileImportDate(Date fileImportDate) {
		this.fileImportDate = fileImportDate;
	}
	public String getImportFileNameText() {
		return importFileNameText;
	}
	public void setImportFileNameText(String importFileNameText) {
		this.importFileNameText = importFileNameText;
	}
	public BigInteger getFileImportRecQty() {
		return fileImportRecQty;
	}
	public void setFileImportRecQty(BigInteger fileImportRecQty) {
		this.fileImportRecQty = fileImportRecQty;
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
	public TableTypeEnum getTableType() {
		return tableType;
	}
	public void setTableType(TableTypeEnum tableType) {
		this.tableType = tableType;
	}
	public String getOpernTypeCode() {
		return opernTypeCode;
	}
	public void setOpernTypeCode(String opernTypeCode) {
		this.opernTypeCode = opernTypeCode;
	}
}