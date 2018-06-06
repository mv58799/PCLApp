/*
 * Created on 14/11/2008
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocTransFinancDAO;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public abstract class BaseOracleTplIpDocTransFinancDAO extends BaseOracleDAO implements
  BaseTplIpDocTransFinancDAO
{
	protected final String C_CUST_NBR = "CUST_NBR";
	protected final String C_PRMNT_INSTR_CODE = "PRMNT_INSTR_CODE"; 
	protected final String C_PRMNT_INSTR_TRF_DATA_CODE = "PRMNT_INSTR_TRF_DATA_CODE";
	protected final String C_PRMNT_INSTR_TRF_SEQ_NBR = "PRMNT_INSTR_TRF_SEQ_NBR";
	protected final String C_CHNNL_ATTD_TEXT = "CHNNL_ATTD_TEXT";
	protected final String C_TRF_ACCT_TYPE = "TRF_ACCT_TYPE";
	protected final String C_TRF_AMT = "TRF_AMT";
	protected final String C_TRF_DATE = "TRF_DATE";
}
