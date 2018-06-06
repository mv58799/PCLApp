package com.citibank.ods.persistence.pl.dao;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProductCorpHistEntity;

public interface TplProductCorpHistDAO extends BaseTplProductCorpDAO
{

	  /**
	   * Métodos Abstratos
	   *  
	   */

	  public TplProductCorpHistEntity insert( TplProductCorpHistEntity tplProductCorpHistEntity_ )
	                                                                  throws UnexpectedException;

	  public DataSet list( String prodCode_, Date refDate_);

	}