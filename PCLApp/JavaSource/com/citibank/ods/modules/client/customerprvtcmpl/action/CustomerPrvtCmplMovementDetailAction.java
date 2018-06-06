/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.CustomerPrvtCmplMovementDetailFnc;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplMovementDetailFncVO;
/**
 * @author bruno.zanetti
 *  
 */
public class CustomerPrvtCmplMovementDetailAction extends BaseODSMovementDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CustomerPrvtCmpl.CustomerPrvtCmplMovementDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return CustomerPrvtCmplMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CustomerPrvtCmplMovementDetailFnc();
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
  
  protected String searchActions(BaseFncVO fncVO_, String invokePath_ )
  {
    CustomerPrvtCmplMovementDetailFncVO fncVO = (CustomerPrvtCmplMovementDetailFncVO) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage= splittedInvokePath[3] + C_SPLITTER_CHAR + splittedInvokePath[4];
    fncVO.setFromSearch(true);
    fncVO.setClickedSearch(nextPage);    

    return getScreenName();
  }
}