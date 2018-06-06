/*
 * Created on Mar 12, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseAggrProdPrvtListFncVO extends BaseODSFncVO
{
  
  /**
   * Constante do nome do elemento C�digo
   */
  public static final String C_PRVT_PROD_AGGR_CODE_DESCRIPTION = "C�digo do Agregador";
  
  /**
   * Constante da descricao da categoria de risco
   */
  public static final String C_PRVT_PROD_AGGR_TEXT_DESCRIPTION = "Descri��o do Agregador";
  
  /**
   * Codigo do Agrupador de Produtos Private
   */
  private String m_prvtProdAggrCodeSrc;

  /**
   * Descricao do agrupador de Produtos Private
   */
  private String m_prvtProdAggrTextSrc;

  /**
   * Resultado da consulta
   */
  private DataSet m_results;

  /**
   * @return Returns the prvtProdAggrCodeSrc.
   */
  public String getPrvtProdAggrCodeSrc()
  {
    return m_prvtProdAggrCodeSrc;
  }

  /**
   * @param prvtProdAggrCodeSrc_ The prvtProdAggrCodeSrc to set.
   */
  public void setPrvtProdAggrCodeSrc( String prvtProdAggrCodeSrc_ )
  {
    m_prvtProdAggrCodeSrc = prvtProdAggrCodeSrc_;
  }

  /**
   * @return Returns the prvtProdAggrTextSrc.
   */
  public String getPrvtProdAggrTextSrc()
  {
    return m_prvtProdAggrTextSrc;
  }

  /**
   * @param prvtProdAggrTextSrc_ The prvtProdAggrTextSrc to set.
   */
  public void setPrvtProdAggrTextSrc( String prvtProdAggrTextSrc_ )
  {
    m_prvtProdAggrTextSrc = prvtProdAggrTextSrc_;
  }

  /**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }
}