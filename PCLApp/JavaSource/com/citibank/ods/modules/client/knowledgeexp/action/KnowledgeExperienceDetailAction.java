package com.citibank.ods.modules.client.knowledgeexp.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.knowledgeexp.functionality.KnowledgeExperienceDetailFnc;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceDetailFncVO;

public class KnowledgeExperienceDetailAction extends BaseODSDetailAction{

    private static final String C_SCREEN_NAME = "KnowledgeExperience.KnowledgeExperienceDetail";	
	
	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseFnc getFuncionality() {
		return new KnowledgeExperienceDetailFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	public String getFncVOPublishName() {
		return KnowledgeExperienceDetailFncVO.class.getName();
	}

}
