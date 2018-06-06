package com.citibank.ods.modules.client.ipdocprvt.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.transaction.ManagedUserTransaction;
import com.citibank.ods.modules.client.ipdocprvt.functionality.IpDocPrvtMovementDetailFnc;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtMovementDetailFncVO;

/**
 * @author l.braga
 *  
 */

public class IpDocPrvtMovementDetailAction extends BaseODSMovementDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "IpDocPrvt.IpDocPrvtMovementDetail";

  private String C_INSERT_CALLBACK = "InsertCallback";

  private String C_DELETE_CALLBACK = "DeleteCallback";

  private String C_INSERT_DOCTRANSFER = "InsertDocTransfer";

  private String C_DELETE_DOCTRANSFER = "DeleteDocTransfer";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return IpDocPrvtMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new IpDocPrvtMovementDetailFnc();
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
    IpDocPrvtMovementDetailFnc detailFnc = ( IpDocPrvtMovementDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 3 ].equals( C_DELETE_DOCTRANSFER ) )
    {
      detailFnc.deleteDocTransfer( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 3 ].equals( C_INSERT_DOCTRANSFER ) )
    {
      detailFnc.insertDocTransfer( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 3 ].equals( C_DELETE_CALLBACK ) )
    {
      detailFnc.deleteCallback( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    if ( splittedInvokePath[ 3 ].equals( C_INSERT_CALLBACK ) )
    {
	  
      
	  ManagedUserTransaction transaction = getUserTransaction();
      
      detailFnc.insertCallback( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
		commitTransaction( transaction );
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
		rollbackTransaction( transaction );
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }

    return forwardKey;
  }

  /**
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch(true);

    return getScreenName();

  }
}