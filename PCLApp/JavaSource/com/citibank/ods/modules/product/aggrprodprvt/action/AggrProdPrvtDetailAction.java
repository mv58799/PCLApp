/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.action;

import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.AggrProdPrvtDetailFnc;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class AggrProdPrvtDetailAction extends BaseODSDetailAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "AggrProdPrvt.AggrProdPrvtDetail";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new AggrProdPrvtDetailFnc();
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

  /**
   * Retorna o nome da classe FncVO
   * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return AggrProdPrvtDetailFncVO.class.getName();
  }

}