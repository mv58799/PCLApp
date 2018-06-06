package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.bg.dao.BaseTbgOfficerDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTbgOfficerDAO extends BaseOracleDAO implements
    BaseTbgOfficerDAO
{

  protected String C_OFFCR_NBR = "OFFCR_NBR";

  protected String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  protected String C_OFFCR_CAT_CODE = "OFFCR_CAT_CODE";

  protected String C_OFFCR_CHNNL_CODE = "OFFCR_CHNNL_CODE";

  protected String C_OFFCR_START_DATE = "OFFCR_START_DATE";

  protected String C_OFFCR_STAT_CODE = "OFFCR_STAT_CODE";

  protected String C_OFFCR_EMAIL_NAME = "OFFCR_EMAIL_NAME";

  protected String C_OFFCR_REAL_NBR = "OFFCR_REAL_NBR";

  protected String C_OFFCR_PHONE_OP_CODE = "OFFCR_PHONE_OP_CODE";

  protected String C_OFFCR_PHONE_SEC_AREA_CODE = "OFFCR_PHONE_SEC_AREA_CODE";

  protected String C_OFFCR_PHONE_SEC_OP_CODE = "OFFCR_PHONE_SEC_OP_CODE";

  protected String C_OFFCR_PHONE_SEC_NBR = "OFFCR_PHONE_SEC_NBR";

  protected String C_OFFCR_PHONE_SEC_EXTN_NBR = "OFFCR_PHONE_SEC_EXTN_NBR";

  protected String C_OFFCR_SEC_CODE = "OFFCR_SEC_CODE";

  protected String C_OFFCR_REMOTE_CODE = "OFFCR_REMOTE_CODE";

  protected String C_OFFCR_NCKN_NAME = "OFFCR_NCKN_NAME";

}