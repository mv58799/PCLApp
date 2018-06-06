/*
 * Created on 01/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodasset.functionality.ProdAssetMovementDetailFnc;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.ProdAssetMovementDetailFncVO;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProdAssetMovementDetailAction extends BaseODSMovementDetailAction
{

	/*
	  * Parte do nome do módulo ou ação
	  */
	 private static final String C_SCREEN_NAME = "ProdAsset.ProdAssetMovementDetail";

	 /* (non-Javadoc)
	  * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
	  */
	 protected BaseFnc getFuncionality()
	 {
	   return new ProdAssetMovementDetailFnc();	 
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
	   return ProdAssetMovementDetailFncVO.class.getName();	   
	 }


}
