package com.citibank.ods.modules.product.membershipTerm.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

public class SegmentoFncVO extends BaseODSFncVO {

	private static final long serialVersionUID = 1L;

	private String segCode;
	private String segName;

	public String getSegCode() {
		return segCode;
	}

	public void setSegCode(String segCode) {
		this.segCode = segCode;
	}

	public String getSegName() {
		return segName;
	}

	public void setSegName(String segName) {
		this.segName = segName;
	}

}
