/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.classcmplc.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.classcmplc.functionality.ClassCmplcListFnc;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.ClassCmplcListFncVO;
/**
 * @author bruno.zanetti
 *  
 */
public class ClassCmplcListAction extends BaseODSListAction
{

  /*
   * Parte do nome do m�dulo ou a��o
   */
  private static final String C_SCREEN_NAME = "ClassCmplc.ClassCmplcList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ClassCmplcListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ClassCmplcListFnc();
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