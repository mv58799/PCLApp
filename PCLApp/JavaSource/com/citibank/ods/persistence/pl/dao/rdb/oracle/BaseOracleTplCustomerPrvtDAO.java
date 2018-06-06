package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle.factory;
 * @version 1.0
 * @author l.braga,14/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOracleTplCustomerPrvtDAO extends BaseOracleDAO
    implements BaseTplCustomerPrvtDAO
{
  public static String C_CUST_NBR = "CUST_NBR";
  
  public static String C_CUST_1_NBR = "CUST_1_NBR";
  
  public static String C_CUST_2_NBR = "CUST_2_NBR";
  
  public static String C_CUST_3_NBR = "CUST_3_NBR";
  
  public static String C_CUST_4_NBR = "CUST_4_NBR";
  
  public static final String C_TPL_ER_EM = C_PL_SCHEMA + "TPL_ER_EM";
  
  public static String C_TPL_ER_EM_ALIAS = "ER_EM";
  
  public static String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";
  
  public static String C_CUST_FULL_NAME_2_TEXT = "CUST_FULL_NAME_2_TEXT";
  
  public static String C_CUST_FULL_NAME_3_TEXT = "CUST_FULL_NAME_3_TEXT";
  
  public static String C_CUST_FULL_NAME_4_TEXT = "CUST_FULL_NAME_4_TEXT";
  
  public static String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  public static String C_CUST_CPF_CNPJ_NBR = "CUST_CPF_CNPJ_NBR";

  public static String C_CUST_SHORT_NAME_TEXT = "CUST_SHORT_NAME_TEXT";

  public static String C_CUST_KEY_NAME_TEXT = "CUST_KEY_NAME_TEXT";

  public static String C_CUST_TYPE_CODE = "CUST_TYPE_CODE";

  public static String C_CUST_ACTL_STAT_CODE = "CUST_ACTL_STAT_CODE";

  public static String C_CUST_STAT_DATE = "CUST_STAT_DATE";

  public static String C_CUST_ESTAB_DATE = "CUST_ESTAB_DATE";

  public static String C_CUST_ESTAB_TIME = "CUST_ESTAB_TIME";

  public static String C_CUST_ESTAB_OP_ID = "CUST_ESTAB_OP_ID";

  public static String C_CUST_CNR_LAST_MTH_AMT = "CUST_CNR_LAST_MTH_AMT";

  public static String C_CUST_CNR_YTD_AMT = "CUST_CNR_YTD_AMT";

  public static String C_CUST_CNR_LAST_YR_AMT = "CUST_CNR_LAST_YR_AMT";

  public static String C_CUST_CNR_LAST_SIX_MTH_CODE = "CUST_CNR_LAST_SIX_MTH_CODE";

  public static String C_LAST_UPDATE_DATE = "LAST_UPDATE_DATE";

  public static String C_LAST_UPDATE_TIME = "LAST_UPDATE_TIME";

  public static String C_LAST_UPDATE_OP_ID = "LAST_UPDATE_OP_ID";

  public static String C_APPRV_DATE = "APPRV_DATE";

  public static String C_APPRV_TIME = "APPRV_TIME";

  public static String C_CUST_INPUT_ORIG_CODE = "CUST_INPUT_ORIG_CODE";

  public static String C_CUST_DUP_CODE = "CUST_DUP_CODE";

  public static String C_CUST_CORP_BKR_APPL_PRFL_IND = "CUST_CORP_BKR_APPL_PRFL_IND";

  public static String C_CUST_CORP_FUND_APPL_PRFL_IND = "CUST_CORP_FUND_APPL_PRFL_IND";

  public static String C_CUST_CETIP_NBR = "CUST_CETIP_NBR";

  public static String C_CUST_BMF_NBR = "CUST_BMF_NBR";

  public static String C_CUST_BOVESPA_NBR = "CUST_BOVESPA_NBR";

  public static String C_CUST_BVRJ_NBR = "CUST_BVRJ_NBR";

  public static String C_CUST_SELIC_NBR = "CUST_SELIC_NBR";

  public static String C_CUST_MKT_CAT_CODE = "CUST_MKT_CAT_CODE";

  public static String C_CUST_NO_CPF_IND = "CUST_NO_CPF_IND";

  public static String C_CUST_NAT_ID = "CUST_NAT_ID";

  public static String C_CUST_NAT_ID_APPL_DATE = "CUST_NAT_ID_APPL_DATE";

  public static String C_CUST_NAT_ID_EMIT_CODE = "CUST_NAT_ID_EMIT_CODE";

  public static String C_CUST_NAT_ID_EMIT_NAME = "CUST_NAT_ID_EMIT_NAME";

  public static String C_CUST_NAT_ID_EMIT_STATE_CODE = "CUST_NAT_ID_EMIT_STATE_CODE";

  public static String C_CUST_CIVIL_STAT_CODE = "CUST_CIVIL_STAT_CODE";

  public static String C_CUST_SEX_CODE = "CUST_SEX_CODE";

  public static String C_CUST_BIRTH_DATE = "CUST_BIRTH_DATE";

  public static String C_CUST_BIRTH_CITY_TEXT = "CUST_BIRTH_CITY_TEXT";

  public static String C_CUST_BIRTH_STATE_CODE = "CUST_BIRTH_STATE_CODE";

  public static String C_CUST_BIRTH_CNTRY_CODE = "CUST_BIRTH_CNTRY_CODE";

  public static String C_CUST_PROF_CODE = "CUST_PROF_CODE";

  public static String C_CUST_EMPL_IND = "CUST_EMPL_IND";

  public static String C_CUST_DEPND_NBR = "CUST_DEPND_NBR";

  public static String C_CUST_OCCUP_CODE = "CUST_OCCUP_CODE";

  public static String C_CUST_DEPND_NBR_DATE = "CUST_DEPND_NBR_DATE";

  public static String C_CUST_MGMT_INCO_MIN_SAL_COUNT = "CUST_MGMT_INCO_MIN_SAL_COUNT";

  public static String C_CUST_CHK_DATE = "CUST_CHK_DATE";

  public static String C_CUST_GRCARD_IND = "CUST_GRCARD_IND";

  public static String C_CUST_SOC_SCTY_NBR = "CUST_SOC_SCTY_NBR";

  public static String C_CUST_CPF_OWN_IND = "CUST_CPF_OWN_IND";

  public static String C_CUST_PARENT_LEVEL_IND = "CUST_PARENT_LEVEL_IND";

  public static String C_CUST_USA_CTZN_AUTH_IND = "CUST_USA_CTZN_AUTH_IND";

  public static String C_CUST_USA_CTZN_AUTH_OP_ID = "CUST_USA_CTZN_AUTH_OP_ID";

  public static String C_CUST_INDIV_PUBLIC_IND = "CUST_INDIV_PUBLIC_IND";

  public static String C_CUST_CITI_GRP_TIE_IND = "CUST_CITI_GRP_TIE_IND";

  public static String C_CUST_BIRTH_CNTRY_CO_CODE = "CUST_BIRTH_CNTRY_CO_CODE";

  public static String C_CUST_ACTY_AREA_CODE = "CUST_ACTY_AREA_CODE";

  public static String C_CUST_NO_CGC_IND = "CUST_NO_CGC_IND";

  public static String C_CUST_FNDTN_DATE = "CUST_FNDTN_DATE";

  public static String C_CUST_CO_TYPE_CODE = "CUST_CO_TYPE_CODE";

  public static String C_CUST_GRP_CODE = "CUST_GRP_CODE";

  public static String C_CUST_SUB_GRP_CODE = "CUST_SUB_GRP_CODE";

  public static String C_CUST_INTL_NBR = "CUST_INTL_NBR";

  public static String C_CUST_IRRF_EXEMPT_IND = "CUST_IRRF_EXEMPT_IND";

  public static String C_REC_STAT_CODE = "REC_STAT_CODE";
  
//Informações referentes a dados complementares de cliente

  public static String C_EM_NBR = "EM_NBR";

  public static String C_MAIL_RECV_IND = "MAIL_RECV_IND";

  public static String C_OFFCL_MAIL_RECV_IND = "OFFCL_MAIL_RECV_IND";

  public static String C_PRVT_CUST_NBR = "PRVT_CUST_NBR";

  public static String C_PRVT_KEY_NBR = "PRVT_KEY_NBR";

  public static String C_WEALTH_POTNL_CODE = "WEALTH_POTNL_CODE";

  public static String C_CLASS_CMPLC_CODE = "CLASS_CMPLC_CODE";
  
  public static String C_PRVT_CUST_TYPE_CODE = "PRVT_CUST_TYPE_CODE";

  public static String C_OFFCR_NBR = "OFFCR_NBR";

  public static String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  public static String C_GLB_REVEN_SYS_OFFCR_NBR = "GLB_REVEN_SYS_OFFCR_NBR";

  public static String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  public static String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  public static String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  public static String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  public static String C_CUST_REF_DATE = "CUST_REF_DATE";

}