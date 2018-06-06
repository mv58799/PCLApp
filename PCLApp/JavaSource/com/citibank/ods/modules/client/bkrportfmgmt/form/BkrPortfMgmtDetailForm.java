package com.citibank.ods.modules.client.bkrportfmgmt.form;

/**
 * @author Hamilton Matos
 */

public class BkrPortfMgmtDetailForm extends BaseBkrPortfMgmtDetailForm
{

  // Data e Hora que o usuário aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Código do usuário (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovação
  private String m_recStatCode = "";

  // Quantidade de registros no grid de corretoras
  private String m_brokerListSize = "";

  /**
   * @return Returns lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  public String getBrokerListSize()
  {
    return m_brokerListSize;
  }

  public void setBrokerListSize( String brokerListSize_ )
  {
    m_brokerListSize = brokerListSize_;
  }
}