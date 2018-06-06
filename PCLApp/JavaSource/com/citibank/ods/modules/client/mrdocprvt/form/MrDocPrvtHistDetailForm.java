package com.citibank.ods.modules.client.mrdocprvt.form;

/**
 * @author m.nakamura
 * 
 * Form com os dados da tela de detalhe de Mem�ria de Risco
 */
public class MrDocPrvtHistDetailForm extends BaseMrDocPrvtDetailForm implements
    MrDocPrvtHistDetailable
{

  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private String m_recStatCode = "";

  // Codigo Documento MR
  private String m_mrDocCode = "";

  // Data de refer�ncia do hist�rico
  private String m_mrDocRefDate = "";

  //N�mero do cliente
  private String m_custNbr = "";
  
  //N�mero da conta corrent
  private String m_curAcctNbr="";

  /**
   * Recupera a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   * 
   * @return Retorna a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * Seta a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   * 
   * @param lastAuthDate_- A Data e Hora que o Usuario Aprovou o Registro
   *          Cadastrado.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * Recupera o Codigo do Usuario que Aprovou o Cadastro do Registro.
   * 
   * @return Retorna o Codigo do Usuario que Aprovou o Cadastro do Registro.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * Seta o Codigo do Usuario que Aprovou o Cadastro do Registro.
   * 
   * @param lastAuthUserId_ - O Codigo do Usuario que Aprovou o Cadastro do
   *          Registro.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * Recupera Codigo Documento MR
   * 
   * @return Retorna Codigo Documento MR
   */
  public String getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * Seta Codigo Documento MR
   * 
   * @param mrDocCode_ - Codigo Documento MR
   */
  public void setMrDocCode( String mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }

  /**
   * Recupera Data de refer�ncia do hist�rico
   * 
   * @return Retorna Data de refer�ncia do hist�rico
   */
  public String getMrDocRefDate()
  {
    return m_mrDocRefDate;
  }

  /**
   * Seta Data de refer�ncia do hist�rico
   * 
   * @param mrDocRefDate_ - Data de refer�ncia do hist�rico
   */
  public void setMrDocRefDate( String mrDocRefDate_ )
  {
    m_mrDocRefDate = mrDocRefDate_;
  }

  /**
   * Recupera Status do Registro.
   * 
   * @return Retorna Status do Registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta Status do Registro.
   * 
   * @param recStatCode_ - Status do Registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Retorna o n�mero do cliente.
   */
  public String getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_.Seta o n�mero do cliente.
   */
  public void setCustNbr( String custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.
   */
  public String getSelectedMrDocCode()
  {
    return null;
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.
   */
  public String getSelectedMrDocRefDate()
  {
    return null;
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.
   */
  public String getSelectedProdAcctCode()
  {

    return null;
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.
   */
  public String getSelectedProdUnderAcctCode()
  {

    return null;
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.Seta o n�mero do documento MR.
   */
  public void setSelectedMrDocCode( String mrDocCode_ )
  {
    setMrDocCode( mrDocCode_ );

  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.Seta a data de refer�ncia do
   * hist�rico.
   */
  public void setSelectedMrDocRefDate( String mrDocRefDate_ )
  {
    setMrDocRefDate( mrDocRefDate_ );
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable.Seta o n�mero da conta produto.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ )
  {
    setProdAcctCode( prodAcctCode_ );
  }

  /**
   * M�todo da interface MrDocPrvtHistDetailable. Seta o n�mero da sub conta
   * produto.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    setProdUnderAcctCode( prodUnderAcctCode_ );
  }
  
  
  /**
   * @return Retorna o n�mero da conta corrente.
   */
  public String getCurAcctNbr()
  {
    return m_curAcctNbr;
  }
  /**
   * @param curAcctNbr_.Seta o n�mero da conta corrente.
   */
  public void setCurAcctNbr( String curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }
}