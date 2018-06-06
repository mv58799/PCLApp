/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetDAO;

/**
 * @author ronaldo.machado
 */
public abstract class BaseOracleTplProdAssetDAO extends BaseOracleDAO 
				implements BaseTplProdAssetDAO
{
  
  protected String C_PROD_ASSET_CODE = "ASSET_CLASS_CODE";

  protected String C_PROD_ASSET_TEXT = "ASSET_CLASS_TEXT";

  protected String C_LAST_UPD_DATE = "ASSET_CLASS_LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "ASSET_CLASS_LAST_UPD_USER_ID";
  
  protected String C_ASSET_CLASS_CUST_RPT_ORDER_NBR = "ASSET_CLASS_CUST_RPT_ORDER_NBR";
  
  protected String C_REC_STAT_CODE = "ASSET_CLASS_STAT_CODE";

}
