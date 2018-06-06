package com.citibank.ods.modules.client.erem.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.erem.functionality.EREMMovementListFnc;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMMovementListFncVO;

/**
 * @author gerson.a.rodrigues
 *  
 */

public class EREMMovementListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "EREM.EREMMovementList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return EREMMovementListFncVO.class.getName();
  }

  /**
   * Retorna uma instância de um Fnc
   */
  protected BaseFnc getFuncionality()
  {
    return new EREMMovementListFnc();
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
    return null;
  }

  /**
   * Realiza a pesquisa por ReltnNbr
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    EREMMovementListFncVO fncVO = ( EREMMovementListFncVO ) fncVO_;
    fncVO.setFromSearch( true );
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );

    return getScreenName();

  }
}