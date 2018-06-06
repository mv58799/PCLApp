/*
 * Created on Apr 25, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
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
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class DisclaimerAction extends BaseAction
{

  private static final String C_ODS_MENU_SCREEN = "ODSDisclaimer";

  /*
   * Redirecinamento para tela de Menu após tela de Disclaimer.
   * @see com.citibank.ods.common.action.BaseAction#execute(java.lang.String,
   *      org.apache.struts.action.ActionForm,
   *      org.apache.struts.action.ActionErrors,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      com.citibank.ods.common.user.User,
   *      org.apache.struts.action.ActionErrors,
   *      org.apache.struts.action.ActionMessages)
   */
  public ActionResult execute( String invokePath_, ActionForm actionForm_,
                              ActionErrors actionErrors_, BaseFncVO fncVo_,
                              User loggedUser_, ActionErrors previousErrors_,
                              ActionMessages previousMessages_,String ordenar )
  {
    //return null;
    ActionResult actionResult = null;
    String forwardKey = C_ODS_MENU_SCREEN;
    //BaseFncVO fncVO = ( BaseFncVO ) fncVo_;
    actionResult = buildActionResult( fncVo_, forwardKey );

    return actionResult;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    //return "";
    return BaseFncVO.class.getName();
  }

}