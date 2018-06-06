package com.citibank.ods.modules.client.relationeg.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.relationeg.functionality.RelationEgMovementDetailFnc;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgMovementDetailFncVO;

/**
 * @author leonardo.nakada
 *
 */

public class RelationEgMovementDetailAction extends BaseODSMovementDetailAction
{
  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "RelationEg.RelationEgMovementDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return RelationEgMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new RelationEgMovementDetailFnc();
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
    RelationEgMovementDetailFnc detailFnc = ( RelationEgMovementDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
         && ( splittedInvokePath[ 3 ].equals( C_INSERT_DOMAIN ) ) )
    {
      detailFnc.insertDomain( fncVO_ );

      if ( !fncVO_.hasErrors() )
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
      }
      else
      {
        forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[ 2 ];
      }
    }
    else if ( splittedInvokePath[ 2 ].equals( C_MODE_UPDATE )
              && ( splittedInvokePath[ 3 ].equals( C_DELETE_DOMAIN ) ) )
    {
      detailFnc.deleteDomain( fncVO_ );

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
  

  /**
   * Realiza a pesquisa por ReltnNbr
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    RelationEgMovementDetailFncVO fncVO = ( RelationEgMovementDetailFncVO ) fncVO_;

    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}