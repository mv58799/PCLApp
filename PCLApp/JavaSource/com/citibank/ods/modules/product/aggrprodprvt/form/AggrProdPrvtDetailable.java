/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.form;

/**
 * @author leonardo.nakada
 * 
 */
public interface AggrProdPrvtDetailable
{
  /**
   * Retorna o valor do Item seleciona no Grid da Consulta
   * @return
   */
  public String getSelectedPrvtProdAggrCode();
  
  /**
   * Atribui o valor do Ítem delecionado no Grid da Consulta
   * @param selectedPrvtProdAggrCode_
   */
  public void setSelectedPrvtProdAggrCode( String selectedPrvtProdAggrCode_ );
}