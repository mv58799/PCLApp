package com.citibank.ods.modules.client.erem.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.erem.functionality.EREMListFnc;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMListFncVO;

/**
 * @author gerson.a.rodrigues
 *  
 */

public class EREMListAction extends BaseODSListAction
{

  private static final String C_CLEAR_PAGE = "ClearPage";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "EREM.EREMList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return EREMListFncVO.class.getName();
  }

  /**
   * Retorna uma instância de um Fnc
   */
  protected BaseFnc getFuncionality()
  {
    return new EREMListFnc();
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
   * Actions adicionais
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    String[] splittedInvokePath = invokePath_.split( "\\." );
    EREMListFnc listFnc = ( EREMListFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_LIST )
         && ( splittedInvokePath[ 3 ].equals( C_CLEAR_PAGE ) ) )

    {
      listFnc.clearPage( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
    }
    return forwardKey;
  }

  /**
   * Realiza a pesquisa
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    EREMListFncVO fncVO = ( EREMListFncVO ) fncVO_;

    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch( true );

    return getScreenName();

  }
}