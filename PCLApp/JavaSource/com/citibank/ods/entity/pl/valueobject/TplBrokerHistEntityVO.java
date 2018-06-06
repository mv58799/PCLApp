package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author Hamilton Matos
 */
public class TplBrokerHistEntityVO extends BaseTplBrokerEntityVO
{
  //Data de Referencia do registro no historico.
  private Date m_bkrRefDate;

  //Data e Hora que o usuario aprovou o registro cadastrado
  private Date m_lastAuthDate;

  //Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId;

  //Status Registro - Identifica se o registro esta ativo, inativo ou pendente
  private String m_recStatCode;

  public Date getBkrRefDate()
  {
    return m_bkrRefDate;
  }

  public void setBkrRefDate( Date bkrRefDate_ )
  {
    m_bkrRefDate = bkrRefDate_;
  }

  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}