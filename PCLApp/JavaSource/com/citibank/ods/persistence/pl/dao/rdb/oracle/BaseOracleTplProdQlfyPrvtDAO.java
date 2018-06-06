/*
 * Created on Mar 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO;

/**
 * @author fernando.salgado
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseOracleTplProdQlfyPrvtDAO extends BaseOracleDAO 
implements BaseTplProdQlfyPrvtDAO
{

  protected String C_PROD_QLFY_CODE = "PROD_QLFY_CODE";

  protected String C_PROD_QLFY_TEXT = "PROD_QLFY_TEXT";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

}
