/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 */
public interface TplProdSubFamlPrvtDAO extends BaseTplProdSubFamlPrvtDAO {
	public DataSet list(BigInteger prodSubFamlCode_, String prodSubFamlName_,
			String prodSubFamlText_);

	public TplProdSubFamlPrvtEntity insert(
			TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);

	public void update(TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);

	public void delete(TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);

	public boolean exists(
			TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);
	
	public boolean existsActive(
	          			TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);
	
	public boolean existsProductFamilyPrvtDependency(
	          			TplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity_);
	
	public DataSet loadDomain();
}