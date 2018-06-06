package com.citibank.ods.persistence.pl.dao;

import java.util.List;

import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;

public interface TplRiskFamilyProdPlayerMovDAO extends BaseTplRiskFamilyProdPlayerMovDAO{
	  /**
	   * Métodos Abstratos
	   *  
	   */

	  public TplRiskFamilyProdPlayerMovEntity insert( TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity );

	  public TplRiskFamilyProdPlayerMovEntity update( TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity );

	  public TplRiskFamilyProdPlayerMovEntity delete( TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity );

	  public List<TplRiskFamilyProdPlayerMovEntity> list(TplProductMovEntity tplProductMovEntity);
	  
	  public boolean exists( TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity );

	  public void deleteAll(TplProductMovEntity tplProductMovEntity);
}
