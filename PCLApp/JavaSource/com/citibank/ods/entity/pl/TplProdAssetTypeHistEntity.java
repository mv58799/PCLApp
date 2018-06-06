/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeHistEntityVO;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class TplProdAssetTypeHistEntity extends
	BaseTplProdAssetTypeEntity
{	

	/**
   * Construtor padrão - sem argumentos
   */
	public TplProdAssetTypeHistEntity()
	{
		m_data = new TplProdAssetTypeHistEntityVO();
	}
	  /*
	   * Construtor parametro current
	   */
	  public TplProdAssetTypeHistEntity( TplProdAssetTypeEntity prvtEntity_,
									     Date assetTypeRefDate_ )
	{
		m_data = new TplProdAssetTypeHistEntityVO();					 

		m_data.setProdAssetTypeCode( prvtEntity_.getData().getProdAssetTypeCode() );
		m_data.setProdSubAssetCode( prvtEntity_.getData().getProdSubAssetCode() );
		m_data.setProdAssetTypeText( prvtEntity_.getData().getProdAssetTypeText() );
		m_data.setAssetTypeCustRptOrderNbr(prvtEntity_.getData().getAssetTypeCustRptOrderNbr());
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastAuthDate() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastAuthUserId() );
		( ( TplProdAssetTypeHistEntityVO ) m_data ).setAssetTypeRefDate( assetTypeRefDate_ );
		
	}

}
