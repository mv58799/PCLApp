package com.citibank.ods.modules.client.mrdocprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.mrdocprvt.functionality.MrDocPrvtMovListFnc;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtMovListFncVO;

/**
 * @author m.nakamura
 * 
 * Action da tela de lista de movimento de mem�ria de risco.
 */
public class MrDocPrvtMovListAction extends BaseODSListAction
{

  //Parte do nome do m�dulo ou a��o
  private static final String C_SCREEN_NAME = "MrDocPrvt.MrDocPrvtMovList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return MrDocPrvtMovListFncVO.class.getName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new MrDocPrvtMovListFnc();
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
   * Action do Bot�o Buscar.
   *  
   */
  protected String searchActions( BaseFncVO fncVO_, String invokePath_ )
  {
    MrDocPrvtMovListFncVO fncVO = ( MrDocPrvtMovListFncVO ) fncVO_;
    String[] splittedInvokePath = invokePath_.split( "\\." );
    String nextPage = splittedInvokePath[ 3 ] + C_SPLITTER_CHAR
                      + splittedInvokePath[ 4 ];
    fncVO.setClickedSearch( nextPage );
    fncVO.setFromSearch(true);

    return getScreenName();
  }
}