package com.citibank.ods.modules.product.membershipTerm.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

public class MembershipTermListFncVO extends BaseODSFncVO {

	private static final long serialVersionUID = 1L;

	private Long curAcctNbr;

	public Long getCurAcctNbr() {
		return curAcctNbr;
	}

	public void setCurAcctNbr(Long curAcctNbr) {
		this.curAcctNbr = curAcctNbr;
	}

}
