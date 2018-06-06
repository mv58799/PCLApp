package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductEntity;

public interface TplProductCorpDAO extends BaseTplProductCorpDAO {
	
	public TplProductCorpEntity insert(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException;

	public void update(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException;

	public void delete(TplProductCorpEntity tplProductCorpEntity_) throws UnexpectedException;

	public boolean exists(TplProductEntity tplProductEntity_) throws UnexpectedException;
	
	/*public DataSet list(String prodCode_, BigInteger prodFamlCode_, String prodName_, BigInteger prodQlfyCode_, BigInteger prodRiskCatCode_,
			BigInteger prodSubFamlCode_, String orderBy_);

	public TplProductEntity find(TplProductEntity TplProductEntity_) throws UnexpectedException;

	

	public boolean existsActive(TplProductEntity TplProductEntity_) throws UnexpectedException;

	public boolean existsProductByForeignKey(String prvtProdAggrCode_, BigInteger prodQlfyCode_, BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
			BigInteger assetTypeCode_) throws UnexpectedException;

	public DataSet loadDomain();

	public DataSet loadDomainPMA();
	*/
}
