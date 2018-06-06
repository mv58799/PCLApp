package com.citibank.ods.modules.client.mrdocprvt.form;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Form da classe MRDocPrvtMovementListView.jsp
 * 
 * @author michele.monteiro,24/04/2007
 *  
 */

public class MrDocPrvtMovListForm extends BaseMrDocPrvtListForm implements MrDocPrvtMovDetailable
{

  //Usuário da última atualização
  private String m_lastUpdUserIdSrc = "";

  //Data da última atualização
  private String m_lastUpdDateSrc = "";
  
  //Número do documento MR
  private String m_mrDocCodeSrc="";

  //Código do documento MR selecionado.
  private String m_selectedMrDocCode = "";
  
  //Código do documento MR selecionado.
  private String m_selectedProdAcctCode = "";
  
  //Código do documento MR selecionado.
  private String m_selectedProdUnderAcctCode = "";
  
  

  /**
   * @return Retorna o usuário da última atualização.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUppDateUserIdSrc_.Seta o usuário da última atualização.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Retorna a data da última atualização.
   */
  public String getLastUpdDateSrc()
  {
    return m_lastUpdDateSrc;
  }

  /**
   * @param lastUpdDateSrc_.Seta a data da última atualização.
   */
  public void setLastUpdDateSrc( String lastUpdDateSrc_ )
  {
    m_lastUpdDateSrc = lastUpdDateSrc_;
  }

  /**
   * @return Retorna o número do documento MR selecionado.
   */
  public String getSelectedMrDocCode()
  {
    return m_selectedMrDocCode;
  }

  /**
   * @param selectedMrDocCode_.Seta o número do documento MR selecionado.
   */
  public void setSelectedMrDocCode( String selectedMrDocCode_ )
  {
    m_selectedMrDocCode = selectedMrDocCode_;
  }
  
  
  /**
   * @return Retorna o número do documento.
   */
  public String getMrDocCodeSrc()
  {
    return m_mrDocCodeSrc;
  }
  /**
   * @param mrDocCode_.Seta o número do documento.
   */
  public void setMrDocCodeSrc( String mrDocCodeSrc_ )
  {
    m_mrDocCodeSrc = mrDocCodeSrc_;
  }
  
  /**
   * @return Retorna o número da conta produto selecionada.
   */
  public String getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }
  /**
   * @param selectedProdAcctCode_ Seta o número da conta produto selecionada.
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }
  /**
   * @return Retorna o número da sub conta produto selecionada.
   */
  public String getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }
  /**
   * @param selectedProdUnderAcctCode_ Seta o número da sub conta produto selecionada.
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }
}