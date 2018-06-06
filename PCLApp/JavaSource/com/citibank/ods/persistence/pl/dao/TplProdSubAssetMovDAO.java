/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubAssetMovEntity;

/**
 * @author rcoelho
 */
public interface TplProdSubAssetMovDAO extends BaseTplProdSubAssetDAO
{
	  public DataSet list( BigInteger prodSubAssetCode_, String prodSubAssetText_ , String lastUpdUserId);

	  public TplProdSubAssetMovEntity insert(
											 TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ );

	  public void update( TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ );

	  public void delete( TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ );
	  
	  public boolean exists( TplProdSubAssetMovEntity tplProdSubAssetMovEntity_ );

}
