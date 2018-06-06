package com.citibank.ods.modules.client.relationeg.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.relationeg.functionality.RelationEgDetailFnc;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */

public class RelationEgDetailAction extends BaseODSDetailAction
{
  private static final String C_DELETE_DOMAIN = "DeleteDomain";

  private static final String C_INSERT_DOMAIN = "InsertDomain";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "RelationEg.RelationEgDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return RelationEgDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new RelationEgDetailFnc();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /**
   * Actions Extras
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    String[] splittedInvokePath = invokePath_.split( "\\." );
    RelationEgDetailFnc detailFnc = ( RelationEgDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT ) || splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) )
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
    else if ( ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT ) || splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) )
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
    RelationEgDetailFncVO fncVO = ( RelationEgDetailFncVO ) fncVO_;

    fncVO.setFromSearch( true );
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}