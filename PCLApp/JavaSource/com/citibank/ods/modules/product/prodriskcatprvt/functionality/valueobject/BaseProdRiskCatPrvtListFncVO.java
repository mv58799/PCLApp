package com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdRiskCatPrvtListFncVO extends BaseODSFncVO
{
  /**
   * Constante do nome do elemento Código
   */
  public static final String C_PROD_RISK_CAT_CODE_DESCRIPTION = "Código da Categoria de Risco";

  /**
   * Constante da descricao da categoria de risco
   */
  public static final String C_PROD_RISK_CAT_TEXT_DESCRIPTION = "Descrição da Categoria de Risco";

  /**
   * Resultado da consulta
   */
  private DataSet m_results;

  /**
   * Comment for <code>m_prodRiskCatCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodRiskCatCodeSrc;

  /**
   * Comment for <code>m_prodRiskCatNameText</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodRiskCatTextSrc;

  /**
   * @return Returns the prodRiskCatCodeSrc.
   */
  public BigInteger getProdRiskCatCodeSrc()
  {
    return m_prodRiskCatCodeSrc;
  }

  /**
   * @param prodRiskCatCodeSrc_ The prodRiskCatCodeSrc to set.
   */
  public void setProdRiskCatCodeSrc( BigInteger prodRiskCatCodeSrc_ )
  {
    m_prodRiskCatCodeSrc = prodRiskCatCodeSrc_;
  }

  /**
   * @return Returns the prodRiskCatTextSrc.
   */
  public String getProdRiskCatTextSrc()
  {
    return m_prodRiskCatTextSrc;
  }

  /**
   * @param prodRiskCatTextSrc_ The prodRiskCatTextSrc to set.
   */
  public void setProdRiskCatTextSrc( String prodRiskCatTextSrc_ )
  {
    m_prodRiskCatTextSrc = prodRiskCatTextSrc_;
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