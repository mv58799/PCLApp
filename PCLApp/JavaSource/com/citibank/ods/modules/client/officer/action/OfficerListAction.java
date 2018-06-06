package com.citibank.ods.modules.client.officer.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.officer.functionality.OfficerListFnc;
import com.citibank.ods.modules.client.officer.functionality.valueobject.OfficerListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.officer.action; 
 *@version 1.0
 *@author l.braga,23/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class OfficerListAction extends BaseODSListAction
{
  private static final String C_CLEAR_PAGE = "ClearPage";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "Officer.OfficerList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return OfficerListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new OfficerListFnc();
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
    String[] splittedInvokePath = invokePath_.split( "\\." );
    OfficerListFnc listFnc = ( OfficerListFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_LIST )
         && ( splittedInvokePath[ 3 ].equals( C_CLEAR_PAGE ) ) )

    {
      listFnc.clearPage( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
    }
    return forwardKey;
  }
}