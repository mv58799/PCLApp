package com.citibank.ods.modules.client.erem.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.erem.functionality.EREMDetailFnc;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMDetailFncVO;

/**
 * @author gerson.a.rodrigues
 *
 */

public class EREMDetailAction extends BaseODSDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "EREM.EREMDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return EREMDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new EREMDetailFnc();
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
    EREMDetailFnc detailFnc = ( EREMDetailFnc ) getFuncionality();
    String forwardKey = "";

    if ( ( splittedInvokePath[ 2 ].equals( C_MODE_INSERT ) || splittedInvokePath[ 2 ].equals( C_MODE_UPDATE ) )
         && ( splittedInvokePath[ 3 ].equals( C_ACTION_INSERT_DOMAIN ) ) )
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
              && ( splittedInvokePath[ 3 ].equals( C_ACTION_DELETE_DOMAIN ) ) )
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
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    EREMDetailFncVO fncVO = ( EREMDetailFncVO ) fncVO_;
    fncVO.setFromSearch(true);
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}