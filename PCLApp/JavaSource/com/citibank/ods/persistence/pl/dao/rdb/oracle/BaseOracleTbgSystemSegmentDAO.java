package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.entity.pl.BaseTbgSystemSegmentEntity;
import com.citibank.ods.persistence.pl.dao.BaseTbgSystemSegmentDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @author michele.monteiro,02/05/2007
 *  
 */

public class BaseOracleTbgSystemSegmentDAO extends BaseOracleDAO implements
    BaseTbgSystemSegmentDAO
{

  public BaseTbgSystemSegmentEntity find(
                                         BaseTbgSystemSegmentEntity systemSegmentEntity_ )
  {
    return null;
  }

  /**
   * Popula o combo de segmento do sistema.
   */
  public DataSet loadSysSegCodeDomain()
  {
    return null;
  }
}