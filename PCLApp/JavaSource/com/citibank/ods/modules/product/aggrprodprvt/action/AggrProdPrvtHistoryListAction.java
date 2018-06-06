/*
 * Created on Mar 12, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.aggrprodprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.AggrProdPrvtHistoryListFnc;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtHistoryListFncVO;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AggrProdPrvtHistoryListAction extends BaseODSListAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "AggrProdPrvt.AggrProdPrvtHistoryList";
  
  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new AggrProdPrvtHistoryListFnc();
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    return C_SCREEN_NAME;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
   */
  protected String extraActions( BaseFncVO fncVO_, String invokePath_ )
  {
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return AggrProdPrvtHistoryListFncVO.class.getName();
  }

}
