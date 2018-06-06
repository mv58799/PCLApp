/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality.valueobject;

import com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class ProdAssetTypeMovementDetailFncVO extends
	BaseProdAssetTypeDetailFncVO
{
  /**
   * Construtor sem argumento
   */
  public ProdAssetTypeMovementDetailFncVO()
  {
	m_baseTplProdAssetTypeEntity = new TplProdAssetTypeMovEntity();
  }
}