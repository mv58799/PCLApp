/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodasset.form.BaseProdAssetListForm;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.BaseProdAssetListFncVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdAssetListFnc extends BaseFnc{

	/*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdAssetListForm baseProdAssetListForm = ( BaseProdAssetListForm ) form_;
		BaseProdAssetListFncVO baseProdAssetListFncVO = ( BaseProdAssetListFncVO ) fncVO_;

		if ( baseProdAssetListForm.getProdAssetCodeSrc() != null
			 && !"".equals( baseProdAssetListForm.getProdAssetCodeSrc() ) )
		{
		  baseProdAssetListFncVO.setProdAssetCodeSrc( new BigInteger(
																		baseProdAssetListForm.getProdAssetCodeSrc() ) );
		}
		else
		{
		  baseProdAssetListFncVO.setProdAssetCodeSrc( null );
		}
		baseProdAssetListFncVO.setProdAssetTextSrc( baseProdAssetListForm.getProdAssetTextSrc() );

	  }

	  /*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdAssetListForm baseProdAssetListForm = ( BaseProdAssetListForm ) form_;
		BaseProdAssetListFncVO baseProdAssetListFncVO = ( BaseProdAssetListFncVO ) fncVO_;

		if ( baseProdAssetListFncVO.getProdAssetCodeSrc() != null )
		{
		  baseProdAssetListForm.setProdAssetCodeSrc( baseProdAssetListFncVO.getProdAssetCodeSrc().toString() );
		}
		baseProdAssetListForm.setProdAssetTextSrc( baseProdAssetListFncVO.getProdAssetTextSrc() );
		baseProdAssetListForm.setResults( baseProdAssetListFncVO.getResults() );
	  }
}
