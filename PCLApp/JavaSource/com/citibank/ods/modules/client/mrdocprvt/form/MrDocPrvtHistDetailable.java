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
  
  
  
  /**
   * @return Retorna a data de refer�ncia do hist�ric.
   */
  public String getSelectedMrDocRefDate();

  /**
   * @param mrDocRefDate_. Seta a data de refer�ncia do hist�rico.
   */
  public void setSelectedMrDocRefDate( String mrDocRefDate_ );

}