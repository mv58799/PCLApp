package com.citibank.ods.modules.product.prodsubfamlprvt.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.ProdSubFamlPrvtMovementDetailFnc;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtMovementDetailFncVO;

/**
 * @author fernando.salgado
 *
 */

public class ProdSubFamlPrvtMovementDetailAction extends BaseODSMovementDetailAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProdSubFamlPrvtMovementDetailFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProdSubFamlPrvtMovementDetailFnc();
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
