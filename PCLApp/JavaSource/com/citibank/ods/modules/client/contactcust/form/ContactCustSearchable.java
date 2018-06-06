package com.citibank.ods.modules.client.contactcust.form;

/**
 * Implementação do botão buscar. 
 * 
 * @author michele.monteiro,25/04/2007
 *  
 */

public interface ContactCustSearchable
{
  
  /**
   * @return Retorna o número do contato selecionado
   */
  public String getSelectedCtcNbrSrc();

  /**
   * @param ctcNbrSrc_. Seta o número do contato selecionado.
   */
  public void setSelectedCtcNbrSrc( String ctcNbrSrc_ );

  /**
   * @param custNbrSrc_. Seta o número do contato no campo de pesquisa.
   */
  public void setCtcNbrSrc( String ctcNbrSrc_ );

  /**
   * @return Retorna o número do contato pesquisado.
   */
  public String getCtcNbrSrc();

}