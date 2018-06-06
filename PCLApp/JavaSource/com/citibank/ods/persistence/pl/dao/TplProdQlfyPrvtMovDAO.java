/*
 * Created on Mar 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TplProdQlfyPrvtMovDAO extends BaseTplProdQlfyPrvtDAO
{
	  public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_ , String lastUpdUserId);

	  public TplProdQlfyPrvtMovEntity insert(
	                                         TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ );

	  public void update( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ );

	  public void delete( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ );
	  
	  public boolean exists( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_ );

}
