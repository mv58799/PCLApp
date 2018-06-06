/*
 * Created on Mar 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.form.BaseProdQlfyPrvtListForm;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.BaseProdQlfyPrvtListFncVO;

/**
 * @author fernando.salgado
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProdQlfyPrvtListFnc extends BaseFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdQlfyPrvtListForm baseProdQlfyPrvtListForm = ( BaseProdQlfyPrvtListForm ) form_;
    BaseProdQlfyPrvtListFncVO baseProdQlfyPrvtListFncVO = ( BaseProdQlfyPrvtListFncVO ) fncVO_;

    if ( baseProdQlfyPrvtListForm.getProdQlfyCodeSrc() != null
         && !"".equals( baseProdQlfyPrvtListForm.getProdQlfyCodeSrc() ) )
    {
      baseProdQlfyPrvtListFncVO.setProdQlfyCodeSrc( new BigInteger(
                                                                    baseProdQlfyPrvtListForm.getProdQlfyCodeSrc() ) );
    }
    else
    {
      baseProdQlfyPrvtListFncVO.setProdQlfyCodeSrc( null );
    }
    baseProdQlfyPrvtListFncVO.setProdQlfyTextSrc( baseProdQlfyPrvtListForm.getProdQlfyTextSrc() );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdQlfyPrvtListForm baseProdQlfyPrvtListForm = ( BaseProdQlfyPrvtListForm ) form_;
    BaseProdQlfyPrvtListFncVO baseProdQlfyPrvtListFncVO = ( BaseProdQlfyPrvtListFncVO ) fncVO_;

    if ( baseProdQlfyPrvtListFncVO.getProdQlfyCodeSrc() != null )
    {
      baseProdQlfyPrvtListForm.setProdQlfyCodeSrc( baseProdQlfyPrvtListFncVO.getProdQlfyCodeSrc().toString() );
    }
    baseProdQlfyPrvtListForm.setProdQlfyTextSrc( baseProdQlfyPrvtListFncVO.getProdQlfyTextSrc() );
    baseProdQlfyPrvtListForm.setResults( baseProdQlfyPrvtListFncVO.getResults() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */

}