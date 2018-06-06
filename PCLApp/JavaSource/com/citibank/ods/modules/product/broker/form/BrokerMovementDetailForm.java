package com.citibank.ods.modules.product.broker.form;

/**
 * @author Hamilton Matos
 *  
 */

public class BrokerMovementDetailForm extends BaseBrokerDetailForm
{

  //Ação do registro( Insert, Delete, Update)
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