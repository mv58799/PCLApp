package com.citibank.ods.modules.client.mrdocprvt.form;

/**
 * Interface implementada nos forms MrDocPrvtHistListForm e MrDocPrvtHistDetailForm.
 * 
 * @author michele.monteiro,24/04/2007
 * 
 */

public interface MrDocPrvtHistDetailable
{

  /**
   * @return SelectedMrDocPrvt. Retorna o código do documento MR.
   */
  public String getSelectedMrDocCode();

  /**
   * @param mrDocPrvt_. Seta o código do documento MR.
   */
  public void setSelectedMrDocCode( String mrDocCode_ );

  /**
   * @return SelectedMrDocPrvt. Retorna o número da conta produto.
   */
  public String getSelectedProdAcctCode();

  /**
   * @param prodAcctCode_. Seta o número da conta produto.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ );

  /**
   * @return SelectedMrDocPrvt. Retorna o número da sub conta produto.
   */
  public String getSelectedProdUnderAcctCode();

  /**
   * @param prodUnderAcctCode_. Seta o número da sub conta produto.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ );
  
  
  
  /**
   * @return Retorna a data de referência do históric.
   */
  public String getSelectedMrDocRefDate();

  /**
   * @param mrDocRefDate_. Seta a data de referência do histórico.
   */
  public void setSelectedMrDocRefDate( String mrDocRefDate_ );

}