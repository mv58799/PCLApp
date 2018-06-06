/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author rcoelho
 */
public class BaseProdAssetListFncVO extends BaseODSFncVO
{
  /**
   * Constante do Qualificador que descreve o nome do campo do Código da
   * Sub Classe da tela
   */
  public static final String C_PROD_ASSET_CODE_DESCRIPTION = "Código da Sub Classe";

  /**
   * Constante do Qualificador que descreve o nome do campo da Descrição de
   * Sub Classe da tela
   */
  public static final String C_PROD_ASSET_TEXT_DESCRIPTION = "Descrição da Sub Classe";

  /*
   * Resultado da consulta
   */
  private DataSet m_results;

  /*
   * Comment for <code> m_prodAssetCode </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodAssetCodeSrc;

  /*
   * Comment for <code> m_prodAssetText </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodAssetTextSrc;

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
   * @return Returns prodAssetCodeSrc.
   */
  public BigInteger getProdAssetCodeSrc()
  {
	return m_prodAssetCodeSrc;
  }

  /**
   * @param prodAssetCodeSrc_ Field prodAssetCodeSrc to be setted.
   */
  public void setProdAssetCodeSrc( BigInteger prodAssetCodeSrc_ )
  {
	m_prodAssetCodeSrc = prodAssetCodeSrc_;
  }

  /**
   * @return Returns prodAssetTextSrc.
   */
  public String getProdAssetTextSrc()
  {
	return m_prodAssetTextSrc;
  }

  /**
   * @param prodAssetTextSrc_ Field prodAssetTextSrc to be setted.
   */
  public void setProdAssetTextSrc( String prodAssetTextSrc_ )
  {
	m_prodAssetTextSrc = prodAssetTextSrc_;
  } 

}
