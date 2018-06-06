package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerDAO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,04/04/2007
 * 
 * <PRE>
 * o
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTplPlayerDAO extends BaseOracleDAO implements
    BaseTplPlayerDAO
{

  protected String C_PLYR_CNPJ_NBR = "PLYR_CNPJ_NBR";

  protected String C_PLYR_NAME = "PLYR_NAME";
  
  protected String C_PLYR_ROLE_TYPE_CODE = "PLYR_ROLE_TYPE_CODE";

  protected String C_PLYR_ADDR_TEXT = "PLYR_ADDR_TEXT";

  protected String C_PLYR_DUE_DLG_DATE = "PLYR_DUE_DLG_DATE";

  protected String C_PLYR_DUE_DLG_EXEC_IND = "PLYR_DUE_DLG_EXEC_IND";

  protected String C_PLYR_DUE_DLG_END_DATE = "PLYR_DUE_DLG_END_DATE";

  protected String C_PLYR_DUE_DLG_RNW_DATE = "PLYR_DUE_DLG_RNW_DATE";

  protected String C_PLYR_INVST_CMTTE_APPRV_DATE = "PLYR_INVST_CMTTE_APPRV_DATE";

  protected String C_PLYR_APPRV_RSTRN_TEXT = "PLYR_APPRV_RSTRN_TEXT";

  protected String C_PLYR_SUPL_SERV_TEXT = "PLYR_SUPL_SERV_TEXT";

  protected String C_PLYR_CMNT_TEXT = "PLYR_CMNT_TEXT";
  
  protected String C_PLYR_DV_CODE = "PLYR_DV_CODE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

}