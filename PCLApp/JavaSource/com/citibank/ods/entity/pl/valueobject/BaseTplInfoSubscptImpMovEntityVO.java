package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;


public class BaseTplInfoSubscptImpMovEntityVO extends BaseEntityVO {
	private Date fileImportDate;
	private String importFileNameText;
	private BigInteger fileImportRecQty;
	private Date lastUpdDate;
	private String lastUpdUserId;
	private String opernTypeCode;
	
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
	public String getOpernTypeCode() {
		return opernTypeCode;
	}
	public void setOpernTypeCode(String opernTypeCode) {
		this.opernTypeCode = opernTypeCode;
	}
}
