/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtEntity;

/**
 * @author fernando.salgado
 *
 */
public interface TplProdQlfyPrvtDAO extends BaseTplProdQlfyPrvtDAO 
{
	  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_ );

	  public TplProdQlfyPrvtEntity insert( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ );

	  public void update( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ );

	  public void delete( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ );
	  
	  public boolean exists( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ );
	  
	  public boolean existsActive( TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ );
	  
	  public DataSet loadDomain();

}
