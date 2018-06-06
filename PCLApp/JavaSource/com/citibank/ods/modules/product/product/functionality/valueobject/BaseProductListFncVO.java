/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.product.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProductListFncVO extends BaseODSFncVO
{
  /**
   * Resultado da consulta
   */
  private String m_prodCodeSrc;

  private BigInteger m_prodFamlCodeSrc;

  private String m_prodNameSrc;

  private BigInteger m_prodQlfyCodeSrc;

  private BigInteger m_prodRiskCatCodeSrc;

  private BigInteger m_prodSubFamlCodeSrc;

  private DataSet m_results;

  private DataSet m_prodQlfyCodeDomain;

  private DataSet m_prodFamlCodeDomain;

  private DataSet m_prodRiskCodeDomain;
  
  private DataSet m_prodSubFamlCodeDomain;

  private String m_lastUpdUserIdSrc;
  
  private ArrayList m_listProduct;
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

  /**
   * @return Returns prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }

  /**
   * @param prodCodeSrc_ Field prodCodeSrc to be setted.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }

  /**
   * @return Returns prodFamlCodeSrc.
   */
  public BigInteger getProdFamlCodeSrc()
  {
    return m_prodFamlCodeSrc;
  }

  /**
   * @param prodFamlCodeSrc_ Field prodFamlCodeSrc to be setted.
   */
  public void setProdFamlCodeSrc( BigInteger prodFamlCodeSrc_ )
  {
    m_prodFamlCodeSrc = prodFamlCodeSrc_;
  }

  /**
   * @return Returns prodNameSrc.
   */
  public String getProdNameSrc()
  {
    return m_prodNameSrc;
  }

  /**
   * @param prodNameSrc_ Field prodNameSrc to be setted.
   */
  public void setProdNameSrc( String prodNameSrc_ )
  {
    m_prodNameSrc = prodNameSrc_;
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
   * @return Returns prodRiskCatCodeSrc.
   */
  public BigInteger getProdRiskCatCodeSrc()
  {
    return m_prodRiskCatCodeSrc;
  }

  /**
   * @param prodRiskCatCodeSrc_ Field prodRiskCatCodeSrc to be setted.
   */
  public void setProdRiskCatCodeSrc( BigInteger prodRiskCatCodeSrc_ )
  {
    m_prodRiskCatCodeSrc = prodRiskCatCodeSrc_;
  }

  /**
   * @return Returns prodSubFamlCodeSrc.
   */
  public BigInteger getProdSubFamlCodeSrc()
  {
    return m_prodSubFamlCodeSrc;
  }

  /**
   * @param prodSubFamlCodeSrc_ Field prodSubFamlCodeSrc to be setted.
   */
  public void setProdSubFamlCodeSrc( BigInteger prodSubFamlCodeSrc_ )
  {
    m_prodSubFamlCodeSrc = prodSubFamlCodeSrc_;
  }

  public void setProdQlfyCodeDomain( DataSet prodQlfyDomain_ )
  {
    m_prodQlfyCodeDomain = prodQlfyDomain_;
  }

  public void setProdFamlCodeDomain( DataSet prodFamlDomain_ )
  {
    m_prodFamlCodeDomain = prodFamlDomain_;
  }

  public void setProdRiskDomain( DataSet prodRiskDomain_ )
  {
    m_prodRiskCodeDomain = prodRiskDomain_;
  }
  
  public DataSet getProdQlfyCodeDomain()
  {
    return m_prodQlfyCodeDomain;
  }
  
  public DataSet getProdFamlCodeDomain()
  {
    return m_prodFamlCodeDomain;
  }
  
  public DataSet getProdRiskDomain()
  {
    return m_prodRiskCodeDomain;
  }
  
  /**
   * @return Returns prodRiskCodeDomain.
   */
  public DataSet getProdRiskCodeDomain()
  {
    return m_prodRiskCodeDomain;
  }
  /**
   * @param prodRiskCodeDomain_ Field prodRiskCodeDomain to be setted.
   */
  public void setProdRiskCodeDomain( DataSet prodRiskCodeDomain_ )
  {
    m_prodRiskCodeDomain = prodRiskCodeDomain_;
  }
  /**
   * @return Returns prodSubFamlCodeDomain.
   */
  public DataSet getProdSubFamlCodeDomain()
  {
    return m_prodSubFamlCodeDomain;
  }
  /**
   * @param prodSubFamlCodeDomain_ Field prodSubFamlCodeDomain to be setted.
   */
  public void setProdSubFamlCodeDomain( DataSet prodSubFamlCodeDomain_ )
  {
    m_prodSubFamlCodeDomain = prodSubFamlCodeDomain_;
  }
  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }
  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

	/**
 	* @return
 	*/
  public ArrayList getListProduct() {
    return m_listProduct;
  }

/**
 * @param list
 */
  public void setListProduct(ArrayList m_listProduct_) {
	m_listProduct = m_listProduct_;
  }

}