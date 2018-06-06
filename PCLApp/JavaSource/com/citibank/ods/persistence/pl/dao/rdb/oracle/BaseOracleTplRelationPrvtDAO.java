package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplRelationPrvtDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.pl.dao.rdb.oracle; 
 *@version 1.0
 *@author gerson.a.rodrigues,Mar 29, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public abstract class BaseOracleTplRelationPrvtDAO extends BaseOracleDAO implements BaseTplRelationPrvtDAO
{
  
  protected String C_RELTN_NBR                      = "RELTN_NBR";  
  protected String C_RELTN_CLASS_CODE               = "RELTN_CLASS_CODE";
  protected String C_RELTN_TYPE_CODE                = "RELTN_TYPE_CODE";
  protected String C_RELTN_CAT_CODE                 = "RELTN_CAT_CODE";
  protected String C_RELTN_SEG_CODE                 = "RELTN_SEG_CODE";
  protected String C_RELTN_CUST_ADDR_NBR            = "RELTN_CUST_ADDR_NBR";
  protected String C_RELTN_CUST_ADDR_SEQ_NBR        = "RELTN_CUST_ADDR_SEQ_NBR";
  protected String C_RELTN_ESTAB_DATE               = "RELTN_ESTAB_DATE";
  protected String C_RELTN_PRMT_CODE                = "RELTN_PRMT_CODE";
  protected String C_RELTN_PORTF_CODE               = "RELTN_PORTF_CODE";
  protected String C_RELTN_STMT_OPTN_CODE           = "RELTN_STMT_OPTN_CODE";
  protected String C_RELTN_CLASS_SCORE_CODE         = "RELTN_CLASS_SCORE_CODE";
  protected String C_RELTN_MF_STMT_IND              = "RELTN_MF_STMT_IND";
  protected String C_RELTN_SAV_STMT_IND             = "RELTN_SAV_STMT_IND";
  protected String C_RELTN_ACCT_STMT_IND            = "RELTN_ACCT_STMT_IND";
  protected String C_RELTN_TD_STMT_IND              = "RELTN_TD_STMT_IND";
  protected String C_RELTN_CORP_BASE_NBR            = "RELTN_CORP_BASE_NBR";
  protected String C_RELTN_SPCF_CLASS_SERV_PACK_IND = "RELTN_SPCF_CLASS_SERV_PACK_IND";
  protected String C_RELTN_RISK_LEVEL_CODE          = "RELTN_RISK_LEVEL_CODE";
  protected String C_RELTN_ADDR_EMAIL_CUST_NBR      = "RELTN_ADDR_EMAIL_CUST_NBR";
  protected String C_RELTN_ADDR_EMAIL_SEQ_NBR       = "RELTN_ADDR_EMAIL_SEQ_NBR";
  protected String C_RELTN_ADDR_CELL_CUST_NBR       = "RELTN_ADDR_CELL_CUST_NBR";
  protected String C_RELTN_ADDR_CELL_SEQ_NBR        = "RELTN_ADDR_CELL_SEQ_NBR";
  protected String C_RELTN_COMM_TYPE_CODE           = "RELTN_COMM_TYPE_CODE";
  protected String C_RELTN_CUST_1_NBR               = "RELTN_CUST_1_NBR";
  protected String C_RELTN_CUST_2_NBR               = "RELTN_CUST_2_NBR";
  protected String C_RELTN_CUST_3_NBR               = "RELTN_CUST_3_NBR";
  protected String C_RELTN_CUST_4_NBR               = "RELTN_CUST_4_NBR";
  protected String C_RELTN_CUST_5_NBR               = "RELTN_CUST_5_NBR";
  protected String C_LAST_UPDATE_OP_ID              = "LAST_UPDATE_OP_ID";
  protected String C_LAST_UPDATE_DATE               = "LAST_UPDATE_DATE";
  protected String C_REC_STAT_CODE                  = "REC_STAT_CODE";

}
