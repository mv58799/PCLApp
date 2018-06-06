package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocCallbackDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTplIpDocCallbackDAO extends BaseOracleDAO implements
    BaseTplIpDocCallbackDAO
{

  public String C_CTC_NBR = "CTC_NBR";

  public String C_CUST_NBR = "CUST_NBR";

  public String C_IP_CALLBACK_CODE = "PRMNT_INSTR_CALLBACK_CODE";

  public String C_IP_DOC_CODE = "PRMNT_INSTR_CODE";

  public String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  public String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  
  public String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";
  
  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";
  
}