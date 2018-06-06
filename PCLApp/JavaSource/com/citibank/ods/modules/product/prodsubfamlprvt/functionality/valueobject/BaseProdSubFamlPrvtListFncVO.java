/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdSubFamlPrvtListFncVO extends BaseODSFncVO
{
  public static final String C_PROD_SUB_FAML_CODE_DESCRIPTION = "Código da Sub-Família";
  public static final String C_PROD_SUB_FAML_NAME_DESCRIPTION = "Nome da Sub-Família";
  public static final String C_PROD_SUB_FAML_TEXT_DESCRIPTION = "Desc. da Sub-Família";
  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Codigo da Familia";
  
  /**
   * Resultado da consulta
   */
  private DataSet m_results;
  
  /**
   * Codigo da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodSubFamlCodeSrc;

  /**
   * Nome da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodSubFamlNameSrc;

  /**
   * Descricao da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodSubFamlTextSrc;

  /**
   * @return Returns the prodSubFamlCodeSrc.
   */
  public BigInteger getProdSubFamlCodeSrc()
  {
    return m_prodSubFamlCodeSrc;
  }

  /**
   * @param prodSubFamlCodeSrc_ The prodSubFamlCodeSrc to set.
   */
  public void setProdSubFamlCodeSrc( BigInteger prodSubFamlCodeSrc_ )
  {
    m_prodSubFamlCodeSrc = prodSubFamlCodeSrc_;
  }

  /**
   * @return Returns the prodSubFamlNameSrc.
   */
  public String getProdSubFamlNameSrc()
  {
    return m_prodSubFamlNameSrc;
  }

  /**
   * @param prodSubFamlNameSrc_ The prodSubFamlNameSrc to set.
   */
  public void setProdSubFamlNameSrc( String prodSubFamlNameSrc_ )
  {
    m_prodSubFamlNameSrc = prodSubFamlNameSrc_;
  }

  /**
   * @return Returns the prodSubFamlTextSrc.
   */
  public String getProdSubFamlTextSrc()
  {
    return m_prodSubFamlTextSrc;
  }

  /**
   * @param prodSubFamlTextSrc_ The prodSubFamlTextSrc to set.
   */
  public void setProdSubFamlTextSrc( String prodSubFamlTextSrc_ )
  {
    m_prodSubFamlTextSrc = prodSubFamlTextSrc_;
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