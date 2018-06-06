/*
 * Created on 02/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProdAssetMovEntity;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProdAssetMovementDetailFncVO extends
	BaseProdAssetDetailFncVO
{
  /**
   * Construtor sem argumento
   */
  public ProdAssetMovementDetailFncVO()
  {
	m_baseTplProdAssetEntity = new TplProdAssetMovEntity();
  }
}
