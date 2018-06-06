/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdAssetTypeMovEntityVO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TplProdAssetTypeMovEntity extends BaseTplProdAssetTypeEntity
	{
	  /**
	   * Constante do tamanho do Qualificador
	   */
	  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

	  /**
	   * Construtor padrão - sem argumentos
	   */
	  public TplProdAssetTypeMovEntity()
	  {
		m_data = new TplProdAssetTypeMovEntityVO();
	  }

	  /*
	   * Construtor parametro current
	   */
	  public TplProdAssetTypeMovEntity( TplProdAssetTypeEntity prvtEntity_,
									  String opernCode_ )
	  {
		m_data = new TplProdAssetTypeMovEntityVO();					 

		m_data.setProdAssetTypeCode( prvtEntity_.getData().getProdAssetTypeCode() );
		m_data.setProdAssetTypeText( prvtEntity_.getData().getProdAssetTypeText() );
		m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setProdSubAssetCode( prvtEntity_.getData().getProdSubAssetCode() );
		m_data.setLastAuthUserId( prvtEntity_.getData().getLastUpdUserId() );
		m_data.setLastAuthDate( prvtEntity_.getData().getLastUpdDate() );
		m_data.setRecStatCode( prvtEntity_.getData().getRecStatCode() );
		m_data.setAssetTypeCustRptOrderNbr(prvtEntity_.getData().getAssetTypeCustRptOrderNbr());
		( ( TplProdAssetTypeMovEntityVO ) m_data ).setOpernCode( opernCode_ );
	  }

}
