package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.bg.dao.BaseTbgPointAcctInvstDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * *
 * @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
 * @version 1.0
 * @author michele.monteiro,19/06/2007
 *  
 */

public class BaseOracleTbgPointAcctInvstDAO extends BaseOracleDAO
    implements BaseTbgPointAcctInvstDAO
{

  //Número da entidade
  protected String C_INVST_ACCT_BUS_NBR = "INVST_ACCT_BUS_NBR";

  //Número da conta investimento
  protected String C_INVST_ACCT_BRCH_NBR = "INVST_ACCT_BRCH_NBR";

  //Número da filial
  protected String C_ACCT_BRCH_NBR = "ACCT_BRCH_NBR";

  //Número da entidade
  protected String C_ACCT_BUS_NBR = "ACCT_BUS_NBR";

  //Número da conta corrente
  protected String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  //Número da conta investimento
  protected String C_INVST_CUR_ACCT_NBR = "INVST_CUR_ACCT_NBR";

  //Código de origem do movimento
  protected String C_CUST_MOV_ORIG_CODE = "CUST_MOV_ORIG_CODE";

}