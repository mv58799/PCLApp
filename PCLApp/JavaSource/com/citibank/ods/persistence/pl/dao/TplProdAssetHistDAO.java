/*
 * Created on 02/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetHistEntity;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TplProdAssetHistDAO extends
	BaseTplProdAssetDAO
{
  public DataSet list( BigInteger prodAssetCode_, String prodAssetText_ , String lastUpdUserId_ );

  public TplProdAssetHistEntity insert(
										TplProdAssetHistEntity prodAssetHistEntity_ );
}