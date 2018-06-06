package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 *@author acacio.domingos,Apr 10, 2007
 *
 */

public abstract class BaseOracleTplProdPlayerRoleDAO extends BaseOracleDAO implements
    BaseTplProdPlayerRoleDAO
{

  /** Constantes que representam os campos genericos das tabelas
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO#find(com.citibank.ods.entity.pl.BaseTplProdPlayerRoleEntity)
   */
  
  protected String C_PROD_CODE = "PROD_CODE";
  
  protected String C_SYS_CODE = "SYS_CODE";
  
  protected String C_SYS_SEG_CODE = "SYS_SEG_CODE";
  
  protected String C_PLYR_CNPJ_NBR = "PLYR_CNPJ_NBR";
  
  protected String C_PLYR_ROLE_TYPE_CODE = "PLYR_ROLE_TYPE_CODE";
  
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";
  
}
