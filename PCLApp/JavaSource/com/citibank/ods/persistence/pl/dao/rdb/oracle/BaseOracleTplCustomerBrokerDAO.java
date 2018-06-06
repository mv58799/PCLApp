package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerBrokerDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_BROKER
 * @author Hamilton Matos
 */
public abstract class BaseOracleTplCustomerBrokerDAO extends BaseOracleDAO implements
    BaseTplCustomerBrokerDAO
{
  protected String C_TABLE_COLUMNS = "BKR_ADDR_TEXT,BKR_APPRV_CR_LIM_DLR_AMT,BKR_APPRV_CR_LIM_LCY_AMT,BKR_APPRV_DATE,BKR_APPRV_STAT_CODE,BKR_AUTH_PROC_SIT_TEXT,BKR_BMF_MKT_CODE,BKR_BOVESPA_MKT_CODE,BKR_CNPJ_NBR,BKR_COMMENT_TEXT,BKR_NAME_TEXT,BKR_RBT_BMF_RATE,BKR_RBT_BOVESPA_RATE,BKR_REQ_CR_LIM_DLR_AMT,BKR_REQ_CR_LIM_LCY_AMT,BKR_REQ_DATE,BKR_RNW_DATE,BKR_SUPL_SERV_TEXT,LAST_UPD_DATE,LAST_UPD_USER_ID";

  protected String C_BKR_ADDR_TEXT = "BKR_ADDR_TEXT";

  protected String C_BKR_APPRV_CR_LIM_DLR_AMT = "BKR_APPRV_CR_LIM_DLR_AMT";

  protected String C_BKR_APPRV_CR_LIM_LCY_AMT = "BKR_APPRV_CR_LIM_LCY_AMT";

  protected String C_BKR_APPRV_DATE = "BKR_APPRV_DATE";

  protected String C_BKR_APPRV_STAT_CODE = "BKR_APPRV_STAT_CODE";

  protected String C_BKR_AUTH_PROC_SIT_TEXT = "BKR_AUTH_PROC_SIT_TEXT";

  protected String C_BKR_BMF_MKT_CODE = "BKR_BMF_MKT_CODE";

  protected String C_BKR_BOVESPA_MKT_CODE = "BKR_BOVESPA_MKT_CODE";

  protected String C_BKR_CNPJ_NBR = "BKR_CNPJ_NBR";

  protected String C_BKR_COMMENT_TEXT = "BKR_COMMENT_TEXT";

  protected String C_BKR_NAME_TEXT = "BKR_NAME_TEXT";

  protected String C_BKR_RBT_BMF_RATE = "BKR_RBT_BMF_RATE";

  protected String C_BKR_RBT_BOVESPA_RATE = "BKR_RBT_BOVESPA_RATE";

  protected String C_BKR_REQ_CR_LIM_DLR_AMT = "BKR_REQ_CR_LIM_DLR_AMT";

  protected String C_BKR_REQ_CR_LIM_LCY_AMT = "BKR_REQ_CR_LIM_LCY_AMT";

  protected String C_BKR_REQ_DATE = "BKR_REQ_DATE";

  protected String C_BKR_RNW_DATE = "BKR_RNW_DATE";

  protected String C_BKR_SUPL_SERV_TEXT = "BKR_SUPL_SERV_TEXT";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_TABLE_NAME = "TPL_BROKER";

}