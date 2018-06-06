/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetMovEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdAssetMovEntity extends BaseTplProdAssetEntity
	{
	  /**
	   * Constante do tamanho do Qualificador
	   */
	  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

	  /**
	   * Construtor padrão - sem argumentos
	   */
	  public TplProdAssetMovEntity()
	  {
		m_data = new TplProdAssetMovEntityVO();
	  }

	  /*
	   * Construtor parametro current
	   */
	  public TplProdAssetMovEntity( TplProdAssetEntity prvtEntity_,
									  String opernCode_ )
	  {
		m_data = new TplProdAssetMovEntityVO();					 

		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setProdAssetText( prvtEntity_.getData().getProdAssetText() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setProdAssetCode( prvtEntity_.getData().getProdAssetCode() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setAssetClassCustRptOrderNbr(prvtEntity_.getData().getAssetClassCustRptOrderNbr());
		( ( TplProdAssetMovEntityVO ) m_data ).setOpernCode( opernCode_ );
	  }

}
