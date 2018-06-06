/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.action;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.action.BaseODSDetailAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.ProdQlfyPrvtDetailFnc;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtDetailFncVO;
import com.citibank.ods.modules.product.prodsubasset.functionality.ProdSubAssetDetailFnc;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.ProdSubAssetDetailFncVO;

/**
 * @author rcoelho 
 */
public class ProdSubAssetDetailAction extends BaseODSDetailAction{

	/*
	   * Parte do nome do módulo ou ação
	   */
	  private static final String C_SCREEN_NAME = "ProdSubAsset.ProdSubAssetDetail";

	  /**
	   * @see com.citibank.ods.commom.action.BaseAction#getFncVOPublishName()
	   */
	  public String getFncVOPublishName()
	  {
		return ProdSubAssetDetailFncVO.class.getName();
	  }

	  /*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.action.BaseODSAction#getODSFuncionality()
	   */
	  protected BaseFnc getFuncionality()
	  {
		return new ProdSubAssetDetailFnc();
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
