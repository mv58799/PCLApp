/*
 * Created on Mar 12, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplAggrProdPrvtDAO;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseOracleTplFundsSubscriptionDAO extends BaseOracleDAO implements
    BaseTplAggrProdPrvtDAO
{
  protected String C_PRVT_PROD_AGGR_CODE = "PRVT_PROD_AGGR_CODE";
  protected String C_PRVT_PROD_AGGR_TEXT = "PRVT_PROD_AGGR_TEXT";
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";
  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";
}
