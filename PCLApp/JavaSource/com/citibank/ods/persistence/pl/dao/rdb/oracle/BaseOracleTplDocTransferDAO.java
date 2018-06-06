package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplDocTransferDAO;

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

public abstract class BaseOracleTplDocTransferDAO extends BaseOracleDAO
    implements BaseTplDocTransferDAO
{
  public String C_AGN_BANK_CODE = "BANK_NBR";

  public String C_BRCH_CODE = "AGN_NBR";

  public String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  public String C_CUST_NBR = "CUST_NBR";

  public String C_IP_DOC_CODE = "PRMNT_INSTR_CODE";

  public String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  public String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  public String C_OWN_DEST_ACCT_IND = "OWN_DEST_ACCT_IND";

  public String C_TXN_TYPE_CODE = "TXN_TYPE_CODE";

  public String C_DOC_TRANSFER_CODE = "PRMNT_INSTR_TRF_DATA_CODE";
  
  public String C_BENE_NAME_TEXT = "BENE_NAME_TEXT";
  
  public String C_BENE_CPF_CNPJ_NBR = "BENE_CPF_CNPJ_NBR";
  
  public String C_BENE_ACCT_TYPE_CODE = "BENE_ACCT_TYPE_CODE";
  
  public String C_BENE_MAIN_DEST_ACCT_IND = "BENE_MAIN_DEST_ACCT_IND";  


}