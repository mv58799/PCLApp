package com.citibank.ods.persistence.pl.dao;

import java.util.List;

import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;

public interface TplRiskFamilyProdPlayerDAO extends BaseTplRiskFamilyProdPlayerDAO{
	
	  /**
	   * Métodos Abstratos
	   *  
	   */

	  public TplRiskFamilyProdPlayerEntity insert( TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity );

	  public TplRiskFamilyProdPlayerEntity update( TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity );

	  public TplRiskFamilyProdPlayerEntity delete( TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity );

	  public List<TplRiskFamilyProdPlayerEntity> list(TplProductEntity tplProductEntity, String recStatCode);
	  
	  public boolean exists(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity);

	  public void inactivateEmissor(TplProductEntity tplProductEntity);

}
