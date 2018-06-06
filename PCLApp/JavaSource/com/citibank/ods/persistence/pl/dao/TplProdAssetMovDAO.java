/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetMovEntity;

/**
 * @author rcoelho
 */
public interface TplProdAssetMovDAO extends BaseTplProdAssetDAO
{
	  public DataSet list( BigInteger prodAssetCode_, String prodAssetText_ , String lastUpdUserId);

	  public TplProdAssetMovEntity insert(
											 TplProdAssetMovEntity tplProdAssetMovEntity_ );

	  public void update( TplProdAssetMovEntity tplProdAssetMovEntity_ );

	  public void delete( TplProdAssetMovEntity tplProdAssetMovEntity_ );
	  
	  public boolean exists( TplProdAssetMovEntity tplProdAssetMovEntity_ );

}
