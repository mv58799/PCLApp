/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.er.functionality.ERMovementDetailFnc;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERMovementDetailFncVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERMovementDetailAction extends BaseODSMovementDetailAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ER.ERMovementDetail";

  /**
   * Retorna instancia do Fnc
   */
  protected BaseFnc getFuncionality()
  {
	return new ERMovementDetailFnc();
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
  	return null;
  }

  /**
   *  
   */
  public String getFncVOPublishName()
  {
	return ERMovementDetailFncVO.class.getName();
  }
  
}