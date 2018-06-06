/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity;

/**
 * @author leonardo.nakada
 * 
 */
public interface TplProdSubFamlPrvtMovDAO extends BaseTplProdSubFamlPrvtDAO {
	public DataSet list(BigInteger prodFamlCode_, String prodFamlName_,
			String prodFamlText_, String lastUpdUserId_);

	public TplProdSubFamlPrvtMovEntity insert(
			TplProdSubFamlPrvtMovEntity tplProdSubFamlPrvtMovEntity_);

	public void update(
			TplProdSubFamlPrvtMovEntity productSubFamilyPrvtMovEntity_);

	public void delete(
			TplProdSubFamlPrvtMovEntity productSubFamilyPrvtMovEntity_);

	public boolean exists(
			TplProdSubFamlPrvtMovEntity productSubFamilyPrvtMovEntity_);
}