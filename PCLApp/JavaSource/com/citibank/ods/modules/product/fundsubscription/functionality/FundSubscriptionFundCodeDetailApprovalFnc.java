package com.citibank.ods.modules.product.fundsubscription.functionality;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionFundCodeDetailApprovalFncVO;

public class FundSubscriptionFundCodeDetailApprovalFnc extends BaseFnc{

	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionFundCodeDetailApprovalFncVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		FundSubscriptionFundCodeDetailApprovalFncVO fnc = ( FundSubscriptionFundCodeDetailApprovalFncVO ) fncVO_;

	    fnc.clearErrors();
	    fnc.clearMessages();
	    fnc.setCodigo(null);
	    fnc.setNome(null);
	    fnc.setCurrentPage(null);
		
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(form_, fncVO_);
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(fncVO_, form_);
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
