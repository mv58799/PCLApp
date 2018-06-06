package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.TplProductCorpMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;

public interface TplProductCorpMovDAO  extends BaseTplProductCorpDAO {

	  public TplProductCorpMovEntity insert( TplProductCorpMovEntity tplProductCorpMovEntity );

	  public TplProductCorpMovEntity update( TplProductCorpMovEntity tplProductCorpMovEntity );

	  public TplProductCorpMovEntity delete( TplProductCorpMovEntity tplProductCorpMovEntity );

//	  public DataSet list( String prodCode_, BigInteger prodFamlCode_,
//	                      String prodName_, BigInteger prodQlfyCode_,
//	                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
//	                      String lastUpdUserId_ );
	  
	  public boolean exists( TplProductEntity tplProductEntity );
}
