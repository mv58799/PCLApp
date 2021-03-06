package com.citibank.ods.modules.product.product.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.product.functionality.ProductDetailFnc;
import com.citibank.ods.modules.product.product.functionality.ProductMovementDetailFnc;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductMovementDetailFncVO;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.product.product.action; 
 *@version 1.0
 *@author atilio.l.araujo,Apr 19, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class ProductMovementDetailAction extends BaseODSMovementDetailAction
{

  /*
   * Parte do nome do m�dulo ou a��o
   */
  private static final String C_SCREEN_NAME = "Product.ProductMovementDetail";
  
  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
   */
  protected BaseFnc getFuncionality()
  {
    return new ProductMovementDetailFnc();
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#getScreenName()
   */
  protected String getScreenName()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return C_SCREEN_NAME;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.action.BaseODSAction#extraActions(com.citibank.ods.common.functionality.valueobject.BaseFncVO, java.lang.String)
   */
  protected String extraActions(BaseFncVO fncVO_, String invokePath_) {

		String forwardKey = "";

		String[] splittedInvokePath = null;
		splittedInvokePath = invokePath_.split("\\.");

		String invokePathMode = splittedInvokePath[2];
		String invokePathAction = splittedInvokePath[3];

		ProductMovementDetailFnc productDetailFnc = (ProductMovementDetailFnc) getFuncionality();

		// EMISSOR UPDATE - INSERT EMISSOR
		if (invokePathMode.equals(C_MODE_UPDATE) && (invokePathAction.equals(C_MODE_INSERT_EMISSOR))) {

			productDetailFnc.insertEmissor(fncVO_);

			if (!fncVO_.hasErrors()) {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;

			} else {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + invokePathMode;
			}
		}

		// EMISSOR UPDATE - DELETE EMISSOR
		if (invokePathMode.equals(C_MODE_UPDATE) && (invokePathAction.equals(C_MODE_DELETE_EMISSOR))) {

			productDetailFnc.deleteEmissor(fncVO_);

			if (!fncVO_.hasErrors()) {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
			} else {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + invokePathMode;
			}
		}

		return forwardKey;
	}

  /*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.common.action.BaseAction#getFncVOPublishName()
	 */
  public String getFncVOPublishName()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return ProductMovementDetailFncVO.class.getName();
  }

}
