package com.citibank.ods.modules.client.mrdocprvt.action;

import com.citibank.ods.common.action.BaseODSHistoryDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.mrdocprvt.functionality.MrDocPrvtHistDetailFnc;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtHistDetailFncVO;

/**
 * @author m.nakamura
 * 
 * Action da tela de histórico de memória de risco.
 */
public class MrDocPrvtHistDetailAction extends BaseODSHistoryDetailAction
{

  // Parte do nome do módulo ou ação
  private static final String C_SCREEN_NAME = "MrDocPrvt.MrDocPrvtHistDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return MrDocPrvtHistDetailFncVO.class.getName();
  }

  /**
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new MrDocPrvtHistDetailFnc();
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
}