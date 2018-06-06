/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.action;

import com.citibank.ods.common.action.BaseODSHistoryDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.er.functionality.ERHistoryDetailFnc;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERHistoryDetailFncVO;

/**
 * @author lfabiano
 * @since 09/12/2008	
 */
public class ERHistoryDetailAction extends BaseODSHistoryDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ER.ERHistoryDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
	return ERHistoryDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
	return new ERHistoryDetailFnc();
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
	return null;
  }
}
