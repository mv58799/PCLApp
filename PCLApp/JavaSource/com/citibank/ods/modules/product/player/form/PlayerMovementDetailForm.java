package com.citibank.ods.modules.product.player.form;

import com.citibank.ods.modules.product.player.form.BasePlayerDetailForm;

/**
*@author atilio.l.araujo
*
*/

public class PlayerMovementDetailForm extends BasePlayerDetailForm
{
  
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
