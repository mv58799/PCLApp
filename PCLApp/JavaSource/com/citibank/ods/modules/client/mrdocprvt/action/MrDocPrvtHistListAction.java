package com.citibank.ods.modules.client.mrdocprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.mrdocprvt.functionality.MrDocPrvtHistListFnc;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtHistListFncVO;

/**
 * @author m.nakamura
 * 
 * Action da tela de lista de histórico de memória de risco.
 */
public class MrDocPrvtHistListAction extends BaseODSListAction
{

  // Parte do nome do módulo ou ação
  private static final String C_SCREEN_NAME = "MrDocPrvt.MrDocPrvtHistList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return MrDocPrvtHistListFncVO.class.getName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new MrDocPrvtHistListFnc();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO,
   *      java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }
  
  /**
   * Action do Botão Buscar.
   *  
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    MrDocPrvtHistListFncVO fncVO = ( MrDocPrvtHistListFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch(true);

    return getScreenName();
  }
}