package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductCorpEntityVO;

public class BaseTplProductCorpEntity  extends BaseODSEntity{
	
	public static final int C_PROD_CODE_SIZE = 10;
	public static final int C_SYS_CODE_SIZE = 3;
	public static final int C_SYS_SEG_CODE_SIZE = 2;
	                              
	//INICIO - "FRD - PCL- RDIP Fase 3_V04.doc" (novos campos para a tela produto)
	                              
	public static final int C_PROD_EVN_CONTRB_DATE_SIZE = 12;
	public static final int C_PROD_FUND_PRFL_TYP_SIZE = 1;
	public static final int C_PROD_CNPJ_NBR_SIZE = 14;
	public static final int C_PROD_CLOSE_TYP_CODE_SIZE = 1;
	public static final int C_PROD_OPEN_TYP_CODE_SIZE = 1;
	public static final int C_PROD_ORIG_NAME_SIZE = 50;
	public static final int C_PROD_OPEN_EVN_DATE_SIZE = 12;
	public static final int C_PROD_ADMIN_RATE_SIZE = 6;
	public static final int C_PROD_PERFM_RATE_SIZE = 7;
	public static final int C_PROD_PRTFINV_APPL_RATE_SIZE = 6;
	public static final int C_PROD_EXIT_RATE_SIZE = 9;
	public static final int C_PROD_QUOT_TYPE_CODE_SIZE = 1;
	public static final int C_PROD_CLOSE_TIME_SIZE = 12;
	public static final int C_PROD_DEP_QUOT_DATE_TYPE_SIZE = 5;
	public static final int C_PROD_WTHDR_CR_DATE_TYPE_SIZE = 5;
	public static final int C_PROD_MIN_STA_APPL_AMT_SIZE = 11;
	public static final int C_PROD_MOV_MIN_AMT_SIZE = 18;
	public static final int C_PROD_MIN_WTHDR_AMT_SIZE = 11;
	public static final int C_PROD_HOLD_MIN_AMT_SIZE = 18;
	public static final int C_PROD_GRACE_IND_SIZE = 1;
	public static final int C_PROD_BAL_CLOSE_DATE_SIZE = 12;
	public static final int C_PROD_CVM_DIST_CODE_SIZE = 1;
	public static final int C_PROD_QUOT_CNDM_CODE_SIZE = 1;
	public static final int C_PROD_RSTRN_CODE_SIZE = 1;
	public static final int C_PROD_GAZETA_DCLR_FORM_CODE_SIZE = 1;
	public static final int C_ANBID_FUND_CLASS_CODE_SIZE = 3;
	public static final int C_PROD_ANBID_DCLR_CODE_SIZE = 1;
	public static final int C_PROD_CVM_TAX_CODE_SIZE = 1;
	public static final int C_PROD_TERM_LONG_IND_SIZE = 1;
	public static final int C_PROD_JRNL_DCLR_IND_SIZE = 1;
	public static final int C_PROD_TAX_TYPE_SIZE = 1;
	public static final int C_PROD_IRF_EXMP_IND_SIZE = 1;
	public static final int C_PROD_IOF_EXMP_IND_SIZE = 1;
	public static final int C_PROD_GRACE_TERM_SIZE = 4;
	public static final int C_PROD_PERFM_PAY_DATE_SIZE = 7;
	public static final int C_PROD_BNCH_IX_TEXT_SIZE = 12;
	public static final int C_PROD_QUOT_INIT_AMT_SIZE = 18;
	public static final int C_PROD_MARK_TYPE_CODE_SIZE = 1;
                                  
	//FIM - "FRD - PCL- RDIP Fase 3_V04.doc" (novos campos para a tela produto)
	                              
	public static final int C_REC_STAT_CODE_SIZE = 1;
	public static final int C_LAST_AUTH_DATE_SIZE = 12;
	public static final int C_LAST_AUTH_USER_ID_SIZE = 20;
	public static final int C_LAST_UPD_DATE_SIZE = 12;
	public static final int C_LAST_UPD_USER_ID_SIZE = 20;
	
	protected BaseTplProductCorpEntityVO m_data;

	public BaseTplProductCorpEntityVO getData(){
	    return m_data;
	}	

}
