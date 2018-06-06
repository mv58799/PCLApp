package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplFundMortMovEntityVO extends BaseEntityVO {
	private BigInteger fundCode;
	private Date lastUpdDate;
	private String lastUpdUserId;
	private String opernTypeCode;
	
	public BigInteger getFundCode() {
		return fundCode;
	}
	public void setFundCode(BigInteger fundCode) {
		this.fundCode = fundCode;
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