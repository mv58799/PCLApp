package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductDAO;

/**
 * Implementação Oracle para DAO da tabela TPL_PRODUCT
 * @author leonardo.nakada
 * @date 04/04/2007
 */
public abstract class BaseOracleTplProductDAO extends BaseOracleDAO implements
    BaseTplProductDAO
{
  //private static final String C_TABLE_COLUMNS = "CITI_GRP_TIE_RELTN_PLCY_IND,CITI_GRP_TIE_RSTRN_PLCY_IND,LAST_UPD_DATE,LAST_UPD_USER_ID,PROD_ADMIN_CNPJ_NBR,PROD_ANBID_CODE,PROD_APPRV_DATE,PROD_AUDIT_CNPJ_NBR,PROD_BMF_CODE,PROD_BOVESPA_CODE,PROD_CCY_CODE,PROD_CETIP_CODE,PROD_CODE,PROD_CREATE_DATE,PROD_CR_TYPE_CLASS_CODE,PROD_CSTDY_CNPJ_NBR,PROD_CTL_CNPJ_NBR,PROD_FAML_CODE,PROD_ISO_CODE,PROD_MGMT_CNPJ_NBR,PROD_NAME,PROD_OPERN_STA_DATE,PROD_PROC_SYS_CODE,PROD_PROC_SYS_SEG_CODE,PROD_QLFY_CODE,PROD_INVST_RISK_CODE,PROD_SELIC_CODE,PROD_STAT_CODE,PROD_SUB_FAML_CODE,PROD_TEXT,PRVT_PROD_AGGR_CODE,SYS_CODE,SYS_SEG_CODE";

  protected static final String C_CITI_GRP_TIE_RELTN_PLCY_IND = "CITI_GRP_TIE_RELTN_PLCY_IND";

  protected static final String C_CITI_GRP_TIE_RSTRN_PLCY_IND = "CITI_GRP_TIE_RSTRN_PLCY_IND";

  protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected static final String C_PROD_ADMIN_CNPJ_NBR = "PROD_ADMIN_CNPJ_NBR";

  protected static final String C_PROD_ANBID_CODE = "PROD_ANBID_CODE";

  protected static final String C_PROD_APPRV_DATE = "PROD_APPRV_DATE";

  protected static final String C_PROD_AUDIT_CNPJ_NBR = "PROD_AUDIT_CNPJ_NBR";

  protected static final String C_PROD_BMF_CODE = "PROD_BMF_CODE";

  protected static final String C_PROD_BOVESPA_CODE = "PROD_BOVESPA_CODE";

  protected static final String C_PROD_CCY_CODE = "PROD_CCY_CODE";

  protected static final String C_PROD_CETIP_CODE = "PROD_CETIP_CODE";

  protected static final String C_PROD_CODE = "PROD_CODE";

  protected static final String C_PROD_CREATE_DATE = "PROD_CREATE_DATE";

  protected static final String C_PROD_CR_TYPE_CLASS_CODE = "PROD_CR_TYPE_CLASS_CODE";

  protected static final String C_PROD_CSTDY_CNPJ_NBR = "PROD_CSTDY_CNPJ_NBR";

  protected static final String C_PROD_CTL_CNPJ_NBR = "PROD_CTL_CNPJ_NBR";

  protected static final String C_PROD_FAML_CODE = "PROD_FAML_CODE";

  protected static final String C_PROD_ISO_CODE = "PROD_ISO_CODE";

  protected static final String C_PROD_MGMT_CNPJ_NBR = "PROD_MGMT_CNPJ_NBR";

  protected static final String C_PROD_NAME = "PROD_NAME";

  protected static final String C_PROD_OPERN_STA_DATE = "PROD_OPERN_STA_DATE";

  protected static final String C_PROD_PROC_SYS_CODE = "PROD_PROC_SYS_CODE";

  protected static final String C_PROD_PROC_SYS_SEG_CODE = "PROD_PROC_SYS_SEG_CODE";

  protected static final String C_PROD_QLFY_CODE = "PROD_QLFY_CODE";

  protected static final String C_PROD_INVST_RISK_CODE = "PROD_INVST_RISK_CODE";

  protected static final String C_PROD_SELIC_CODE = "PROD_SELIC_CODE";

  protected static final String C_PROD_STAT_CODE = "PROD_STAT_CODE";

  protected static final String C_PROD_SUB_FAML_CODE = "PROD_SUB_FAML_CODE";

  protected static final String C_PROD_TEXT = "PROD_TEXT";

  protected static final String C_PRVT_PROD_AGGR_CODE = "PRVT_PROD_AGGR_CODE";

  protected static final String C_SYS_CODE = "SYS_CODE";

  protected static final String C_SYS_SEG_CODE = "SYS_SEG_CODE";
  
  protected static final String C_PROD_LEGAL_CLASS_CODE = "PROD_LEGAL_CLASS_CODE";
  
  protected static final String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";
  
  protected static final String C_ASSET_TYPE_CODE = "ASSET_TYPE_CODE";
  
  //Fase 3
  protected static final String C_PROD_ISIN_CODE = "PROD_ISIN_CODE";

  //private static final String C_TABLE_NAME = "TPL_PRODUCT";
  
  protected static final String C_PROD_SENT_IND = "PROD_SENT_IND";
  protected static final String C_PRCLAS_PROD_ASSET_CLASS_CODE = "PRCLAS_PROD_ASSET_CLASS_CODE";
  protected static final String C_PRCLAS_PROD_STYP_CODE = "PRCLAS_PROD_STYP_CODE";
  protected static final String C_PRCLAS_PROD_TYPE_CODE = "PRCLAS_PROD_TYPE_CODE";
  
  
  protected static final String C_FUND_DIST_FORM_TYPE_CODE = "FUND_DIST_FORM_TYPE_CODE";
  protected static final String C_TERM_TEXT = "TERM_TEXT";
  protected static final String C_STRATEGY_START_DATE = "STRATEGY_START_DATE";
  protected static final String C_STRATEGY_CLOSE_DATE = "STRATEGY_CLOSE_DATE";
  protected static final String C_APPLICATION_STAT_CODE = "APPLICATION_STAT_CODE";
  protected static final String C_WTHDR_STAT_CODE= "WTHDR_STAT_CODE";
  protected static final String C_PERFM_RATE_TEXT= "PERFM_RATE_TEXT";
  protected static final String C_CLOSE_DATE= "CLOSE_DATE";
  

}