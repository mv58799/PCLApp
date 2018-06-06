/*
 * Created on 02/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;


import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetHistEntityVO;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdAssetHistEntity extends
	BaseTplProdAssetEntity
{
  
	/**
   * Construtor padrão - sem argumentos
   */
  public TplProdAssetHistEntity()
	  {
		m_data = new TplProdAssetHistEntityVO();
	  }

	  /*
	   * Construtor parametro current
	   */
	  public TplProdAssetHistEntity( TplProdAssetEntity prvtEntity_,
									   Date assetClassRefDate_ )
	  {
		m_data = new TplProdAssetHistEntityVO();					 

		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setProdAssetText( prvtEntity_.getData().getProdAssetText() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastAuthUserId() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastAuthDate() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setAssetClassCustRptOrderNbr(prvtEntity_.getData().getAssetClassCustRptOrderNbr());
		( ( TplProdAssetHistEntityVO ) m_data ).setAssetClassRefDate( assetClassRefDate_ );
		
	  }

}
