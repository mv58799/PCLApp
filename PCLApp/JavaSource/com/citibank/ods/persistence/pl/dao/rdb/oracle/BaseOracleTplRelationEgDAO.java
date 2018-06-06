package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplRelationEgDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_RELATION_EG
 * @author leonardo.nakada
 * @date 15/04/2007
 */
public abstract class BaseOracleTplRelationEgDAO extends BaseOracleDAO
    implements BaseTplRelationEgDAO
{
  protected static final String C_EG_NBR = "EG_NBR";

  protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected static final String C_RELTN_NBR = "RELTN_NBR";

}