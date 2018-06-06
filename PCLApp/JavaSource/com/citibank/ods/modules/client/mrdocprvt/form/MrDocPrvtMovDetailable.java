package com.citibank.ods.modules.client.mrdocprvt.form;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Interface implementada nos forms MrDocPrvtMovListForm e MrDocPrvtMovDetailForm.
 * 
 *@author michele.monteiro,26/04/2007
 */

public interface MrDocPrvtMovDetailable
{
  
  /**
   * @return SelectedMrDocPrvt. Retorna o c�digo do documento MR.
   */
  public String getSelectedMrDocCode();

  /**
   * @param mrDocPrvt_. Seta o c�digo do documento MR.
   */
  public void setSelectedMrDocCode( String mrDocCode_ );

  /**
   * @return SelectedMrDocPrvt. Retorna o n�mero da conta produto.
   */
  public String getSelectedProdAcctCode();

  /**
   * @param prodAcctCode_. Seta o n�mero da conta produto.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ );

  /**
   * @return SelectedMrDocPrvt. Retorna o n�mero da sub conta produto.
   */
  public String getSelectedProdUnderAcctCode();

  /**
   * @param prodUnderAcctCode_. Seta o n�mero da sub conta produto.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ );
  
  

}
