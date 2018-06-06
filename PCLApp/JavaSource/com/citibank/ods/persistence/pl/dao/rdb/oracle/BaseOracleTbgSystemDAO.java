package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.entity.pl.BaseTbgSystemEntity;
import com.citibank.ods.persistence.pl.dao.BaseTbgSystemDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Base Oracle da table TBG_SYSTEM
 * 
 * @author michele.monteiro,02/05/2007
 *  
 */

public class BaseOracleTbgSystemDAO extends BaseOracleDAO implements
    BaseTbgSystemDAO
{
  
  
  public BaseTbgSystemEntity find( BaseTbgSystemEntity systemEntity_ )
  {
    return null;
  }

  /**
   * Data set retorna conteúdo do combo Sistema.
   */
  public DataSet loadSysCodeDomain()
  {

    return null;
  }
}