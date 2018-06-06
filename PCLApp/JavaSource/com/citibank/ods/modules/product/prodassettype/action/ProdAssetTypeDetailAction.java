/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.action;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodassettype.functionality.ProdAssetTypeDetailFnc;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.ProdAssetTypeDetailFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.ProdQlfyPrvtDetailFnc;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtDetailFncVO;

/**
 * @author rcoelho 
 */
public class ProdAssetTypeDetailAction extends BaseODSDetailAction{

	/*
	   * Parte do nome do módulo ou ação
	   */
	  private static final String C_SCREEN_NAME = "ProdAssetType.ProdAssetTypeDetail";

	  /**
	   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
	   */
	  public String getFncVOPublishName()
	  {
		return ProdAssetTypeDetailFncVO.class.getName();
	  }

	  /*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
	   */
	  protected BaseFnc getFuncionality()
	  {
		return new ProdAssetTypeDetailFnc();
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
