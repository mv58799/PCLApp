package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

public class TplDataSubscptEntityVO extends BaseTplDataSubscptEntityVO {

	private String fundCode;

	private String custName;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	
}
