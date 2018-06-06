/*
 * Created on Mar 12, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.form.BaseAggrProdPrvtListForm;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtListFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseAggrProdPrvtListFnc extends BaseFnc
{
  /*
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtListForm productAggrListForm = (BaseAggrProdPrvtListForm) form_; 
    BaseAggrProdPrvtListFncVO productAggrListFncVO = (BaseAggrProdPrvtListFncVO) fncVO_;
    
    productAggrListFncVO.setPrvtProdAggrCodeSrc(productAggrListForm.getPrvtProdAggrCodeSrc());
    productAggrListFncVO.setPrvtProdAggrTextSrc(productAggrListForm.getPrvtProdAggrTextSrc());
  }

  /*
   * Atualiza a Form com as informacoes do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtListForm productAggrListForm = (BaseAggrProdPrvtListForm) form_; 
    BaseAggrProdPrvtListFncVO productAggrListFncVO = (BaseAggrProdPrvtListFncVO) fncVO_;
    
    productAggrListForm.setPrvtProdAggrCodeSrc(productAggrListFncVO.getPrvtProdAggrCodeSrc());
    productAggrListForm.setPrvtProdAggrTextSrc(productAggrListFncVO.getPrvtProdAggrTextSrc());
    productAggrListForm.setResults(productAggrListFncVO.getResults());
  }
}