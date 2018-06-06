/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class BaseTplPlayerRoleEntityVO extends BaseEntityVO
{
  /**
   * CNPJ do Player.
   */
  private String m_plyrCnpjNbr;

  /**
   * Tipo do Papel do Player (Administrador, Gestor, Custodiante, Controlador,
   * Auditor)
   */
  private String m_plyrRoleTypeCode;

  /**
   * Codigo do Usuario que efetuou a ultima atualizacao registro.
   */
  private String m_lastUpdUserId;

  /**
   * Data e Hora da Ultima atualizacao efetuada pelo usuario.
   */
  private Date m_lastUpdDate;

  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns the plyrCnpjNbr.
   */
  public String getPlyrCnpjNbr()
  {
    return m_plyrCnpjNbr;
  }

  /**
   * @param plyrCnpjNbr_ The plyrCnpjNbr to set.
   */
  public void setPlyrCnpjNbr( String plyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = plyrCnpjNbr_;
  }

  /**
   * @return Returns the plyrRoleTypeCode.
   */
  public String getPlyrRoleTypeCode()
  {
    return m_plyrRoleTypeCode;
  }

  /**
   * @param plyrRoleTypeCode_ The plyrRoleTypeCode to set.
   */
  public void setPlyrRoleTypeCode( String plyrRoleTypeCode_ )
  {
    m_plyrRoleTypeCode = plyrRoleTypeCode_;
  }
}