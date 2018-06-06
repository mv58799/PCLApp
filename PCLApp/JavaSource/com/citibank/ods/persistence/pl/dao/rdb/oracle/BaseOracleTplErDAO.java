/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseOracleTplErDAO extends BaseOracleDAO
{
  public String C_ER_NBR = "ER_NBR";

  public String C_ER_RELTN_TRF_IND = "ER_RELTN_TRF_IND";

  public String C_RELTN_END_REAS_CODE = "RELTN_END_REAS_CODE";
  
  public String C_RELTN_END_REAS_TEXT = "RELTN_END_REAS_TEXT";
  
  public String C_EQUITY_CLASS_CODE = "EQUITY_CLASS_CODE";
  
  public String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";
  
  public String C_LAST_AUTH_USER_ID = " LAST_AUTH_USER_ID";

  public String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  public String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  
  public String C_REC_STAT_CODE = "REC_STAT_CODE";
  
  /*
   * Tabelas auxiliares 
   */
  public static final String C_TPL_ER_EM = C_PL_SCHEMA + "TPL_ER_EM";
  
  /*
   * Colunas auxiliares
   * @author lfabiano
   *
   */
  
  public String C_SYS_CODE = "SYS_CODE";
  
  public String C_SYS_SEG_CODE = "SYS_SEG_CODE";
  
}