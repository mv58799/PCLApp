/*
 * Created on 20/08/2008
 */
package com.citibank.ods.modules.product.prodassettype.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;

/**
 * @author rcoelho
 */
public class BaseProdAssetTypeDetailFncVO extends BaseODSFncVO {
	/**
	 * Constante de descrição do nome do campo da Sub Classe do Produto
	 */
	public static final String C_PROD_SUBASSET_CODE_DESCRIPTION =
		"Código do Tipo de Ativo";

	/**
	 * Constante de descrição do nome do campo de Descrição da Qualificação do
	 * Produto
	 */
	public static final String C_PROD_SUBASSET_TEXT_DESCRIPTION =
		"Descrição do Tipo de Ativo";

	protected BaseTplProdAssetTypeEntity m_baseTplProdAssetTypeEntity;

	/**
		 * Lista do Domain da Asset
		 * 
		 * @generated "UML to Java
		 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
		 */
	private DataSet m_prodAssetCodeDomain = null;

	/**
		   * Lista do Domain da SubAsset
		   * 
		   * @generated "UML to Java
		   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
		   */
	private DataSet m_prodSubAssetCodeDomain = null;
	
	private boolean m_RefreshAssetCode = false;

	public void setProdAssetCodeDomain(DataSet prodAssetCodeDomain_) {
		m_prodAssetCodeDomain = prodAssetCodeDomain_;
	}

	public DataSet getProdAssetCodeDomain() {
		return m_prodAssetCodeDomain;
	}

	/**
	 * @return Returns the m_baseTplProdAssetTypeEntity.
	 */
	public BaseTplProdAssetTypeEntity getBaseTplProdAssetTypeEntity() {
		return m_baseTplProdAssetTypeEntity;
	}

	/**
	 * @param tplProdAssetTypeEntity The m_baseTplProdAssetTypeEntity to set.
	 */
	public void setBaseTplProdAssetTypeEntity(BaseTplProdAssetTypeEntity tplProdAssetTypeEntity_) {
		m_baseTplProdAssetTypeEntity = tplProdAssetTypeEntity_;
	}

	/**
	 * @return
	 */
	public DataSet getProdSubAssetCodeDomain() {
		return m_prodSubAssetCodeDomain;
	}

	/**
	 * @param set
	 */
	public void setProdSubAssetCodeDomain(DataSet set) {
		m_prodSubAssetCodeDomain = set;
	}

	/**
	 * @return
	 */
	public boolean isRefreshAssetCode() {
		return m_RefreshAssetCode;
	}

	/**
	 * @param m_RefreshAssetCode_
	 */
	public void setRefreshAssetCode(boolean m_RefreshAssetCode_) {
		m_RefreshAssetCode = m_RefreshAssetCode_;
	}
	
}
