/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.action;

import com.citibank.ods.common.action.BaseODSMovementDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodassettype.functionality.ProdAssetTypeMovementDetailFnc;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.ProdAssetTypeMovementDetailFncVO;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class ProdAssetTypeMovementDetailAction extends BaseODSMovementDetailAction
{

	/*
	  * Parte do nome do m�dulo ou a��o
	  */
	 private static final String C_SCREEN_NAME = "ProdAssetType.ProdAssetTypeMovementDetail";

	 /* (non-Javadoc)
	  * @see com.citibank.ods.common.action.BaseODSAction#getFuncionality()
	  */
	 protected BaseFnc getFuncionality()
	 {
	   return new ProdAssetTypeMovementDetailFnc();	 
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
	   return ProdAssetTypeMovementDetailFncVO.class.getName();	   
	 }


}
