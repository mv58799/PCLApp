package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseOracleTplErEmDAO extends BaseOracleDAO
{
  public String C_ER_NBR = "ER_NBR";

  public String C_EM_NBR = "EM_NBR";

  public String C_ROLE_CUST_CODE = "ROLE_CUST_CODE";
  
  public String C_ROLE_CUST_TEXT = "ROLE_CUST_TEXT";

  public String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  public String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";
  
  /*
   * Tabelas auxiliares 
   */
  public static final String C_TPL_CUSTOMER_PRVT_CMPL = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT_CMPL";
  
  public static final String C_TPL_CUSTOMER_PRVT = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT";
  
  public static final String C_TPL_RELATION_PRVT = C_PL_SCHEMA + "TPL_RELATION_PRVT";
  
  public static final String C_TPL_ROLE_CUST = C_PL_SCHEMA + "TPL_ROLE_CUST";
  
  public static final String C_TO3_PRODUCT_ACCOUNT = C_O3_SCHEMA + "TO3_PRODUCT_ACCOUNT";
  
  /*
   * Colunas auxiliares
   * @author leonardo.nakada
   *
   */
  public String C_CUST_NBR = "CUST_NBR";
  
  public String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  public String C_RELTN_CUST_1_NBR = "RELTN_CUST_1_NBR";

  public String C_RELTN_NBR = "RELTN_NBR";
  
  public String C_SYS_CODE = "SYS_CODE";
  
  public String C_SYS_SEG_CODE = "SYS_SEG_CODE";
  
  public String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";
  
  public String C_PROD_CODE = "PROD_CODE";
  
  /*
   * Estes são os valores das constantes a serem utilizadas
   * pela pesquisa, para filtrar as Contas Correntes corretas para
   * serem utilizadas neste módulo
   */
  protected static final String C_SYS_CODE_VALUE = "DA"; // Conta Corrente
  
  protected static final int C_SYS_SEG_CODE_VALUE = 1; // Consumer
  
  protected static final String C_PROD_CODE_VALUE = "010"; // Código do Produto Conta corrente
  
}