
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.form.BaseProdRiskCatPrvtListForm;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.BaseProdRiskCatPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProdRiskCatPrvtListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdRiskCatPrvtListForm baseProdRiskCatPrvtListForm = ( BaseProdRiskCatPrvtListForm ) form_;
    BaseProdRiskCatPrvtListFncVO baseProdRiskCatPrvtListFncVO = ( BaseProdRiskCatPrvtListFncVO ) fncVO_;

    if ( baseProdRiskCatPrvtListForm.getProdRiskCatCodeSrc() != null
         && !"".equals( baseProdRiskCatPrvtListForm.getProdRiskCatCodeSrc() ) )
    {
      baseProdRiskCatPrvtListFncVO.setProdRiskCatCodeSrc( new BigInteger(
                                                                          baseProdRiskCatPrvtListForm.getProdRiskCatCodeSrc() ) );
    }
    else
    {
      baseProdRiskCatPrvtListFncVO.setProdRiskCatCodeSrc( null );
    }
    baseProdRiskCatPrvtListFncVO.setProdRiskCatTextSrc( baseProdRiskCatPrvtListForm.getProdRiskCatTextSrc() );
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdRiskCatPrvtListForm baseProdRiskCatPrvtListForm = ( BaseProdRiskCatPrvtListForm ) form_;
    BaseProdRiskCatPrvtListFncVO baseProdRiskCatPrvtListFncVO = ( BaseProdRiskCatPrvtListFncVO ) fncVO_;

    if ( baseProdRiskCatPrvtListFncVO.getProdRiskCatCodeSrc() != null )
    {
      baseProdRiskCatPrvtListForm.setProdRiskCatCodeSrc( baseProdRiskCatPrvtListFncVO.getProdRiskCatCodeSrc().toString() );
    }
    else{
      baseProdRiskCatPrvtListForm.setProdRiskCatCodeSrc( null );
    }
    baseProdRiskCatPrvtListForm.setProdRiskCatTextSrc( baseProdRiskCatPrvtListFncVO.getProdRiskCatTextSrc() );
    baseProdRiskCatPrvtListForm.setResults( baseProdRiskCatPrvtListFncVO.getResults() );
  }
}