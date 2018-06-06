/*
 * Created on Jan 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplClassCmplcDAO;
/**
 * @author gerson.a.rodrigues
 * 
 */
public abstract class BaseOracleTplClassCmplcDAO extends BaseOracleDAO implements BaseTplClassCmplcDAO
{

  protected String C_CLASS_CMPLC_CODE = "CLASS_CMPLC_CODE";
  
  protected String C_CLASS_CMPLC_TEXT = "CLASS_CMPLC_TEXT";
  
  protected String C_SENS_IND         = "SENS_IND";
  
  protected String C_LAST_UPD_DATE    = "LAST_UPD_DATE";
  
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  
  protected String C_REC_STAT_CODE    = "REC_STAT_CODE"; 
  
  protected String C_STATUS_INATIVO = "I";
  
}