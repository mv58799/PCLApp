package com.citibank.ods.modules.client.curacctprmntinstr.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.CurAcctPrmntInstrListFnc;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 *  
 *@see package com.citibank.ods.modules.client.curacctprmntinstr.action; 
 *@version 1.0
 *@author michele.monteiro,18/06/2007
 *
 */
 
public class CurAcctPrmntInstrListAction extends BaseODSListAction
{
  
  
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CurAcctPrmntInstr.CurAcctPrmntInstrList";

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }
  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return CurAcctPrmntInstrListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CurAcctPrmntInstrListFnc();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }
  
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    CurAcctPrmntInstrListFncVO fncVO = ( CurAcctPrmntInstrListFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch( true );

    return getScreenName();
  }
}
