package com.citibank.ods.modules.client.relationprvt.form;

/**
 * 
 * 
 * @author michele.monteiro,12/04/2007
 *  
 */

public interface RelationPrvtSearchable
{
  
  /**
   * @return SelectedReltnNbr. Retorna o valor selecionado no grid.
   */
  public String getSelectedReltnNbr();

  /**
   * @param reltnNbr. Seta o número do relacionamento.
   */
  public void setSelectedReltnNbr( String reltnNbr_ );
 
  /**
   * @return ReltnNbrSrc. Retorna o valor selecionado no grid.
   */
  public String getReltnNbrSrc();

  /**
   * @param reltnNbrSrc_. Seta o número do relacionamento.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ );

}