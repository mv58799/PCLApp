package com.citibank.ods.modules.product.broker.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.broker.form.BaseBrokerListForm;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BaseBrokerListFncVO;

/**
 * @author Hamilton Matos
 *  
 */
public abstract class BaseBrokerListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBrokerListForm baseBrokerListForm = ( BaseBrokerListForm ) form_;
    BaseBrokerListFncVO baseBrokerListFncVO = ( BaseBrokerListFncVO ) fncVO_;

    baseBrokerListFncVO.setBkrCnpjNbrSrc( baseBrokerListForm.getBkrCnpjNbrSrc() );
    baseBrokerListFncVO.setBkrNameTextSrc( baseBrokerListForm.getBkrNameTextSrc() );

  }

  /**
   * Atualiza os atributos do Form com os atributos vindos da FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBrokerListForm baseBrokerListForm = ( BaseBrokerListForm ) form_;
    BaseBrokerListFncVO baseBrokerListFncVO = ( BaseBrokerListFncVO ) fncVO_;

    baseBrokerListForm.setBkrCnpjNbrSrc( baseBrokerListFncVO.getBkrCnpjNbrSrc() );
    baseBrokerListForm.setBkrNameTextSrc( baseBrokerListFncVO.getBkrNameTextSrc() );
    baseBrokerListForm.setResults( baseBrokerListFncVO.getResults() );

  }

}