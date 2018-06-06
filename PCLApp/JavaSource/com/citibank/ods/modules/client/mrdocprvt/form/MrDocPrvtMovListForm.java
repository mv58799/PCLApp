package com.citibank.ods.modules.client.mrdocprvt.form;


//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Form da classe MRDocPrvtMovementListView.jsp
 * 
 * @author michele.monteiro,24/04/2007
 *  
 */

public class MrDocPrvtMovListForm extends BaseMrDocPrvtListForm implements MrDocPrvtMovDetailable
{

  //Usu�rio da �ltima atualiza��o
  private String m_lastUpdUserIdSrc = "";

  //Data da �ltima atualiza��o
  private String m_lastUpdDateSrc = "";
  
  //N�mero do documento MR
  private String m_mrDocCodeSrc="";

  //C�digo do documento MR selecionado.
  private String m_selectedMrDocCode = "";
  
  //C�digo do documento MR selecionado.
  private String m_selectedProdAcctCode = "";
  
  //C�digo do documento MR selecionado.
  private String m_selectedProdUnderAcctCode = "";
  
  

  /**
   * @return Retorna o usu�rio da �ltima atualiza��o.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUppDateUserIdSrc_.Seta o usu�rio da �ltima atualiza��o.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Retorna a data da �ltima atualiza��o.
   */
  public String getLastUpdDateSrc()
  {
    return m_lastUpdDateSrc;
  }

  /**
   * @param lastUpdDateSrc_.Seta a data da �ltima atualiza��o.
   */
  public void setLastUpdDateSrc( String lastUpdDateSrc_ )
  {
    m_lastUpdDateSrc = lastUpdDateSrc_;
  }

  /**
   * @return Retorna o n�mero do documento MR selecionado.
   */
  public String getSelectedMrDocCode()
  {
    return m_selectedMrDocCode;
  }

  /**
   * @param selectedMrDocCode_.Seta o n�mero do documento MR selecionado.
   */
  public void setSelectedMrDocCode( String selectedMrDocCode_ )
  {
    m_selectedMrDocCode = selectedMrDocCode_;
  }
  
  
  /**
   * @return Retorna o n�mero do documento.
   */
  public String getMrDocCodeSrc()
  {
    return m_mrDocCodeSrc;
  }
  /**
   * @param mrDocCode_.Seta o n�mero do documento.
   */
  public void setMrDocCodeSrc( String mrDocCodeSrc_ )
  {
    m_mrDocCodeSrc = mrDocCodeSrc_;
  }
  
  /**
   * @return Retorna o n�mero da conta produto selecionada.
   */
  public String getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }
  /**
   * @param selectedProdAcctCode_ Seta o n�mero da conta produto selecionada.
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }
  /**
   * @return Retorna o n�mero da sub conta produto selecionada.
   */
  public String getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }
  /**
   * @param selectedProdUnderAcctCode_ Seta o n�mero da sub conta produto selecionada.
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }
}