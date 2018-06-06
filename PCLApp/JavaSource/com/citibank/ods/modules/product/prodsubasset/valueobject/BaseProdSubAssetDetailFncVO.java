/*
 * Created on 20/08/2008
 */
package com.citibank.ods.modules.product.prodsubasset.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;

/**
 * @author rcoelho
 */
public class BaseProdSubAssetDetailFncVO  extends BaseODSFncVO
{
  /**
   * Constante de descrição do nome do campo da Sub Classe do Produto
   */
  public static final String C_PROD_SUBASSET_CODE_DESCRIPTION = "Código da Sub Classe";

  /**
   * Constante de descrição do nome do campo de Descrição da Qualificação do
   * Produto
   */
  public static final String C_PROD_SUBASSET_TEXT_DESCRIPTION = "Descrição da Sub Classe";

  protected BaseTplProdSubAssetEntity m_baseTplProdSubAssetEntity;
  
  /**
 	 * Lista do Domain da Asset
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
  private DataSet m_prodAssetCodeDomain = null;

  public void setProdAssetCodeDomain(DataSet prodAssetCodeDomain_)
  {
  	 m_prodAssetCodeDomain = prodAssetCodeDomain_;
  }
   
  public DataSet getProdAssetCodeDomain()
  {
	return m_prodAssetCodeDomain; 
  }	

  /**
   * @return Returns the m_baseTplProdSubAssetEntity.
   */
  public BaseTplProdSubAssetEntity getBaseTplProdSubAssetEntity()
  {
	return m_baseTplProdSubAssetEntity;
  }

  /**
   * @param tplProdSubAssetEntity The m_baseTplProdSubAssetEntity to set.
   */
  public void setBaseTplProdSubAssetEntity(
										   BaseTplProdSubAssetEntity tplProdSubAssetEntity_ )
  {
	m_baseTplProdSubAssetEntity = tplProdSubAssetEntity_;
  }


}
