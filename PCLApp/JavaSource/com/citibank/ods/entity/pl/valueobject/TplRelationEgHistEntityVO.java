package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * TplRelationEgHistory
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class TplRelationEgHistEntityVO extends BaseTplRelationEgEntityVO
{

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private String m_recStatCode;

  private Date m_reltnEgRefDate;

  /**
   * Retorna atributo lastAuthDate
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthDate
   * @param lastAuthDate
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * Retorna atributo lastAuthUserId
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthUserId
   * @param lastAuthUserId
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * Retorna atributo recStatCode
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_recStatCode
   * @param recStatCode
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Retorna atributo reltnEgRefDate
   * @author leonardo.nakada
   * @date 15/04/2007
   */
  public Date getReltnEgRefDate()
  {
    return m_reltnEgRefDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_reltnEgRefDate
   * @param reltnEgRefDate
   */
  public void setReltnEgRefDate( Date reltnEgRefDate_ )
  {
    m_reltnEgRefDate = reltnEgRefDate_;
  }
}