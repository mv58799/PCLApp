/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author rcoelho
 */
public class BaseProdAssetTypeListFncVO extends BaseODSFncVO
{
  /**
   * Constante do Qualificador que descreve o nome do campo do Código da
   * Sub Classe da tela
   */
  public static final String C_PROD_ASSETTYPE_CODE_DESCRIPTION = "Código do Tipo de Ativo";

  /**
   * Constante do Qualificador que descreve o nome do campo da Descrição de
   * Sub Classe da tela
   */
  public static final String C_PROD_ASSETTYPE_TEXT_DESCRIPTION = "Descrição do Tipo de Ativo";

  /*
   * Resultado da consulta
   */
  private DataSet m_results;

  /*
   * Comment for <code> m_prodAssetTypeCode </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodAssetTypeCodeSrc;

  /*
   * Comment for <code> m_prodAssetTypeText </code> @generated "UML to Java
   * (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodAssetTypeTextSrc;

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
   * @return Returns prodAssetTypeCodeSrc.
   */
  public BigInteger getProdAssetTypeCodeSrc()
  {
	return m_prodAssetTypeCodeSrc;
  }

  /**
   * @param prodAssetTypeCodeSrc_ Field prodAssetTypeCodeSrc to be setted.
   */
  public void setProdAssetTypeCodeSrc( BigInteger prodAssetTypeCodeSrc_ )
  {
	m_prodAssetTypeCodeSrc = prodAssetTypeCodeSrc_;
  }

  /**
   * @return Returns prodAssetTypeTextSrc.
   */
  public String getProdAssetTypeTextSrc()
  {
	return m_prodAssetTypeTextSrc;
  }

  /**
   * @param prodAssetTypeTextSrc_ Field prodAssetTypeTextSrc to be setted.
   */
  public void setProdAssetTypeTextSrc( String prodAssetTypeTextSrc_ )
  {
	m_prodAssetTypeTextSrc = prodAssetTypeTextSrc_;
  } 

}
