package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;

public interface TplAssetClassOnesrcDao extends BaseTplProdRiskCatPrvtDAO
{
	public DataSet loadDomain();
}