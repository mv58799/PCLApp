/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;


/**
 * @author acacio.domingos
 *  
 */
public abstract class BaseOracleTplPlayerRoleDAO extends BaseOracleDAO implements
		BaseTplPlayerRoleDAO 

{
	protected String C_PLYR_CNPJ_NBR = "PLYR_CNPJ_NBR";
	
	protected String C_PLYR_ROLE_TYPE_CODE = "PLYR_ROLE_TYPE_CODE";
	
	protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
	
	protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";
	
	
}
