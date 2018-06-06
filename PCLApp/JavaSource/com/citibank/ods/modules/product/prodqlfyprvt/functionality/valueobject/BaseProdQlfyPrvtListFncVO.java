/*
 * Created on Mar 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author fernando.salgado
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdQlfyPrvtListFncVO extends BaseODSFncVO
{
  /**
   * Constante do Qualificador que descreve o nome do campo do Código da
   * Qualificação da tela
   */
  public static final String C_PROD_QLFY_CODE_DESCRIPTION = "Código da Qualificação";

  /**
   * Constante do Qualificador que descreve o nome do campo da Descrição de
   * Qualificação da tela
   */
  public static final String C_PROD_QLFY_TEXT_DESCRIPTION = "Descrição da Qualificação";

  /*
   * Resultado da consulta
   */
  private DataSet m_results;

  /*
   * Comment for <code> m_prodQlfyCode </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodQlfyCodeSrc;

  /*
   * Comment for <code> m_prodQlfyText </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodQlfyTextSrc;

  /**
   * @return Returns the m_results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param m_results The m_results to set.
   */
  public void setResults( DataSet results_ )
  {
    this.m_results = results_;
  }

  /**
   * @return Returns prodQlfyCodeSrc.
   */
  public BigInteger getProdQlfyCodeSrc()
  {
    return m_prodQlfyCodeSrc;
  }

  /**
   * @param prodQlfyCodeSrc_ Field prodQlfyCodeSrc to be setted.
   */
  public void setProdQlfyCodeSrc( BigInteger prodQlfyCodeSrc_ )
  {
    m_prodQlfyCodeSrc = prodQlfyCodeSrc_;
  }

  /**
   * @return Returns prodQlfyTextSrc.
   */
  public String getProdQlfyTextSrc()
  {
    return m_prodQlfyTextSrc;
  }

  /**
   * @param prodQlfyTextSrc_ Field prodQlfyTextSrc to be setted.
   */
  public void setProdQlfyTextSrc( String prodQlfyTextSrc_ )
  {
    m_prodQlfyTextSrc = prodQlfyTextSrc_;
  }

}