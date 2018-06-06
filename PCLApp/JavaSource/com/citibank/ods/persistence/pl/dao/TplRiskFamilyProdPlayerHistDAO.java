package com.citibank.ods.persistence.pl.dao;

import java.util.List;

import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerHistEntity;


public interface TplRiskFamilyProdPlayerHistDAO extends BaseTplRiskFamilyProdPlayerHistDAO{
	  /**
	   * M�todos Abstratos
	   *  
	   */

	  public TplRiskFamilyProdPlayerHistEntity insert( TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity );

	  public TplRiskFamilyProdPlayerHistEntity update( TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity );

	  public TplRiskFamilyProdPlayerHistEntity delete( TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity );

	  public List<TplRiskFamilyProdPlayerHistEntity> list(String prodCode);
	  
	  public boolean exists( TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity );
}
