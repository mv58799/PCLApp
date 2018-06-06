package com.citibank.ods.modules.product.fundsubscription.functionality;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionFundCodeListFncVO;

public class FundSubscriptionFundCodeListFnc extends BaseFnc{

	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionFundCodeListFncVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		FundSubscriptionFundCodeListFncVO fnc = ( FundSubscriptionFundCodeListFncVO ) fncVO_;

	    fnc.clearErrors();
	    fnc.clearMessages();
	    
	    fnc.setNomeFundo(null);
	    fnc.setResultado(null);
	    fnc.setFundosComCodigo(false);
	    
	    
		
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
