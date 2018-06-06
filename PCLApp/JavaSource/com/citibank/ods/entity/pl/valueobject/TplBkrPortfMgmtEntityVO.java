package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * Classe que instancia os valores correspondente a um registro da tabela
 * Tpl_Bkr_Portf_Mgmt
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtEntityVO extends BaseTplBkrPortfMgmtEntityVO
{

  // Data e Hora que o Usuário aprovou o Registro Cadastrado
  private Date m_lastAuthDate;

  // Código do Usuário que Aprovou o Cadastro do Registro
  private String m_lastAuthUserId;

  // Código de operação
  private String m_opernCode;

  // Status do registro
  private String m_recStatCode;

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
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
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