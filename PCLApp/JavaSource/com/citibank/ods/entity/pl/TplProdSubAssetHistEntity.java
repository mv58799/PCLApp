/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetHistEntityVO;

  /**
   * @author lfabiano
   * @since 06/10/2008
   */
  public class TplProdSubAssetHistEntity extends
	BaseTplProdSubAssetEntity
  {
  
	/**
   * Construtor padrão - sem argumentos
   */
  public TplProdSubAssetHistEntity()
	  {
		m_data = new TplProdSubAssetHistEntityVO();
	  }

	  /*
	   * Construtor parametro current
	   */
	  public TplProdSubAssetHistEntity( TplProdSubAssetEntity prvtEntity_,
									   Date subAssetRefDate_ )
	  {
		m_data = new TplProdSubAssetHistEntityVO();					 

		m_data.setProdSubAssetCode( prvtEntity_.getData().getProdSubAssetCode() );
		m_data.setProdSubAssetText( prvtEntity_.getData().getProdSubAssetText() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastAuthUserId() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastAuthDate() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setSubAssetClassRptOrderNbr(prvtEntity_.getData().getSubAssetClassRptOrderNbr());
		( ( TplProdSubAssetHistEntityVO ) m_data ).setSubAssetRefDate( subAssetRefDate_ );
		
	  }
}
