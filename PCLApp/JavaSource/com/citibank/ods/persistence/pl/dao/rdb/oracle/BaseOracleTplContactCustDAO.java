package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplContactCustDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_CONTACT_CUST
 * @author Hamilton Matos
 * @date 26/03/2007
 */
public abstract class BaseOracleTplContactCustDAO extends BaseOracleDAO
    implements BaseTplContactCustDAO
{
  protected String C_TABLE_COLUMNS = "CTC_NBR,CUST_NBR,FULL_NAME_TEXT,LAST_AUTH_DATE,LAST_AUTH_USER_ID,LAST_UPD_DATE,LAST_UPD_USER_ID,PHONE_DDD_CODE,PHONE_EXTN_NBR,PHONE_NBR,REC_STAT_CODE";

  protected String C_CTC_NBR = "CTC_NBR";

  protected String C_CUST_NBR = "CUST_NBR";

  protected String C_FULL_NAME_TEXT = "FULL_NAME_TEXT";
  
  protected String C_FULL_NAME_TEXT_2 = "FULL_NAME_1_TEXT";
  
  protected String C_FULL_NAME_TEXT_3 = "FULL_NAME_2_TEXT";

  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_PHONE_DDD_CODE = "PHONE_DDD_CODE";

  protected String C_PHONE_EXTN_NBR = "PHONE_EXTN_NBR";

  protected String C_PHONE_NBR = "PHONE_NBR";

  protected String C_REC_STAT_CODE = "REC_STAT_CODE";

  protected String C_TABLE_NAME = C_PL_SCHEMA + "TPL_CONTACT_CUST";

}