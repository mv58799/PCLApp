package com.citibank.ods.modules.client.bkrportfmgmt.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.BkrPortfMgmtMovementDetailFnc;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtMovementDetailFncVO;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementDetailAction extends
    BaseODSMovementDetailAction
{
  private static final String C_LIST_BROKER = "List";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  private static final String C_LIST_BKR_PORTF_MGMT = "ListBkrPortfMgmt";

  private static final String C_UPDATE = "Update";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "BkrPortfMgmt.BkrPortfMgmtMovementDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return BkrPortfMgmtMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new BkrPortfMgmtMovementDetailFnc();
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
    BkrPortfMgmtMovementDetailFnc detailFnc = ( BkrPortfMgmtMovementDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_APPROVAL )
         && ( splittedInvokePath[ 3 ].equals( C_LIST_BKR_PORTF_MGMT ) ) )

    {
      detailFnc.listBkrPortfMgmt( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
              && ( splittedInvokePath[ 3 ].equals( C_LIST_BKR_PORTF_MGMT ) ) )

    {
      detailFnc.listBkrPortfMgmt( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
              && ( splittedInvokePath[ 3 ].equals( C_LIST_BROKER ) ) )
    {
      detailFnc.listBroker( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];

    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
              && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )

    {
      detailFnc.addSelectedBrokers( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];

    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
              && ( splittedInvokePath[ 3 ].equals( C_UPDATE ) ) )
    {
      detailFnc.clearPage( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
    }
    return forwardKey;
  }
}