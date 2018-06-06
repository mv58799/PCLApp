/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodsubasset.functionality.ProdSubAssetMovementDetailFnc;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetMovementDetailFncVO;

/**
 * @author lfabiano
 * @since 06/10/2008
 */
public class ProdSubAssetMovementDetailAction extends BaseODSMovementDetailAction
{

	/*
	  * Parte do nome do módulo ou ação
	  */
	 private static final String C_SCREEN_NAME = "ProdSubAsset.ProdSubAssetMovementDetail";

	 /* (non-Javadoc)
	  * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
	  */
	 protected BaseFnc getFuncionality()
	 {
	   return new ProdSubAssetMovementDetailFnc();	 
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
	   return ProdSubAssetMovementDetailFncVO.class.getName();	   
	 }


}
