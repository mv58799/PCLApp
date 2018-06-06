package com.citibank.ods.modules.client.curacctprmntinstr.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.CurAcctPrmntInstrHistoryListFnc;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrHistoryListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.action;
 * @version 1.0
 * @author michele.monteiro,19/06/2007
 *  
 */

public class CurAcctPrmntInstrHistoryListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CurAcctPrmntInstr.CurAcctPrmntInstrHistoryList";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CurAcctPrmntInstrHistoryListFnc();
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
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {

    return CurAcctPrmntInstrHistoryListFncVO.class.getName();
  }

  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    CurAcctPrmntInstrHistoryListFncVO fncVO = ( CurAcctPrmntInstrHistoryListFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch( true );

    return getScreenName();
  }
}