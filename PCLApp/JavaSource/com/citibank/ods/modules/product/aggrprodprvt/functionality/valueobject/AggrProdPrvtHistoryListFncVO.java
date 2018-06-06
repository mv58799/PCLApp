/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject;

import java.util.Date;

/**
 * @author leonardo.nakada
 * 
 */
public class AggrProdPrvtHistoryListFncVO extends BaseAggrProdPrvtListFncVO
{
  /**
   * Constante da da Data de Referência de Agregador de Produto
   */
  public static final String C_PRVT_PROD_AGGR_REF_DATE_DESCRIPTION = "Data de Referência";

  /**
   * Data de Referencia do registro no historico
   */
  private Date m_prvtProdAggrRefDate;
  
  /**
   * Código do agregador no registro de historico
   */
  private String m_prvtProdAggrCodeHistSrc;

  /**
   * @return Returns the prvtProdAggrRefDate.
   */
  public Date getPrvtProdAggrRefDate()
  {
    return m_prvtProdAggrRefDate;
  }

  /**
   * @param prvtProdAggrRefDate_ The prvtProdAggrRefDate to set.
   */
  public void setPrvtProdAggrRefDate( Date prvtProdAggrRefDate_ )
  {
    m_prvtProdAggrRefDate = prvtProdAggrRefDate_;
  }

  public String getPrvtProdAggrCodeHistSrc()
  {
    return m_prvtProdAggrCodeHistSrc;
  }

  public void setPrvtProdAggrCodeHistSrc( String prvtProdAggrCodeHistSrc_ )
  {
    m_prvtProdAggrCodeHistSrc = prvtProdAggrCodeHistSrc_;
  }
}