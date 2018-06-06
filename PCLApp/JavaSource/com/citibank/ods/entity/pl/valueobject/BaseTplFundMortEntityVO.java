package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
import com.citibank.ods.common.util.TableTypeEnum;

public class BaseTplFundMortEntityVO extends BaseEntityVO {
	private String fundCode;
	private String fundName;
	private Date lastApprvDate;
	private String lastApprvUserId;
	private Date lastUpdDate;
	private String lastUpdUserId;
	private String opernTypeCode;
	
	private TableTypeEnum tableType;
	
	public String getOpernTypeCode() {
		return opernTypeCode;
	}
	public void setOpernTypeCode(String opernTypeCode) {
		this.opernTypeCode = opernTypeCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public TableTypeEnum getTableType() {
		return tableType;
	}
	public void setTableType(TableTypeEnum tableType) {
		this.tableType = tableType;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
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