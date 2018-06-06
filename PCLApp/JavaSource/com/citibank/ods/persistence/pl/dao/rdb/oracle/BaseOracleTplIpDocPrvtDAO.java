package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTplIpDocPrvtDAO extends BaseOracleDAO implements
    BaseTplIpDocPrvtDAO
{
  protected String C_IP_DOC_CODE = "PRMNT_INSTR_CODE";

  protected String C_IP_DOC_TEXT = "PRMNT_INSTR_TEXT";

  protected String C_IP_INVST_CUR_ACCT_IND = "PRMNT_INSTR_INVST_CUR_ACCT_IND";

  protected String C_CUST_NBR = "CUST_NBR";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";
}