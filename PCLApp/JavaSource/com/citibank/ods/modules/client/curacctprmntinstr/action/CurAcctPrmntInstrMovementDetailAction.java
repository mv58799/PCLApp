package com.citibank.ods.modules.client.curacctprmntinstr.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.CurAcctPrmntInstrMovementDetailFnc;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrMovementDetailFncVO;

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

public class CurAcctPrmntInstrMovementDetailAction extends
    BaseODSMovementDetailAction
{

  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CurAcctPrmntInstrMovementDetailFnc();
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

    return CurAcctPrmntInstrMovementDetailFncVO.class.getName();
  }

  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    String[] splittedInvokePath = invokePath_.split( "\\." );
    CurAcctPrmntInstrMovementDetailFnc detailFnc = ( CurAcctPrmntInstrMovementDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
         && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )

    {
      detailFnc.insertIP( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
         && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )

    {
      detailFnc.deleteIP( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    return forwardKey;

  }

  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch( true );
    fncVO.setRelation(false);

    return getScreenName();
  }

}