/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO;

/**
 * @author rcoelho
 */
public abstract class BaseOracleTplProdSubAssetDAO extends BaseOracleDAO 
				implements BaseTplProdSubAssetDAO
{

  protected String C_PROD_SUB_ASSET_CODE = "SUB_ASSET_CLASS_CODE";
  
  protected String C_PROD_ASSET_CODE = "ASSET_CLASS_CODE";

  protected String C_PROD_SUB_ASSET_TEXT = "SUB_ASSET_CLASS_TEXT";

  protected String C_LAST_UPD_DATE = "SUB_ASSET_LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "SUB_ASSET_LAST_UPD_USER_ID";
  
  protected String C_SUB_ASSET_CLASS_RPT_ORDER_NBR = "SUB_ASSET_CLASS_RPT_ORDER_NBR";

}
