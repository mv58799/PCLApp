/*
 * Created on 22/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseOracleTplProdAssetTypeDAO extends BaseOracleDAO 
				implements BaseTplProdAssetTypeDAO
{

  protected String C_PROD_ASSET_TYPE_CODE = "ASSET_TYPE_CODE";
  
  protected String C_PROD_SUB_ASSET_CODE = "SUB_ASSET_CLASS_CODE";
  
  protected String C_PROD_ASSET_CODE = "ASSET_CLASS_CODE";

  protected String C_PROD_ASSET_TYPE_TEXT = "ASSET_TYPE_TEXT";

  protected String C_LAST_UPD_DATE = "ASSET_TYPE_LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "ASSET_TYPE_LAST_UPD_USER_ID";
  
  protected String C_ASSET_TYPE_CUST_RPT_ORDER_NBR = "ASSET_TYPE_CUST_RPT_ORDER_NBR";
  
  protected String C_ASSET_TEXT = "ASSET_CLASS_TEXT";

  protected String C_ASSET_CODE = "ASSET_CLASS_CODE";

  protected String C_SUB_ASSET_CODE = "SUB_ASSET_CLASS_CODE";

  protected String C_SUB_ASSET_TEXT = "SUB_ASSET_CLASS_TEXT";
  
  /*
	   * Nome das tabelas
	   */
  protected static final String C_TPL_ASSET_TYPE =
		  C_PL_SCHEMA + "TPL_ASSET_TYPE";

  protected static final String C_TPL_ASSET_CLASS =
		  C_PL_SCHEMA + "TPL_ASSET_CLASS";

  protected static final String C_TPL_SUB_ASSET_CLASS =
		  C_PL_SCHEMA + "TPL_SUB_ASSET_CLASS";


}
