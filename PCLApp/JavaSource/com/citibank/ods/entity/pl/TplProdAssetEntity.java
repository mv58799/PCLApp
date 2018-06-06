/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdAssetEntity extends BaseTplProdAssetEntity {

	/**
	  * Construtor padrão - sem argumentos 
	  */
	public TplProdAssetEntity() {
		m_data = new TplProdAssetEntityVO();
	}

	/**
	 * Construtor - Carrega os atributos com os atributos do movimento
	 */
	public TplProdAssetEntity(TplProdAssetMovEntity tplProdAssetMovEntity_,Date lastAuthDate_,
								String lastAuthUserId_,String recStatCode_) {
		
		m_data = new TplProdAssetEntityVO();

		m_data.setProdAssetCode(tplProdAssetMovEntity_.getData().getProdAssetCode());
		
		m_data.setProdAssetText(tplProdAssetMovEntity_.getData().getProdAssetText());
		
		m_data.setAssetClassCustRptOrderNbr(tplProdAssetMovEntity_.getData().getAssetClassCustRptOrderNbr());
		
		m_data.setLastUpdDate(tplProdAssetMovEntity_.getData().getLastUpdDate());
		
		m_data.setLastUpdUserId(tplProdAssetMovEntity_.getData().getLastUpdUserId());
		
		((TplProdAssetEntityVO) m_data).setLastAuthDate(lastAuthDate_);
		((TplProdAssetEntityVO) m_data).setLastAuthUserId(lastAuthUserId_);
		((TplProdAssetEntityVO) m_data).setRecStatCode(recStatCode_);

	}

}
