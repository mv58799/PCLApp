package com.citibank.ods.modules.client.knowledgeexp.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.knowledgeexp.form.BaseKnowledgeExperienceDetailForm;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.BaseKnowledgeExperienceDetailFncVO;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceDetailFncVO;

public abstract class BaseKnowledgeExperienceDetailFnc extends BaseFnc implements ODSDetailFnc{

	@Override
	public BaseFncVO createFncVO() {
		return new KnowledgeExperienceDetailFncVO();
	}

	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		
		BaseKnowledgeExperienceDetailFncVO fncVO = (BaseKnowledgeExperienceDetailFncVO) fncVO_;
		BaseKnowledgeExperienceDetailForm form = (BaseKnowledgeExperienceDetailForm) form_;
		
		BigInteger clientNumber = 	( form.getSelectedClientNumber() != null && form.getSelectedClientNumber().length() > 0 )?
									new BigInteger(form.getSelectedClientNumber())
									:null;

		String clientNameText = ( form.getSelectedClientNameText() != null )?
								form.getSelectedClientNameText()
								:"";
		
		fncVO.setClientNumber(clientNumber);
		fncVO.setClientNameText(clientNameText);
		
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {

		BaseKnowledgeExperienceDetailFncVO fncVO = (BaseKnowledgeExperienceDetailFncVO) fncVO_;
		BaseKnowledgeExperienceDetailForm form = (BaseKnowledgeExperienceDetailForm) form_;
		
		DataSet results = (fncVO.getResults() != null)? fncVO.getResults() : null;
		
		form.setResults(results);
		
	}
	
}
