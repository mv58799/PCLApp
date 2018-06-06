/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetTypeEntity;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplProdAssetTypeDAO extends BaseTplProdAssetTypeDAO 
{
	  public DataSet list( BigInteger prodAssetTypeCode_, String prodAssetTypeText_ );

	  public TplProdAssetTypeEntity insert( TplProdAssetTypeEntity tplProdAssetTypeEntity_ );

	  public void update( TplProdAssetTypeEntity tplProdAssetTypeEntity_ );

	  public void delete( TplProdAssetTypeEntity tplProdAssetTypeEntity_ );
	  
	  public boolean exists( TplProdAssetTypeEntity tplProdAssetTypeEntity_ );
	  
	  public boolean existsActive( TplProdAssetTypeEntity tplProdAssetTypeEntity_ );
	  
	  public boolean existsAssetTypeByForeignKey(BigInteger subAssetCode_);
	  
	  public DataSet loadDomain();
	  
	  public DataSet loadSubAsset();
	  
	  public BigInteger loadAssetBySubAsset(BigInteger subAssetCode_);
	  
	  public DataSet loadFullDomain();
	  
	  

}