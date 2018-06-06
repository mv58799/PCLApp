/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodsubfamlprvt.form.BaseProdSubFamlPrvtListForm;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.BaseProdSubFamlPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProdSubFamlPrvtListFnc extends BaseFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdSubFamlPrvtListFncVO prvtListFncVO = ( BaseProdSubFamlPrvtListFncVO ) fncVO_;
    BaseProdSubFamlPrvtListForm prvtListForm = ( BaseProdSubFamlPrvtListForm ) form_;

    String formProdSubFamlCodeSrc = prvtListForm.getProdSubFamlCodeSrc();
    if ( formProdSubFamlCodeSrc != null && !formProdSubFamlCodeSrc.equals( "" ) )
    {
      prvtListFncVO.setProdSubFamlCodeSrc( new BigInteger(
                                                           formProdSubFamlCodeSrc ) );
    }
    else
    {
      prvtListFncVO.setProdSubFamlCodeSrc( null );
    }

    prvtListFncVO.setProdSubFamlNameSrc( prvtListForm.getProdSubFamlNameSrc() );
    prvtListFncVO.setProdSubFamlTextSrc( prvtListForm.getProdSubFamlTextSrc() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdSubFamlPrvtListFncVO prvtListFncVO = ( BaseProdSubFamlPrvtListFncVO ) fncVO_;
    BaseProdSubFamlPrvtListForm prvtListForm = ( BaseProdSubFamlPrvtListForm ) form_;

    if ( prvtListFncVO.getProdSubFamlCodeSrc() != null )
    {
      prvtListForm.setProdSubFamlCodeSrc( prvtListFncVO.getProdSubFamlCodeSrc().toString() );
    }
    else
    {
      prvtListForm.setProdSubFamlCodeSrc( null );
    }

    prvtListForm.setProdSubFamlNameSrc( prvtListFncVO.getProdSubFamlNameSrc() );
    prvtListForm.setProdSubFamlTextSrc( prvtListFncVO.getProdSubFamlTextSrc() );
    prvtListForm.setResults( prvtListFncVO.getResults() );
  }
}