/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubAssetEntity;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplProdSubAssetDAO extends BaseTplProdSubAssetDAO 
{
	  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_ );

	  public TplProdSubAssetEntity insert( TplProdSubAssetEntity tplProdSubAssetEntity_ );

	  public void update( TplProdSubAssetEntity tplProdSubAssetEntity_ );

	  public void delete( TplProdSubAssetEntity tplProdSubAssetEntity_ );
	  
	  public boolean exists( TplProdSubAssetEntity tplProdSubAssetEntity_ );
	  
	  public boolean existsActive( TplProdSubAssetEntity tplProdSubAssetEntity_ );
	  
	  public boolean existsSubAssetByForeignKey(BigInteger assetCode_);
	  
	  public DataSet loadDomain();

}
