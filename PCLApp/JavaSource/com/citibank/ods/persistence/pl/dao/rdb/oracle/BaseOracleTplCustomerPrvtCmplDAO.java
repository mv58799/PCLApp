/*
 * Created on Mar 9, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseOracleTplCustomerPrvtCmplDAO extends BaseOracleDAO implements
    BaseTplCustomerPrvtCmplDAO
{
  protected String C_CUST_NBR = "CUST_NBR";

  protected String C_EM_NBR = "EM_NBR";

  public static final String C_TPL_ER_EM = C_PL_SCHEMA + "TPL_ER_EM";
  
  public static String C_TPL_ER_EM_ALIAS = "ER_EM";
  

  
  public static final String C_ROLE_CUST_CODE = "ROLE_CUST_CODE";

  protected String C_MAIL_RECV_IND = "MAIL_RECV_IND";

  protected String C_OFFCL_MAIL_RECV_IND = "OFFCL_MAIL_RECV_IND";

  protected String C_PRVT_CUST_NBR = "PRVT_CUST_NBR";
  
  protected String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  protected String C_PRVT_KEY_NBR = "PRVT_KEY_NBR";

  protected String C_WEALTH_POTNL_CODE = "WEALTH_POTNL_CODE";

  protected String C_CLASS_CMPLC_CODE = "CLASS_CMPLC_CODE";
  
  protected String C_PRVT_CUST_TYPE_CODE = "PRVT_CUST_TYPE_CODE";

  protected String C_OFFCR_NBR = "OFFCR_NBR";
  
  protected String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  protected String C_GLB_REVEN_SYS_OFFCR_NBR = "GLB_REVEN_SYS_OFFCR_NBR";

  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  protected String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  protected String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";
  
  protected String C_CUST_REF_DATE = "CUST_REF_DATE";
  
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";
}