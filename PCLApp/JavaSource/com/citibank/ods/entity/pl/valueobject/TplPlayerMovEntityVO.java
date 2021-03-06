/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerMovEntityVO extends BaseTplPlayerEntityVO
{
  /**
   * Codigo da Operacao realizada no registro: inclusao, alteracao, 
   * exclusao
   */
  private String m_opernCode;

  /**
   * @return Returns the opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ The opernCode to set.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}