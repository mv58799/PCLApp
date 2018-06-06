package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCurAcctPrmntInstrDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,05/06/2007
 *  
 */

public class BaseOracleTplCurAcctPrmntInstrDAO extends BaseOracleDAO implements
    BaseTplCurAcctPrmntInstrDAO
{

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  protected String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  protected String C_CUST_NBR = "CUST_NBR";

  protected String C_PRMNT_INSTR_CODE = "PRMNT_INSTR_CODE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  public ArrayList selectByPK( BigInteger custNbr_, BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    return null;
  }
}