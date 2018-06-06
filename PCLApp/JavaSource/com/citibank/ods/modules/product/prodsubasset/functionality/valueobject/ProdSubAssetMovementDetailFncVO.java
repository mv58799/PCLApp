/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProdSubAssetMovEntity;

/**
 * @author lfabiano
 * @since 06/10/2008
 */
public class ProdSubAssetMovementDetailFncVO extends
	BaseProdSubAssetDetailFncVO
{
  /**
   * Construtor sem argumento
   */
  public ProdSubAssetMovementDetailFncVO()
  {
	m_baseTplProdSubAssetEntity = new TplProdSubAssetMovEntity();
  }
}