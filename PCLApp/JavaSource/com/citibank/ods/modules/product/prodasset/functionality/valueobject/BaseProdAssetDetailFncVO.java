/*
 * Created on 20/08/2008
 */
package com.citibank.ods.modules.product.prodasset.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdAssetEntity;

/**
 * @author rcoelho
 */
public class BaseProdAssetDetailFncVO  extends BaseODSFncVO
{
  /**
   * Constante de descrição do nome do campo da Sub Classe do Produto
   */
  public static final String C_PROD_ASSET_CODE_DESCRIPTION = "Código Classe";

  /**
   * Constante de descrição do nome do campo de Descrição da Qualificação do
   * Produto
   */
  public static final String C_PROD_ASSET_TEXT_DESCRIPTION = "Descrição da Classe";

  protected BaseTplProdAssetEntity m_baseTplProdAssetEntity;
  
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
   * @return Returns the m_baseTplProdAssetEntity.
   */
  public BaseTplProdAssetEntity getBaseTplProdAssetEntity()
  {
	return m_baseTplProdAssetEntity;
  }

  /**
   * @param tplProdAssetEntity The m_baseTplProdAssetEntity to set.
   */
  public void setBaseTplProdAssetEntity(
										   BaseTplProdAssetEntity tplProdAssetEntity_ )
  {
	m_baseTplProdAssetEntity = tplProdAssetEntity_;
  }


}
