/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.ProductFamilyPrvtMovementListFnc;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtMovementListFncVO;

/**
 * @author leonardo.nakada
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductFamilyPrvtMovementListAction extends BaseODSListAction
{
  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "ProductFamilyPrvt.ProductFamilyPrvtMovementList";

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProductFamilyPrvtMovementListFnc();
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
    return ProductFamilyPrvtMovementListFncVO.class.getName();
  }

}