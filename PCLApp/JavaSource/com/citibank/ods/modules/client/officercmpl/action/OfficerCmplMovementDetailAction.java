package com.citibank.ods.modules.client.officercmpl.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.officercmpl.functionality.OfficerCmplMovementDetailFnc;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplMovementDetailFncVO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Action da funcionalidade de controle de registros de movimento (Aprovação, 
 * Rejeição e Alteração) dos Dados Complementares de Officer.
 * 
 * @see package com.citibank.ods.modules.client.officercmpl.action;
 * @version 1.0
 * @author fabio.luppi.gil,Mar 5, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OfficerCmplMovementDetailAction extends
    BaseODSMovementDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "OfficerCmpl.OfficerCmplMovementDetail";

  /**
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return OfficerCmplMovementDetailFncVO.class.getName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new OfficerCmplMovementDetailFnc();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }

  /**
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    OfficerCmplMovementDetailFncVO fncVO = ( OfficerCmplMovementDetailFncVO ) fncVO_;

    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}