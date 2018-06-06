/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author rcoelho
 */
public class BaseProdSubAssetListFncVO extends BaseODSFncVO
{
  /**
   * Constante do Qualificador que descreve o nome do campo do Código da
   * Sub Classe da tela
   */
  public static final String C_PROD_SUBASSET_CODE_DESCRIPTION = "Código da Sub Classe";

  /**
   * Constante do Qualificador que descreve o nome do campo da Descrição de
   * Sub Classe da tela
   */
  public static final String C_PROD_SUBASSET_TEXT_DESCRIPTION = "Descrição da Sub Classe";

  /*
   * Resultado da consulta
   */
  private DataSet m_results;

  /*
   * Comment for <code> m_prodSubAssetCode </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodSubAssetCodeSrc;

  /*
   * Comment for <code> m_prodSubAssetText </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodSubAssetTextSrc;

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
   * @return Returns prodSubAssetCodeSrc.
   */
  public BigInteger getProdSubAssetCodeSrc()
  {
	return m_prodSubAssetCodeSrc;
  }

  /**
   * @param prodSubAssetCodeSrc_ Field prodSubAssetCodeSrc to be setted.
   */
  public void setProdSubAssetCodeSrc( BigInteger prodSubAssetCodeSrc_ )
  {
	m_prodSubAssetCodeSrc = prodSubAssetCodeSrc_;
  }

  /**
   * @return Returns prodSubAssetTextSrc.
   */
  public String getProdSubAssetTextSrc()
  {
	return m_prodSubAssetTextSrc;
  }

  /**
   * @param prodSubAssetTextSrc_ Field prodSubAssetTextSrc to be setted.
   */
  public void setProdSubAssetTextSrc( String prodSubAssetTextSrc_ )
  {
	m_prodSubAssetTextSrc = prodSubAssetTextSrc_;
  } 

}
