package com.citibank.ods.modules.product.product.action;

import com.citibank.ods.common.action.BaseODSListAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.product.functionality.ProductListFnc;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductListFncVO;

/**
 * @author fernando.salgado
 *  
 */

public class ProductListAction extends BaseODSListAction
{

  private static final String C_CLEAR_PAGE = "ClearPage";

  /*
   * Parte do nome do módulo ou ação
   */
  private static final String C_SCREEN_NAME = "Product.ProductList";

  /**
   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
   */
  public String getFncVOPublishName()
  {
    return ProductListFncVO.class.getName();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProductListFnc();
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
    String[] splittedInvokePath = invokePath_.split( "\\." );
    ProductListFnc listFnc = ( ProductListFnc ) getFuncionality();
    String forwardKey = "";

    if ( splittedInvokePath[ 2 ].equals( C_MODE_LIST )
         && ( splittedInvokePath[ 3 ].equals( C_CLEAR_PAGE ) ) )

    {
      listFnc.clearPage( fncVO_ );

      forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;

    }
    return forwardKey;
  }
}