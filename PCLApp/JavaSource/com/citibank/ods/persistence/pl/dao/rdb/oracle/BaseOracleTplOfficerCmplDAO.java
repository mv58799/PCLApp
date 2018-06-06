package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO;
/**
 * @author gerson.a.rodrigues
 * 
 * 
 * Classe Base do DAO de OfficerCmpl
 */
public abstract class BaseOracleTplOfficerCmplDAO extends BaseOracleDAO
    implements BaseTplOfficerCmplDAO
{

  /*
   * Colunas da tabela
   */
  protected String C_OFFCR_NBR = "OFFCR_NBR";
  
  protected String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  protected String C_OFFCR_TYPE_CODE = "OFFCR_TYPE_CODE";

  protected String C_OFFCR_INTL_NBR = "OFFCR_INTL_NBR";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  protected String C_REC_STAT_CODE = "REC_STAT_CODE";

  protected String C_REC_STAT_TEXT = "REC_STAT_TEXT";

}