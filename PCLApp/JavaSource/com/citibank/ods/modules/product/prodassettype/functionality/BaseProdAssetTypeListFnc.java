/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodassettype.form.BaseProdAssetTypeListForm;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.BaseProdAssetTypeListFncVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdAssetTypeListFnc extends BaseFnc{

	/*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdAssetTypeListForm baseProdAssetTypeListForm = ( BaseProdAssetTypeListForm ) form_;
		BaseProdAssetTypeListFncVO baseProdAssetTypeListFncVO = ( BaseProdAssetTypeListFncVO ) fncVO_;

		if ( baseProdAssetTypeListForm.getProdAssetTypeCodeSrc() != null
			 && !"".equals( baseProdAssetTypeListForm.getProdAssetTypeCodeSrc() ) )
		{
		  baseProdAssetTypeListFncVO.setProdAssetTypeCodeSrc( new BigInteger(
																		baseProdAssetTypeListForm.getProdAssetTypeCodeSrc() ) );
		}
		else
		{
		  baseProdAssetTypeListFncVO.setProdAssetTypeCodeSrc( null );
		}
		baseProdAssetTypeListFncVO.setProdAssetTypeTextSrc( baseProdAssetTypeListForm.getProdAssetTypeTextSrc() );

	  }

	  /*
	   * (non-Javadoc)
	   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	   */
	  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
	  {
		BaseProdAssetTypeListForm baseProdAssetTypeListForm = ( BaseProdAssetTypeListForm ) form_;
		BaseProdAssetTypeListFncVO baseProdAssetTypeListFncVO = ( BaseProdAssetTypeListFncVO ) fncVO_;

		if ( baseProdAssetTypeListFncVO.getProdAssetTypeCodeSrc() != null )
		{
		  baseProdAssetTypeListForm.setProdAssetTypeCodeSrc( baseProdAssetTypeListFncVO.getProdAssetTypeCodeSrc().toString() );
		}
		baseProdAssetTypeListForm.setProdAssetTypeTextSrc( baseProdAssetTypeListFncVO.getProdAssetTypeTextSrc() );
		baseProdAssetTypeListForm.setResults( baseProdAssetTypeListFncVO.getResults() );
	  }
}
