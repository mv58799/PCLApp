/*
 * Created on Apr 25, 2007
 *
 */
package com.citibank.ods.common.action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.user.User;

/**
 * @author marcelo.s.oliveira
 *
 */
public class ODSLogoffAction extends BaseAction{

	private static final String C_ODS_LOGOFF_SCREEN = "ODSExit";

	/* Redirecinamento para tela de Menu após tela de Disclaimer.
	 * @see com.citibank.ods.common.action.BaseAction#execute(java.lang.String, org.apache.struts.action.ActionForm, org.apache.struts.action.ActionErrors, com.citibank.ods.common.functionality.valueobject.BaseFncVO, com.citibank.ods.common.user.User, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public ActionResult execute(String invokePath_, ActionForm actionForm_, ActionErrors actionErrors_, BaseFncVO fncVo_, User loggedUser_, ActionErrors previousErrors_, ActionMessages previousMessages_,String ordenar) {
		
		ActionResult actionResult = null;
		actionResult = buildLogoffActionResult();	
		
		return actionResult;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
	 */
	public String getFncVOPublishName() {
		
		return BaseFncVO.class.getName();
				
	}
}