package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductCorpDAO;

public abstract class BaseOracleTplProductCorpDAO extends BaseOracleDAO implements BaseTplProductCorpDAO{

	protected static final String C_PROD_CODE = "PROD_CODE";
	protected static final String C_SYS_CODE = "SYS_CODE";
	protected static final String C_SYS_SEG_CODE = "SYS_SEG_CODE";
	                              
	//INICIO - "FRD - PCL- RDIP Fase 3_V04.doc" (novos campos para a tela produto)
	                              
	protected static final String C_PROD_EVN_CONTRB_DATE = "PROD_EVN_CONTRB_DATE";
	protected static final String C_PROD_FUND_PRFL_TYP = "PROD_FUND_PRFL_TYP";
	protected static final String C_PROD_CNPJ_NBR = "PROD_CNPJ_NBR";
	protected static final String C_PROD_CLOSE_TYP_CODE = "PROD_CLOSE_TYP_CODE";
	protected static final String C_PROD_OPEN_TYP_CODE = "PROD_OPEN_TYP_CODE";
	protected static final String C_PROD_ORIG_NAME = "PROD_ORIG_NAME";
	protected static final String C_PROD_OPEN_EVN_DATE = "PROD_OPEN_EVN_DATE";
	protected static final String C_PROD_ADMIN_RATE = "PROD_ADMIN_RATE";
	protected static final String C_PROD_PERFM_RATE = "PROD_PERFM_RATE";
	protected static final String C_PROD_PRTFINV_APPL_RATE = "PROD_PRTFINV_APPL_RATE";
	protected static final String C_PROD_EXIT_RATE = "PROD_EXIT_RATE";
	protected static final String C_PROD_QUOT_TYPE_CODE = "PROD_QUOT_TYPE_CODE";
	protected static final String C_PROD_CLOSE_TIME = "PROD_CLOSE_TIME";
	protected static final String C_PROD_DEP_QUOT_DATE_TYPE = "PROD_DEP_QUOT_DATE_TYPE";
	protected static final String C_PROD_WTHDR_CR_DATE_TYPE = "PROD_WTHDR_CR_DATE_TYPE";
	protected static final String C_PROD_MIN_STA_APPL_AMT = "PROD_MIN_STA_APPL_AMT";
	protected static final String C_PROD_MOV_MIN_AMT = "PROD_MOV_MIN_AMT";
	protected static final String C_PROD_MIN_WTHDR_AMT = "PROD_MIN_WTHDR_AMT";
	protected static final String C_PROD_HOLD_MIN_AMT = "PROD_HOLD_MIN_AMT";
	protected static final String C_PROD_GRACE_IND = "PROD_GRACE_IND";
	protected static final String C_PROD_BAL_CLOSE_DATE = "PROD_BAL_CLOSE_DATE";
	protected static final String C_PROD_CVM_DIST_CODE = "PROD_CVM_DIST_CODE";
	protected static final String C_PROD_QUOT_CNDM_CODE = "PROD_QUOT_CNDM_CODE";
	protected static final String C_PROD_RSTRN_CODE = "PROD_RSTRN_CODE";
	protected static final String C_PROD_GAZETA_DCLR_FORM_CODE = "PROD_GAZETA_DCLR_FORM_CODE";
	protected static final String C_ANBID_FUND_CLASS_CODE = "ANBID_FUND_CLASS_CODE";
	protected static final String C_PROD_ANBID_DCLR_CODE = "PROD_ANBID_DCLR_CODE";
	protected static final String C_PROD_CVM_TAX_CODE = "PROD_CVM_TAX_CODE";
	protected static final String C_PROD_TERM_LONG_IND = "PROD_TERM_LONG_IND";
	protected static final String C_PROD_JRNL_DCLR_IND = "PROD_JRNL_DCLR_IND";
	protected static final String C_PROD_TAX_TYPE = "PROD_TAX_TYPE";
	protected static final String C_PROD_IRF_EXMP_IND = "PROD_IRF_EXMP_IND";
	protected static final String C_PROD_IOF_EXMP_IND = "PROD_IOF_EXMP_IND";
	protected static final String C_PROD_GRACE_TERM = "PROD_GRACE_TERM";
	protected static final String C_PROD_PERFM_PAY_DATE = "PROD_PERFM_PAY_DATE";
	protected static final String C_PROD_BNCH_IX_TEXT = "PROD_BNCH_IX_TEXT";
	protected static final String C_PROD_QUOT_INIT_AMT = "PROD_QUOT_INIT_AMT";
	protected static final String C_PROD_MARK_TYPE_CODE = "PROD_MARK_TYPE_CODE";
	protected static final String C_PROD_APPL_LIQ_DATE_TYPE = "PROD_APPL_LIQ_DATE_TYPE";
	protected static final String C_PROD_WTHDR_LIQ_DATE_TYPE = "PROD_WTHDR_LIQ_DATE_TYPE";
                                  
	//FIM - "FRD - PCL- RDIP Fase 3_V04.doc" (novos campos para a tela produto)
	                              
	protected static final String C_REC_STAT_CODE = "REC_STAT_CODE";
	protected static final String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";
	protected static final String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";
	protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";
	protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
	

	  protected static final String C_FUND_DIST_FORM_TYPE_CODE = "FUND_DIST_FORM_TYPE_CODE";
	  protected static final String C_TERM_TEXT = "TERM_TEXT";
	  protected static final String C_STRATEGY_START_DATE = "STRATEGY_START_DATE";
	  protected static final String C_STRATEGY_CLOSE_DATE = "STRATEGY_CLOSE_DATE";
	  protected static final String C_APPLICATION_STAT_CODE = "APPLICATION_STAT_CODE";
	  protected static final String C_WTHDR_STAT_CODE= "WTHDR_STAT_CODE";
	  protected static final String C_PERFM_RATE_TEXT= "PERFM_RATE_TEXT";
	  protected static final String C_CLOSE_DATE= "CLOSE_DATE";
	  
	
	
	
	
}
