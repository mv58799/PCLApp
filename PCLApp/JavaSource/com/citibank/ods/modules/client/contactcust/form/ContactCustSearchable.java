package com.citibank.ods.modules.client.contactcust.form;

/**
 * Implementa��o do bot�o buscar. 
 * 
 * @author michele.monteiro,25/04/2007
 *  
 */

public interface ContactCustSearchable
{
  
  /**
   * @return Retorna o n�mero do contato selecionado
   */
  public String getSelectedCtcNbrSrc();

  /**
   * @param ctcNbrSrc_. Seta o n�mero do contato selecionado.
   */
  public void setSelectedCtcNbrSrc( String ctcNbrSrc_ );

  /**
   * @param custNbrSrc_. Seta o n�mero do contato no campo de pesquisa.
   */
  public void setCtcNbrSrc( String ctcNbrSrc_ );

  /**
   * @return Retorna o n�mero do contato pesquisado.
   */
  public String getCtcNbrSrc();

}