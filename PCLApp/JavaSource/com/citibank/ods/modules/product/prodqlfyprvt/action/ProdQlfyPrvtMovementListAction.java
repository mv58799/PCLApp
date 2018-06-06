package com.citibank.ods.modules.product.prodqlfyprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.ProdQlfyPrvtMovementListFnc;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtMovementListFncVO;

/**
 * @author fernando.salgado
 *
 */

public class ProdQlfyPrvtMovementListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProdQlfyPrvt.ProdQlfyPrvtMovementList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProdQlfyPrvtMovementListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProdQlfyPrvtMovementListFnc();
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
