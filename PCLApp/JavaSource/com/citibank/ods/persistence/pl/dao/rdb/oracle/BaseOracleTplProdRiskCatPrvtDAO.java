/*
 * Created on Mar 14, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseOracleTplProdRiskCatPrvtDAO extends BaseOracleDAO
    implements BaseTplProdRiskCatPrvtDAO
{
  protected String C_PROD_INVST_RISK_CODE = "PROD_INVST_RISK_CODE";

  protected String C_PROD_INVST_RISK_TEXT = "PROD_INVST_RISK_TEXT";    

//  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";
//
//  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
}