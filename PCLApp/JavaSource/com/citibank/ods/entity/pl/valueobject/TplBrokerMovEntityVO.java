package com.citibank.ods.entity.pl.valueobject;

/**
 * @author Hamilton Matos
 */
public class TplBrokerMovEntityVO extends BaseTplBrokerEntityVO
{
  //Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
  private String m_opernCode;

  public String getOpernCode()
  {
    return m_opernCode;
  }

  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}