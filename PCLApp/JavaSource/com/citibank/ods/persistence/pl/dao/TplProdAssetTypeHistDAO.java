/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdAssetTypeHistEntity;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public interface TplProdAssetTypeHistDAO extends
	BaseTplProdAssetTypeDAO
{
  public DataSet list( BigInteger prodAssetTypeCode_, BigInteger prodSubAssetCode_, String prodAssetTypeText_ , String lastUpdUserId_ );

  public TplProdAssetTypeHistEntity insert(
										TplProdAssetTypeHistEntity prodAssetTypeHistEntity_ );
}