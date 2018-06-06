package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * TplProdPlayerRoleHistory
 * @author acacio.domingos
 * @date 09/04/2007
 */

public class TplProdPlayerRoleHistEntityVO extends
    BaseTplProdPlayerRoleEntityVO
{

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private Date m_prodPlyrRefDate;

  private String m_recStatCode;

  /**
   * Retorna atributo lastAuthDate
   * @author acacio.domingos
   * @date 09/04/2007
   */
  public Date getlastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthDate
   * @param lastAuthDate
   */
  public void setlastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * Retorna atributo lastAuthUserId
   * @author acacio.domingos
   * @date 09/04/2007
   */
  public String getlastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthUserId
   * @param lastAuthUserId
   */
  public void setlastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * Retorna atributo prodPlyrRefDate
   * @author acacio.domingos
   * @date 09/04/2007
   */
  public Date getprodPlyrRefDate()
  {
    return m_prodPlyrRefDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_prodPlyrRefDate
   * @param prodPlyrRefDate
   */
  public void setprodPlyrRefDate( Date prodPlyrRefDate_ )
  {
    m_prodPlyrRefDate = prodPlyrRefDate_;
  }

  /**
   * Retorna atributo recStatCode
   * @author acacio.domingos
   * @date 09/04/2007
   */
  public String getrecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_recStatCode
   * @param recStatCode
   */
  public void setrecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}