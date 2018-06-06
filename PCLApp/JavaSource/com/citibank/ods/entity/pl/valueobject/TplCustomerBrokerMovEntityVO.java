package com.citibank.ods.entity.pl.valueobject;


/**
 * 
 * @see package
 *      com.citibank.ods.modules.client.customerbroker.functionality.valueobject;
 * @author Hamilton Matos,Jul 26, 2007
 *  
 */

public class TplCustomerBrokerMovEntityVO extends BaseTplCustomerBrokerEntityVO
{
  /**
   * Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
   */
  private String m_opernCode;

  /**
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}