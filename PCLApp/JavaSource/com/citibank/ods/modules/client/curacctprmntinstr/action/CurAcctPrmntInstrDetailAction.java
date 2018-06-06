package com.citibank.ods.modules.client.curacctprmntinstr.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.BaseCurAcctPrmntInstrListFnc;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.CurAcctPrmntInstrDetailFnc;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrDetailFncVO;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrDetailAction extends BaseODSDetailAction
{

  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "CurAcctPrmntInstr.CurAcctPrmntInstrDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return CurAcctPrmntInstrDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new CurAcctPrmntInstrDetailFnc();
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
    CurAcctPrmntInstrDetailFnc detailFnc = ( CurAcctPrmntInstrDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
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

    if ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT )
         && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )

    {
      detailFnc.deleteIp( fncVO_ );

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
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
	BaseCurAcctPrmntInstrListFnc baseCurAcctPrmntInstrListFnc = new  BaseCurAcctPrmntInstrListFnc(); 
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];

    fncVO.setClickedSearch( nextPage );
	
    fncVO.setFromSearch( true );
	
    return getScreenName();
  }

}