package com.citibank.ods.modules.client.knowledgeexp.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.knowledgeexp.functionality.KnowledgeExperienceListFnc;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceListFncVO;

public class KnowledgeExperienceListAction extends BaseODSListAction {

	/*
	* Parte do nome do módulo ou ação
	*/
    private static final String C_SCREEN_NAME = "KnowledgeExperience.KnowledgeExperienceList";	
	
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		// TODO Auto-generated method stub
		return null;
	}

	protected BaseFnc getFuncionality() {
		return new KnowledgeExperienceListFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	public String getFncVOPublishName() {
		return KnowledgeExperienceListFncVO.class.getName();	
	}
	
}