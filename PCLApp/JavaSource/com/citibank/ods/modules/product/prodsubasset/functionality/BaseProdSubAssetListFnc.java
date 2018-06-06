/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodsubasset.form.BaseProdSubAssetListForm;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.BaseProdSubAssetListFncVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdSubAssetListFnc extends BaseFnc{

	/*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdSubAssetListForm baseProdSubAssetListForm = ( BaseProdSubAssetListForm ) form_;
		BaseProdSubAssetListFncVO baseProdSubAssetListFncVO = ( BaseProdSubAssetListFncVO ) fncVO_;

		if ( baseProdSubAssetListForm.getProdSubAssetCodeSrc() != null
			 && !"".equals( baseProdSubAssetListForm.getProdSubAssetCodeSrc() ) )
		{
		  baseProdSubAssetListFncVO.setProdSubAssetCodeSrc( new BigInteger(
																		baseProdSubAssetListForm.getProdSubAssetCodeSrc() ) );
		}
		else
		{
		  baseProdSubAssetListFncVO.setProdSubAssetCodeSrc( null );
		}
		baseProdSubAssetListFncVO.setProdSubAssetTextSrc( baseProdSubAssetListForm.getProdSubAssetTextSrc() );

	  }

	  /*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdSubAssetListForm baseProdSubAssetListForm = ( BaseProdSubAssetListForm ) form_;
		BaseProdSubAssetListFncVO baseProdSubAssetListFncVO = ( BaseProdSubAssetListFncVO ) fncVO_;

		if ( baseProdSubAssetListFncVO.getProdSubAssetCodeSrc() != null )
		{
		  baseProdSubAssetListForm.setProdSubAssetCodeSrc( baseProdSubAssetListFncVO.getProdSubAssetCodeSrc().toString() );
		}
		baseProdSubAssetListForm.setProdSubAssetTextSrc( baseProdSubAssetListFncVO.getProdSubAssetTextSrc() );
		baseProdSubAssetListForm.setResults( baseProdSubAssetListFncVO.getResults() );
	  }
}
