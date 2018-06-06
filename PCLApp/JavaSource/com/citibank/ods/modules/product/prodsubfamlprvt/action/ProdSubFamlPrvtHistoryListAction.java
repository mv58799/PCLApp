package com.citibank.ods.modules.product.prodsubfamlprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.ProdSubFamlPrvtHistoryListFnc;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtHistoryListFncVO;

/**
 * @author leonardo.nakada
 *
 */

public class ProdSubFamlPrvtHistoryListAction extends BaseODSListAction
{

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProdSubFamlPrvt.ProdSubFamlPrvtHistoryList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProdSubFamlPrvtHistoryListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProdSubFamlPrvtHistoryListFnc();
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
