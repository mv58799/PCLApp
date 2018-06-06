/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdAssetTypeEntity extends BaseTplProdAssetTypeEntity
{
	 /**
	   * Construtor padrão - sem argumentos 
	   */
	  public TplProdAssetTypeEntity()
	  {
		m_data = new TplProdAssetTypeEntityVO();
	  }
	  
	  /**
	   * Construtor - Carrega os atributos com os atributos do movimento
	   */
	  public TplProdAssetTypeEntity( TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_, 
									Date lastAuthDate_, String lastAuthUserId_, 
									String recStatCode_ )
	  {
		 m_data = new TplProdAssetTypeEntityVO();
	  	 
		 m_data.setProdAssetTypeCode(tplProdAssetTypeMovEntity_.getData().getProdAssetTypeCode());
		 m_data.setProdSubAssetCode(tplProdAssetTypeMovEntity_.getData().getProdSubAssetCode());
		 m_data.setProdAssetTypeText(tplProdAssetTypeMovEntity_.getData().getProdAssetTypeText());
		 m_data.setAssetTypeCustRptOrderNbr(tplProdAssetTypeMovEntity_.getData().getAssetTypeCustRptOrderNbr());
		 m_data.setLastUpdDate(tplProdAssetTypeMovEntity_.getData().getLastUpdDate());
		 m_data.setLastUpdUserId(tplProdAssetTypeMovEntity_.getData().getLastUpdUserId());
		 ((TplProdAssetTypeEntityVO)m_data).setLastAuthDate(lastAuthDate_);
		 ((TplProdAssetTypeEntityVO)m_data).setLastAuthUserId(lastAuthUserId_);
		 ((TplProdAssetTypeEntityVO)m_data).setRecStatCode(recStatCode_);	  	
	  }
}
