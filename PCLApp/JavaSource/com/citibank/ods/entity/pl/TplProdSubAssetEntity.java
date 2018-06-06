/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdSubAssetEntity extends BaseTplProdSubAssetEntity
{

	 /**
	   * Construtor padrão - sem argumentos 
	   */
	  public TplProdSubAssetEntity()
	  {
		m_data = new TplProdSubAssetEntityVO();
	  }
	  
	  /**
	   * Construtor - Carrega os atributos com os atributos do movimento
	   */
	  public TplProdSubAssetEntity( TplProdSubAssetMovEntity tplProdSubAssetMovEntity_, 
									Date lastAuthDate_, String lastAuthUserId_, 
									String recStatCode_ )
	  {
		 m_data = new TplProdSubAssetEntityVO();
	  	 
		 m_data.setProdSubAssetCode(tplProdSubAssetMovEntity_.getData().getProdSubAssetCode());
		 m_data.setProdAssetCode(tplProdSubAssetMovEntity_.getData().getProdAssetCode());
		 m_data.setProdSubAssetText(tplProdSubAssetMovEntity_.getData().getProdSubAssetText());
		 m_data.setLastUpdDate(tplProdSubAssetMovEntity_.getData().getLastUpdDate());
		 m_data.setLastUpdUserId(tplProdSubAssetMovEntity_.getData().getLastUpdUserId());
		((TplProdSubAssetEntityVO)m_data).setSubAssetClassRptOrderNbr(tplProdSubAssetMovEntity_.getData().getSubAssetClassRptOrderNbr());
		((TplProdSubAssetEntityVO)m_data).setLastAuthDate(lastAuthDate_);
		((TplProdSubAssetEntityVO)m_data).setLastAuthUserId(lastAuthUserId_);
		((TplProdSubAssetEntityVO)m_data).setRecStatCode(recStatCode_);
	  	
	  }
	  

}
