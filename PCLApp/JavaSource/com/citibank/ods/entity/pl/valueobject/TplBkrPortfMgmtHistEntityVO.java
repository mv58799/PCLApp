package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * Classe que instancia os valores correspondente a um registro da tabela
 * Tpl_Bkr_Portf_Mgmt_Hist
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtHistEntityVO extends BaseTplBkrPortfMgmtEntityVO
{

  // Data de Referência do registro no histórico
  private Date m_bkrPortfMgmtRefDate;

  // Data e Hora que o Usuário aprovou o Registro Cadastrado
  private Date m_lastAuthDate;

  // Código do Usuário que Aprovou o Cadastro do Registro
  private String m_lastAuthUserId;

  // Status do registro
  private String m_recStatCode;

  /**
   * @return Returns bkrPortfMgmtRefDate.
   */
  public Date getBkrPortfMgmtRefDate()
  {
    return m_bkrPortfMgmtRefDate;
  }

  /**
   * @param bkrPortfMgmtRefDate_ Field bkrPortfMgmtRefDate to be setted.
   */
  public void setBkrPortfMgmtRefDate( Date bkrPortfMgmtRefDate_ )
  {
    m_bkrPortfMgmtRefDate = bkrPortfMgmtRefDate_;
  }

  /**
   * @return Returns lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
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
}