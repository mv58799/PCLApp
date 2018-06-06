package com.citibank.ods.modules.client.customerprvt.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.customerprvt.functionality.CustomerPrvtDetailFnc;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.CustomerPrvtDetailFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.customerPrvt.action; 
 *@version 1.0
 *@author l.braga,14/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class CustomerPrvtDetailAction extends BaseODSDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CustomerPrvt.CustomerPrvtDetail";

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return new CustomerPrvtDetailFnc();
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return C_SCREEN_NAME;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return CustomerPrvtDetailFncVO.class.getName();
  }

}
