package com.citibank.ods.modules.product.fundsubscription.functionality;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionListFncVO;
//TODO implements ODSDetailFnc
public class FundSubscriptionListFnc extends BaseFnc  {

	

	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionListFncVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		FundSubscriptionListFncVO fnc = ( FundSubscriptionListFncVO ) fncVO_;

	    fnc.clearErrors();
	    fnc.clearMessages();
	    
	    fnc.setNumeroConta(null);
	    fnc.setNomeCliente(null);
	    fnc.setTipoProduto(null);
	    fnc.setNomeProduto(null);
	    fnc.setResultado(null);
		
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
