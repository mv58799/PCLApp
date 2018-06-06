package com.citibank.ods.entity.pl.valueobject;

/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * TplRelationEgMovement
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class TplRelationEgMovEntityVO extends BaseTplRelationEgEntityVO
{

  private String m_opernCode;

  /**
   * Retorna atributo opernCode
   * @author leonardo.nakada
   * @date 15/04/2007
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