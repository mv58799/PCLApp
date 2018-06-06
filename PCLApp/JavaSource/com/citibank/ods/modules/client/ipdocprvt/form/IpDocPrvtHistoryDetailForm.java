package com.citibank.ods.modules.client.ipdocprvt.form;

import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;

/**
 * @author l.braga
 *  
 */

public class IpDocPrvtHistoryDetailForm extends BaseIpDocPrvtDetailForm
    implements IpDocPrvtHistoryDetailable
{

  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status do RegistroConstraint: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private String m_recStatCode = "";

  private String m_ipDocRefDate = "";

  /**
   * @return Returns m_ipDocRefDate.
   */
  public String getIpDocRefDate()
  {
    return m_ipDocRefDate;
  }

  /**
   * @param ipDocRefDate_ Field m_ipDocRefDate to be setted.
   */
  public void setIpDocRefDate( String ipDocRefDate_ )
  {
    m_ipDocRefDate = ipDocRefDate_;
  }

  /**
   * @return Returns m_lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field m_lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns m_lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field m_lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns m_recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field m_recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Método da interface IpDocPrvtHistoryDetailable.
   */
  public String getSelectedIpDocRefDate()
  {
    return null;
  }

  /**
   * Método da interface IpDocPrvtHistoryDetailable. Seta a data de referência.
   */
  public void setSelectedIpDocRefDate( String ipDocRefDate_ )
  {
    setIpDocRefDate( ipDocRefDate_ );

  }
}