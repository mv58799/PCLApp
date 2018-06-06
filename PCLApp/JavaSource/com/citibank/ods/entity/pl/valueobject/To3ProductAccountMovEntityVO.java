package com.citibank.ods.entity.pl.valueobject;

/**
 * Representa��o da tabela To3ProductAccountMov 
 * @author michele.monteiro,16/05/2007
 */

public class To3ProductAccountMovEntityVO extends BaseTo3ProductAccountEntityVO
{

  //A��o do registro
  private String m_opernCode = "";

  /**
   * @return Retorna a a��o do registro.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_.Seta a a��o do registro.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}