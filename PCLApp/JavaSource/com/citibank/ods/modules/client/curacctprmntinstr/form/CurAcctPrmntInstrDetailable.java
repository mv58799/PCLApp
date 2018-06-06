package com.citibank.ods.modules.client.curacctprmntinstr.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.form;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 *  
 */

public interface CurAcctPrmntInstrDetailable
{

  /**
   * @return custNbr_. Retorna o número do cliente.
   */
  public String getSelectedCustNbr();

  /**
   * @param custNbr_. Seta o número do cliente.
   */
  public void setSelectedCustNbr( String custNbr_ );

  /**
   * @return selectedProdAcctCode. Retorna o valor da conta produto selecionado
   *         no grid.
   */
  public String getSelectedProdAcctCode();

  /**
   * @param prodAcctCode_. Seta o número da conta produto
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ );

  /**
   * @return selectedProdUnderAcctCode. Retorna o valor selecionado no grid.
   */
  public String getSelectedProdUnderAcctCode();

  /**
   * @param prodUnderAcctCode_. Seta o número da sub conta produto
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ );

  /**
   * @return selectedPrmntInstrCode. Retorna o código da instrução permanente.
   */
  public String getSelectedPrmntInstrCode();

  /**
   * @param selectedPrmntInstrCode_. Seta o código da instrução permanente;
   */
  public void setSelectedPrmntInstrCode( String selectedPrmntInstrCode_ );
}