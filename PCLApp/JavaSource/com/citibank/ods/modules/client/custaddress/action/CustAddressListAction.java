package com.citibank.ods.modules.client.custaddress.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.custaddress.functionality.CustAddressListFnc;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.CustAddressListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Action da classe CustAddressListView.jsp
 * @author l.braga
 *  
 */

public class CustAddressListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CustAddress.CustAddressList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return CustAddressListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CustAddressListFnc();
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
   *          java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }
  /**
   * "[Method description]"
   * 
   * @param
   * @return
   * @exception 
   * @see
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ ){
    
	String forwardKey = "";
    String[] splittedInvokePath = invokePath_.split("\\.");
	forwardKey = splittedInvokePath[4] + C_SPLITTER_CHAR + splittedInvokePath[5];
	
	return forwardKey;
  }
}