package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProductCorpEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;

public interface BaseTplProductCorpDAO {
	  /**
	   * Métodos Abstratos
	   *  
	   */

	public BaseTplProductCorpEntity find(BaseTplProductEntity baseTplProductEntity) throws UnexpectedException;
}
