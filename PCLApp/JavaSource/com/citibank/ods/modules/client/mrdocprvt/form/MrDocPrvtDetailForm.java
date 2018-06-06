package com.citibank.ods.modules.client.mrdocprvt.form;

/**
 * @author m.nakamura
 * 
 * Form com os dados da tela de detalhe de Memória de Risco
 */
public class MrDocPrvtDetailForm extends BaseMrDocPrvtDetailForm 

{

  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Codigo Documento MR
  private String m_mrDocPrvt = "";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private String m_recStatCode = "";

  //Número do cliente
  private String m_custNbr= "";
  
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
  public String getMrDocPrvt()
  {
    return m_mrDocPrvt;
  }

  /**
   * Seta Codigo Documento MR
   * 
   * @param mrDocPrvt_ - Codigo Documento MR
   */
  public void setMrDocPrvt( String mrDocPrvt_ )
  {
    m_mrDocPrvt = mrDocPrvt_;
  }

  /**
   * Recupera Status do Registro
   * 
   * @return Retorna Status do Registro
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta Status do Registro
   * 
   * @param recStatCode_ - Status do Registro
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Método da interface MrDocPrvtDetailable.
   */
  public String getSelectedMrDocPrvt()
  {
    return null;
  }

  /**
   * Método da interface MrDocPrvtDetailable.
   */
  public String getSelectedProdAcctCode()
  {
    return null;
  }

  /**
   * Método da interface MrDocPrvtDetailable.
   */
  public String getSelectedProdUnderAcctCode()
  {

    return null;
  }

  /**
   * Método da interface MrDocPrvtDetailable.Seta o código do documento MR.
   */
  public void setSelectedMrDocPrvt( String mrDocPrvt_ )
  {
    setMrDocPrvt( mrDocPrvt_ );
  }

  /**
   * Método da interface MrDocPrvtDetailable.Seta o número da conta produto.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ )
  {
    setProdAcctCode( prodAcctCode_ );
  }

  /**
   * Método da interface MrDocPrvtDetailable.Seta o número da sub conta produto.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    setProdUnderAcctCode( prodUnderAcctCode_ );

  }
  
  
  /**
   * @return Retorna o número do cliente.
   */
  public String getCustNbr()
  {
    return m_custNbr;
  }
  /**
   * @param custNbrSrc_.Seta o número do cliente.
   */
  public void setCustNbr( String custNbr_ )
  {
    m_custNbr = custNbr_;
  }
}