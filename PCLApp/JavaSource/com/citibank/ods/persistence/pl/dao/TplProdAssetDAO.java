/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetEntity;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplProdAssetDAO extends BaseTplProdAssetDAO 
{
	  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_ );

	  public TplProdAssetEntity insert( TplProdAssetEntity tplProdAssetEntity_ );

	  public void update( TplProdAssetEntity tplProdAssetEntity_ );

	  public void delete( TplProdAssetEntity tplProdAssetEntity_ );
	  
	  public boolean exists( TplProdAssetEntity tplProdAssetEntity_ );
	  
	  public boolean existsActive( TplProdAssetEntity tplProdAssetEntity_ );
	  
	  public DataSet loadDomain();

}
