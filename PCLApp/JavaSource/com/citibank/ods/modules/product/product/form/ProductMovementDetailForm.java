package com.citibank.ods.modules.product.product.form;

import com.citibank.ods.modules.product.product.form.BaseProductDetailForm;

/**
*@author leonardo.nakada
*
*/

public class ProductMovementDetailForm extends BaseProductDetailForm
{
	
  // TODO: Coloque seu comentário aqui.
  private String m_opernCode = "";

  /**
  * @return Returns m_opernCode.
  */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
  * @param opernCode_ Field m_opernCode to be setted.
  */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}
