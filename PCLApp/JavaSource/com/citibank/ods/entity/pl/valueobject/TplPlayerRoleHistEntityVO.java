/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerRoleHistEntityVO extends BaseTplPlayerRoleEntityVO
{
  /**
   * Codigo do Usuario que Aprovou o Cadastro do Registro.
   */
  private String m_lastAuthUserId;

  /**
   * Data e Hora que o Usuario aprovou o Registro Cadastrado
   */
  private Date m_lastAuthDate;

  /**
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao
   */
  private String m_recStatCode;

  /**
   *  
   */
  private Date m_plyrRoleRefDate;

  /**
   * @return Returns the lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ The lastAuthDate to set.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns the lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ The lastAuthUserId to set.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns the plyrRoleRefDate.
   */
  public Date getPlyrRoleRefDate()
  {
    return m_plyrRoleRefDate;
  }

  /**
   * @param plyrRoleRefDate_ The plyrRoleRefDate to set.
   */
  public void setPlyrRoleRefDate( Date plyrRoleRefDate_ )
  {
    m_plyrRoleRefDate = plyrRoleRefDate_;
  }

  /**
   * @return Returns the recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ The recStatCode to set.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}