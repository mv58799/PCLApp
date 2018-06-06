/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.form.BaseProductFamilyPrvtListForm;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.BaseProductFamilyPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProductFamilyPrvtListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com as informações vindas do Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductFamilyPrvtListFncVO prvtListFncVO = ( BaseProductFamilyPrvtListFncVO ) fncVO_;
    BaseProductFamilyPrvtListForm prvtListForm = ( BaseProductFamilyPrvtListForm ) form_;

    String formProdFamlCodeSrc = prvtListForm.getProdFamlCodeSrc();
    if ( formProdFamlCodeSrc != null && !formProdFamlCodeSrc.equals( "" ) )
    {
      prvtListFncVO.setProdFamlCodeSrc( new BigInteger( formProdFamlCodeSrc ) );
    }
    else
    {
      prvtListFncVO.setProdFamlCodeSrc( null );
    }
    prvtListFncVO.setProdFamlNameSrc( prvtListForm.getProdFamlNameSrc() );
    prvtListFncVO.setProdFamlTextSrc( prvtListForm.getProdFamlTextSrc() );
  }

  /**
   * Atualiza os atributos do Form com as informações vindas do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductFamilyPrvtListFncVO prvtListFncVO = ( BaseProductFamilyPrvtListFncVO ) fncVO_;
    BaseProductFamilyPrvtListForm prvtListForm = ( BaseProductFamilyPrvtListForm ) form_;

    if ( prvtListFncVO.getProdFamlCodeSrc() != null )
    {
      prvtListForm.setProdFamlCodeSrc( prvtListFncVO.getProdFamlCodeSrc().toString() );
    }
    else
    {
      prvtListForm.setProdFamlCodeSrc( null );
    }
    prvtListForm.setProdFamlNameSrc( prvtListFncVO.getProdFamlNameSrc() );
    prvtListForm.setProdFamlTextSrc( prvtListFncVO.getProdFamlTextSrc() );
    prvtListForm.setResults( prvtListFncVO.getResults() );
  }

}