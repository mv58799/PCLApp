package com.citibank.ods.entity.pl.valueobject;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author michele.monteiro,13/06/2007
 *  
 */

public class TplCurAcctPrmntInstrMovEntityVO extends
    BaseTplCurAcctPrmntInstrEntityVO
{

  private String m_opernCode;

  /**
   * Retorna atributo opernCode
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_opernCode
   * @param opernCode
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }

}