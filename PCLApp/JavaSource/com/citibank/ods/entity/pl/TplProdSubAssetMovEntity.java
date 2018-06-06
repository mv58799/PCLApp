/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubAssetMovEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdSubAssetMovEntity extends BaseTplProdSubAssetEntity
	{
	  /**
	   * Constante do tamanho do Qualificador
	   */
	  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

	  /**
	   * Construtor padrão - sem argumentos
	   */
	  public TplProdSubAssetMovEntity()
	  {
		m_data = new TplProdSubAssetMovEntityVO();
	  }

	  /*
	   * Construtor parametro current
	   */
	  public TplProdSubAssetMovEntity( TplProdSubAssetEntity prvtEntity_,
									  String opernCode_ )
	  {
		m_data = new TplProdSubAssetMovEntityVO();					 

		m_data.setProdSubAssetCode( prvtEntity_.getData().getProdSubAssetCode() );
		m_data.setProdSubAssetText( prvtEntity_.getData().getProdSubAssetText() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setSubAssetClassRptOrderNbr(prvtEntity_.getData().getSubAssetClassRptOrderNbr());
		( ( TplProdSubAssetMovEntityVO ) m_data ).setOpernCode( opernCode_ );
	  }

}
