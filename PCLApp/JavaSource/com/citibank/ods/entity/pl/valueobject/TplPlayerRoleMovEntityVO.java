/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerRoleMovEntityVO extends BaseTplPlayerRoleEntityVO
{
  /**
   * Codigo da Operacao realizada no registro: inclusao, alteracao, 
   * exclusao 
   */
  private String opernCode;

  /**
   * @return Returns the opernCode.
   */
  public String getOpernCode()
  {
    return opernCode;
  }

  /**
   * @param opernCode_ The opernCode to set.
   */
  public void setOpernCode( String opernCode_ )
  {
    opernCode = opernCode_;
  }
}