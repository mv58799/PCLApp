package com.citibank.ods.modules.client.erem.form;

import com.citibank.ods.modules.client.erem.form.BaseEREMDetailForm;

/**
 * @author l.braga
 *  
 */

public class EREMMovementDetailForm extends BaseEREMDetailForm
{
  public static final int COL_POS_OPERN_CODE = 6;

  // 
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