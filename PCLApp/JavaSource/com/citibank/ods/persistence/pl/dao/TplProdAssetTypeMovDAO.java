/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetTypeMovEntity;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplProdAssetTypeMovDAO extends BaseTplProdAssetTypeDAO
{
	  public DataSet list( BigInteger prodAssetTypeCode_, String prodAssetTypeText_ , String lastUpdUserId);

	  public TplProdAssetTypeMovEntity insert(
											 TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ );

	  public void update( TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ );

	  public void delete( TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ );
	  
	  public boolean exists( TplProdAssetTypeMovEntity tplProdAssetTypeMovEntity_ );

}
