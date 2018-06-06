package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductFamilyPrvtDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_PRODUCT_FAMILY_PRVT
 * @author marcelo.s.oliveira
 * @date 19/03/2007
 */
public abstract class BaseOracleTplProductFamilyPrvtDAO extends BaseOracleDAO
    implements BaseTplProductFamilyPrvtDAO
{
  protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected static final String C_PROD_FAML_CODE = "PROD_FAML_CODE";

  protected static final String C_PROD_FAML_NAME = "PROD_FAML_NAME";

  protected static final String C_PROD_FAML_TEXT = "PROD_FAML_TEXT";

}