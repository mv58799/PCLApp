/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProductFamilyPrvtListFncVO extends BaseODSFncVO
{
  /**
   * Constante do nome do código da família
   */
  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Código da Família";
  
  /**
   * Constante do nome da família
   */
  public static final String C_PROD_FAML_NAME_DESCRIPTION = "Descrição da Família";
  
  /**
   * Constante da descricao da família
   */
  public static final String C_PROD_FAML_TEXT_DESCRIPTION = "Descrição da Família";
  
  /**
   * Resultado da consulta
   */
  private DataSet m_results;

  /**
   * Codigo da Familia de Produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodFamlCodeSrc;

  /**
   * Nome da Familia de Produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodFamlNameSrc;

  /**
   * Descricao da Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodFamlTextSrc;

  /**
   * @return Returns the prodFamlCodeSrc.
   */
  public BigInteger getProdFamlCodeSrc()
  {
    return m_prodFamlCodeSrc;
  }

  /**
   * @param prodFamlCodeSrc_ The prodFamlCodeSrc to set.
   */
  public void setProdFamlCodeSrc( BigInteger prodFamlCodeSrc_ )
  {
    m_prodFamlCodeSrc = prodFamlCodeSrc_;
  }

  /**
   * @return Returns the prodFamlNameSrc.
   */
  public String getProdFamlNameSrc()
  {
    return m_prodFamlNameSrc;
  }

  /**
   * @param prodFamlNameSrc_ The prodFamlNameSrc to set.
   */
  public void setProdFamlNameSrc( String prodFamlNameSrc_ )
  {
    m_prodFamlNameSrc = prodFamlNameSrc_;
  }

  /**
   * @return Returns the prodFamlTextSrc.
   */
  public String getProdFamlTextSrc()
  {
    return m_prodFamlTextSrc;
  }

  /**
   * @param prodFamlTextSrc_ The prodFamlTextSrc to set.
   */
  public void setProdFamlTextSrc( String prodFamlTextSrc_ )
  {
    m_prodFamlTextSrc = prodFamlTextSrc_;
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