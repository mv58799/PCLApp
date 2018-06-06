package com.citibank.ods.entity.pl.valueobject;

/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * TplContactCust
 * @author Hamilton Matos
 * @date 26/03/2007
 */

public class TplContactCustEntityVO extends BaseTplContactCustEntityVO
{

  /**
   * Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
   * 
   * Comment for <code>m_opernCode</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
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

