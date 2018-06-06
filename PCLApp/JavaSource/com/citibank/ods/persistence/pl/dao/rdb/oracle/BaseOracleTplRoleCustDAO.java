package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplRoleCustDAO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseOracleTplRoleCustDAO extends BaseOracleDAO implements
    BaseTplRoleCustDAO
{
  protected static final String C_ROLE_CUST_CODE = "ROLE_CUST_CODE";

  protected static final String C_ROLE_CUST_TEXT = "ROLE_CUST_TEXT";

  protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected static final String C_REC_STAT_CODE = "REC_STAT_CODE";

}