package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplTermAdhesionMovEntityVO extends BaseEntityVO {
	private String trsrySysInd;
	private String dpSysInd;
	private String adhTermTypeCode;
	private Date lastApprvDate;
	private String lastApprvUserId;
	private Date lastUpdDate;
	private String lastUpdUserId;
	private String opernTypeCode;
	public String getTrsrySysInd() {
		return trsrySysInd;
	}
	public void setTrsrySysInd(String trsrySysInd) {
		this.trsrySysInd = trsrySysInd;
	}
	public String getDpSysInd() {
		return dpSysInd;
	}
	public void setDpSysInd(String dpSysInd) {
		this.dpSysInd = dpSysInd;
	}
	public String getAdhTermTypeCode() {
		return adhTermTypeCode;
	}
	public void setAdhTermTypeCode(String adhTermTypeCode) {
		this.adhTermTypeCode = adhTermTypeCode;
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
	public String getOpernTypeCode() {
		return opernTypeCode;
	}
	public void setOpernTypeCode(String opernTypeCode) {
		this.opernTypeCode = opernTypeCode;
	}
}