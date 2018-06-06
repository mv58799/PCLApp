package com.citibank.ods.modules.client.mrdocprvt.form;

/**
 * @author m.nakamura
 * 
 * Form com os dados da tela de detalhe de Memo de Risco
 */
public class MrDocPrvtMovDetailForm extends BaseMrDocPrvtDetailForm implements
    MrDocPrvtMovDetailable
{

  // Codigo Documento MR
  private String m_mrDocCode = "";

  // Código da ação que originou o registro
  private String m_opernCode = "";

  // Tamanho da lista de contatos
  private int m_contactSize = 0;
  
  // Vetor com os opern_code do call back
  private String[] m_opernCodeArray;

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
   * Recupera o Código da ação que originou o registro
   * 
   * @return Retorna o Código da ação que originou o registro
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * Seta o Código da ação que originou o registro
   * 
   * @param opernCode_ - O Código da ação que originou o registro
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public String getSelectedMrDocCode()
  {
    return null;
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public String getSelectedProdAcctCode()
  {
    return null;
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public String getSelectedProdUnderAcctCode()
  {

    return null;
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public void setSelectedMrDocCode( String mrDocCode_ )
  {
    setMrDocCode( mrDocCode_ );
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ )
  {
    setProdAcctCode( prodAcctCode_ );
  }

  /**
   * Método da interface MrDocPrvtMovDetailable.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    setProdUnderAcctCode( prodUnderAcctCode_ );
  }

  /**
   * @return Retorna o tamanho da lista de contatos ativos.
   */
  public int getContactSize()
  {
    return m_contactSize;
  }

  /**
   * @param contactSize_.Seta o tamanho da lista de contatos ativos.
   */
  public void setContactSize( int contactSize_ )
  {
    m_contactSize = contactSize_;
  }
  
  
  /**
   * @return Returns opernCodeArray.
   */
  public String[] getOpernCodeArray()
  {
    return m_opernCodeArray;
  }
  /**
   * @param opernCodeArray_ Field opernCodeArray to be setted.
   */
  public void setOpernCodeArray( String[] opernCodeArray_ )
  {
    m_opernCodeArray = opernCodeArray_;
  }
}