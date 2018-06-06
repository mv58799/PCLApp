/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.classcmplc.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.classcmplc.functionality.ClassCmplcDetailFnc;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.ClassCmplcDetailFncVO;
/**
 * @author gerson.a.rodrigues
 *  
 */
public class ClassCmplcDetailAction extends BaseODSDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ClassCmplc.ClassCmplcDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ClassCmplcDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ClassCmplcDetailFnc();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }
}