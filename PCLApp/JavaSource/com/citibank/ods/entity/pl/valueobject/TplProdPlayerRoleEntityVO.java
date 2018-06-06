package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * TplProdPlayerRole
 * @author acacio.domingos
 * @date 09/04/2007
 */

public class TplProdPlayerRoleEntityVO extends BaseTplProdPlayerRoleEntityVO
{

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private String m_opernCode;

  private String m_recStatCode;

  /**
   * Retorna atributo lastAuthDate
   * @author acacio.domingos
   * @date 09/04/2007
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
   * @author acacio.domingos
   * @date 09/04/2007
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
   * Retorna atributo opernCode
   * @author acacio.domingos
   * @date 09/04/2007
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

  /**
   * Retorna atributo recStatCode
   * @author acacio.domingos
   * @date 09/04/2007
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
}