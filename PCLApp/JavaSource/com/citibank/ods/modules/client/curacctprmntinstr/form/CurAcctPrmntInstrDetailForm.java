package com.citibank.ods.modules.client.curacctprmntinstr.form;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrDetailForm extends
    BaseCurAcctPrmntInstrDetailForm
{

  // Data e Hora que o Usuario aprovou o Registro Cadastrado
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";
  
  //Número da instrução permanente - Grid dinâmico
  private String m_selectedIpDocCode = "";

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
   * @return Returns selectedIpDocCode.
   */
  public String getSelectedIpDocCode()
  {
    return m_selectedIpDocCode;
  }
  /**
   * @param selectedIpDocCode_ Field selectedIpDocCode to be setted.
   */
  public void setSelectedIpDocCode( String selectedIpDocCode_ )
  {
    m_selectedIpDocCode = selectedIpDocCode_;
  }
}