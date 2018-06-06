package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTbgSegmentDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,Apr 24, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTbgSegmentDAO extends BaseOracleDAO implements
    BaseTbgSegmentDAO
{
  protected String C_SEG_NAME_CODE = "SEG_NAME_CODE";

  protected String C_SEG_NAME_TEXT = "SEG_NAME_TEXT";

  protected String C_SEG_CUST_TYPE_CODE = "SEG_CUST_TYPE_CODE";

  protected String C_SEG_START_DATE = "SEG_START_DATE";

  protected String C_SEG_STAT_CODE = "SEG_STAT_CODE";

  protected String C_SEG_MTH_INCO_MIN_CODE = "SEG_MTH_INCO_MIN_CODE";

  protected String C_SEG_TOTAL_SALE_MIN_AMT = "SEG_TOTAL_SALE_MIN_AMT";

  protected String C_SEG_CITI_BUS_CODE = "SEG_CITI_BUS_CODE";

  protected String C_SEG_PRIOR_CODE = "SEG_PRIOR_CODE";
}