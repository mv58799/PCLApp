package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPortfolioPrvtDAO;

/**
* Implementação Oracle para DAO da tabela TPL_PORTFOLIO_PRVT
* @author l.braga
* @date 28/03/2007
*/
public abstract class BaseOracleTplPortfolioPrvtDAO extends BaseOracleDAO implements BaseTplPortfolioPrvtDAO
{
public static final String C_TABLE_COLUMNS = "PORTF_BRCH_CODE, PORT.PORTF_CODE,  PORT.PORTF_COST_BUS_GRP_CODE,  PORT.PORTF_COST_DIV_CODE,  PORT.PORTF_COST_PRFTY_CTR_CODE,  PORT.PORTF_COST_REGION_CODE,  PORT.PORTF_COST_RESP_OFFCR_CODE,  PORT.PORTF_CSERV_SUPL_CODE,  PORT.PORTF_NAME_TEXT,  PORT.PORTF_NETWK_SUB_GRP_CODE,  PORT.PORTF_NETWK_SUB_NETWK_GRP_CODE,  PORT.PORTF_OFFCR_NBR,  PORT.PORTF_OPERN_TYPE,  PORT.PORTF_REGION_CODE,  PORT.PORTF_SEG_CODE,  PORT.PORTF_SEG_SUB_CODE,  PORT.PORTF_START_DATE,  PORT.PORTF_STAT_CODE,  PORT.PORTF_UNIT_CODE,  PORT.REC_STAT_CODE ";

public static final String C_PORTF_BRCH_CODE = "PORTF_BRCH_CODE";
public static final String C_PORTF_CODE = "PORTF_CODE";
public static final String C_PORTF_COST_BUS_GRP_CODE = "PORTF_COST_BUS_GRP_CODE";
public static final String C_PORTF_COST_DIV_CODE = "PORTF_COST_DIV_CODE";
public static final String C_PORTF_COST_PRFTY_CTR_CODE = "PORTF_COST_PRFTY_CTR_CODE";
public static final String C_PORTF_COST_REGION_CODE = "PORTF_COST_REGION_CODE";
public static final String C_PORTF_COST_RESP_OFFCR_CODE = "PORTF_COST_RESP_OFFCR_CODE";
public static final String C_PORTF_CSERV_SUPL_CODE = "PORTF_CSERV_SUPL_CODE";
public static final String C_PORTF_NAME_TEXT = "PORTF_NAME_TEXT";
public static final String C_PORTF_NETWK_SUB_GRP_CODE = "PORTF_NETWK_SUB_GRP_CODE";
public static final String C_PORTF_NETWK_SUB_NETWK_GRP_CODE = "PORTF_NETWK_SUB_NETWK_GRP_CODE";
public static final String C_PORTF_OFFCR_NBR = "PORTF_OFFCR_NBR";
public static final String C_PORTF_OPERN_TYPE = "PORTF_OPERN_TYPE";
public static final String C_PORTF_REGION_CODE = "PORTF_REGION_CODE";
public static final String C_PORTF_SEG_CODE = "PORTF_SEG_CODE";
public static final String C_PORTF_SEG_SUB_CODE = "PORTF_SEG_SUB_CODE";
public static final String C_PORTF_START_DATE = "PORTF_START_DATE";
public static final String C_PORTF_STAT_CODE = "PORTF_STAT_CODE";
public static final String C_PORTF_UNIT_CODE = "PORTF_UNIT_CODE";
public static final String C_REC_STAT_CODE = "REC_STAT_CODE";
public static final String C_OFFCR_NBR = "OFFCR_NBR";
public static final String C_OFFCR_CAT_CODE = "OFFCR_CAT_CODE";
public static final String C_OFFCR_STAT_CODE = "OFFCR_STAT_CODE";
public static final String C_PORTFOLIO_PRVT = C_PL_SCHEMA + "TPL_PORTFOLIO_PRVT";
public static final String C_OFFICER = C_BG_SCHEMA + "TBG_OFFICER"; 


}
