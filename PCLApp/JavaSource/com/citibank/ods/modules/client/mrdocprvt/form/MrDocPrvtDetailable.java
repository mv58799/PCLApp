package com.citibank.ods.modules.client.mrdocprvt.form;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Interface implementada nos forms de Consulta em lista e detalhe para passaar o valor selecionado no grid.
 * 
 *@author michele.monteiro,24/04/2007
 */

public interface MrDocPrvtDetailable
{

  /**
   * @return SelectedMrDocPrvt. Retorna o c�digo do documento MR.
   */
  public String getSelectedMrDocPrvt();

  /**
   * @param mrDocPrvt_. Seta  o c�digo do documento MR.
   */
  public void setSelectedMrDocPrvt( String mrDocPrvt_ );
  
  /**
   * @return SelectedMrDocPrvt. Retorna o n�mero da conta produto.
   */
  public String getSelectedProdAcctCode();

  /**
   * @param prodAcctCode_. Seta  o n�mero da conta produto.
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
