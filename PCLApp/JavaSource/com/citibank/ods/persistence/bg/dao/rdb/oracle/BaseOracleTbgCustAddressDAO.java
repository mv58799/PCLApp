package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.bg.dao.BaseTbgCustAddressDAO;

/**
 * @author hamilton.matos
 */

public abstract class BaseOracleTbgCustAddressDAO extends BaseOracleDAO
    implements BaseTbgCustAddressDAO
{

  protected String C_CUST_NBR = "CUST_NBR";

  protected String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  protected String C_ADDR_SEQ_NBR = "ADDR_SEQ_NBR";

  protected String C_ADDR_TYPE_CODE = "ADDR_TYPE_CODE";

  protected String C_ADDR_TYPE_TEXT = "ADDR_TYPE_TEXT";

  protected String C_ADDR_CONTACT_NAME = "ADDR_CONTACT_NAME";

  protected String C_ADDR_NAME_TEXT = "ADDR_NAME_TEXT";

  protected String C_ADDR_CMPL_TEXT = "ADDR_CMPL_TEXT";

  protected String C_ADDR_NEIGHB_TEXT = "ADDR_NEIGHB_TEXT";

  protected String C_ADDR_CITY_TEXT = "ADDR_CITY_TEXT";

  protected String C_ADDR_STATE_CODE = "ADDR_STATE_CODE";

  protected String C_ADDR_CNTRY_CODE = "ADDR_CNTRY_CODE";

  protected String C_MAIL_BOX_NBR = "MAIL_BOX_NBR";

  protected String C_ZIP_CODE = "ZIP_CODE";

  protected String C_ZIP_EXTN_CODE = "ZIP_EXTN_CODE";

  protected String C_PHONE_AREA_CODE = "PHONE_AREA_CODE";

  protected String C_PHONE_OP_CODE = "PHONE_OP_CODE";

  protected String C_PHONE_NBR = "PHONE_NBR";

  protected String C_PHONE_EXTN_NBR = "PHONE_EXTN_NBR";

  protected String C_TELEX_AREA_CODE = "TELEX_AREA_CODE";

  protected String C_TELEX_NBR = "TELEX_NBR";

  protected String C_FAX_AREA_CODE = "FAX_AREA_CODE";

  protected String C_FAX_NBR = "FAX_NBR";

  protected String C_ZIP_CODE_CHANGE_TEXT = "ZIP_CODE_CHANGE_TEXT";

  protected String C_EMAIL_TEXT = "EMAIL_TEXT";

  protected String C_EMAIL_MAIL_IND = "EMAIL_MAIL_IND";

  protected String C_CELL_AREA_CODE = "CELL_AREA_CODE";

  protected String C_CELL_OP_CODE = "CELL_OP_CODE";

  protected String C_CELL_PHONE_NBR = "CELL_PHONE_NBR";

  protected String C_CELL_MAIL_IND = "CELL_MAIL_IND";
}