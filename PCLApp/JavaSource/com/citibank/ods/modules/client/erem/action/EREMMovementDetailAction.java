/*
 * Created on Apr 24, 2007
 *
 */
package com.citibank.ods.modules.client.erem.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.erem.functionality.EREMMovementDetailFnc;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMMovementDetailFncVO;

/**
 * @author leonardo.nakada
 * 
 */
public class EREMMovementDetailAction extends BaseODSMovementDetailAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "EREM.EREMMovementDetail";

  /**
   * Retorna instancia do Fnc
   */
  protected BaseFnc getFuncionality()
  {
    return new EREMMovementDetailFnc();
  }

  /**
   * Retorna o nome lógica da tela
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
    EREMMovementDetailFnc detailFnc = ( EREMMovementDetailFnc ) getFuncionality();
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
   *  
   */
  public String getFncVOPublishName()
  {
    return EREMMovementDetailFncVO.class.getName();
  }

  /**
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    EREMMovementDetailFncVO fncVO = ( EREMMovementDetailFncVO ) fncVO_;

    fncVO.setFromSearch( true );
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}