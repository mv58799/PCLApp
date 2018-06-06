package com.citibank.ods.modules.client.knowledgeexp.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.knowledgeexp.form.BaseKnowledgeExperienceListForm;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.BaseKnowledgeExperienceListFncVO;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceListFncVO;

public abstract class BaseKnowledgeExperienceListFnc extends BaseFnc implements ODSListFnc {

	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		
		BaseKnowledgeExperienceListFncVO fncVO = (BaseKnowledgeExperienceListFncVO) fncVO_;
		BaseKnowledgeExperienceListForm form = (BaseKnowledgeExperienceListForm) form_;

		BigInteger clientNumber = 	( form.getClientNumber() != null && form.getClientNumber().length() > 0 )?
										new BigInteger(form.getClientNumber())
										:null;
		
		String clientNameText = ( form.getClientNameText() != null )?
										form.getClientNameText()
										:"";
		
		fncVO.setClientNumber(clientNumber);
		fncVO.setClientNameText(clientNameText);
		
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {

		BaseKnowledgeExperienceListFncVO fncVO = (BaseKnowledgeExperienceListFncVO) fncVO_;
		BaseKnowledgeExperienceListForm form = (BaseKnowledgeExperienceListForm) form_;
		
		DataSet results = (fncVO.getResults() != null)? fncVO.getResults() : null;
		
		form.setResults(results);
		
	}

	public void validateList(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}
	
	public void load(BaseFncVO fncVO_) {
		
		BaseKnowledgeExperienceListFncVO fncVO = (BaseKnowledgeExperienceListFncVO) fncVO_;		
		fncVO.setResults(null);
		
	}
	

}
