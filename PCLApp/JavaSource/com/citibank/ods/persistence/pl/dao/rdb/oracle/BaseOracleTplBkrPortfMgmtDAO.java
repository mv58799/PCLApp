package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO;

/**
 * @author Hamilton Matos
 */

public abstract class BaseOracleTplBkrPortfMgmtDAO extends BaseOracleDAO
    implements BaseTplBkrPortfMgmtDAO
{

  protected String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  protected String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  protected String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  protected String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  protected String C_PORTF_MGMT_PROD_NAME = "PORTF_MGMT_PROD_NAME";

  protected String C_CUST_MNMC_NAME = "CUST_MNMC_NAME";

  protected String C_RELTN_NBR = "RELTN_NBR";

  protected String C_RELTN_CUST_1_NBR = "RELTN_CUST_1_NBR";

  protected String C_RELTN_CUST_2_NBR = "RELTN_CUST_2_NBR";

  protected String C_RELTN_CUST_3_NBR = "RELTN_CUST_3_NBR";

  protected String C_RELTN_CUST_4_NBR = "RELTN_CUST_4_NBR";

  protected String C_RELTN_CUST_5_NBR = "RELTN_CUST_5_NBR";

  protected String C_PROD_CODE = "PROD_CODE";

  protected String C_BKR_CNPJ_NBR = "BKR_CNPJ_NBR";

  protected String C_BKR_NAME_TEXT = "BKR_NAME_TEXT";

  protected String C_BKR_ADDR_TEXT = "BKR_ADDR_TEXT";

  protected String C_BOVESPA_CUR_ACCT_NBR = "BOVESPA_CUR_ACCT_NBR";

  protected String C_BOVESPA_INVST_ACCT_NBR = "BOVESPA_INVST_ACCT_NBR";

  protected String C_BMF_CUR_ACCT_NBR = "BMF_CUR_ACCT_NBR";

  protected String C_BMF_INVST_ACCT_NBR = "BMF_INVST_ACCT_NBR";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_TABLE_NAME = "TPL_BKR_PORTF_MGMT";

}