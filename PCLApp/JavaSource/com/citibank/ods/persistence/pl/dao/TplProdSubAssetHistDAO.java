/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubAssetHistEntity;

/**
 * @author lfabiano
 * @since 06/10/2008
 */
public interface TplProdSubAssetHistDAO extends
	BaseTplProdSubAssetDAO
{
  public DataSet list( BigInteger prodSubAssetCode_, BigInteger prodAssetCode_, String prodSubAssetText_ , String lastUpdUserId_ );

  public TplProdSubAssetHistEntity insert(
										TplProdSubAssetHistEntity prodSubAssetHistEntity_ );
}