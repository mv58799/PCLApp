package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplRelationPrvtEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplRelationPrvtEntity extends BaseODSEntity
{
  protected BaseTplRelationPrvtEntityVO m_data;

  public static String C_RELTN_PRMT_CODE_1 = "C_RELTN_PRMT_CODE_1";

  public static String C_RELTN_PRMT_CODE_2 = "C_RELTN_PRMT_CODE_2";

  public static String C_RELTN_PRMT_CODE_3 = "C_RELTN_PRMT_CODE_3";

  public static String C_RELTN_PRMT_CODE_4 = "C_RELTN_PRMT_CODE_4";

  public static String C_RELTN_PRMT_CODE_5 = "C_RELTN_PRMT_CODE_5";

  public static String C_RELTN_PRMT_CODE_6 = "C_RELTN_PRMT_CODE_6";

  public static String C_RELTN_PRMT_CODE_7 = "C_RELTN_PRMT_CODE_7";

  public static final int C_CUST_NBR_SRC_SIZE = 11;

  public static final int C_ACCT_NBR_SRC_SIZE = 17;

  public static final int C_RELTN_NBR_SRC_SIZE = 11;

  public static final int C_CUST_CPF_CNPJ_NBR_SRC_SIZE = 14;

  public static final int C_OWNER_SELECTED_SRC_SIZE = 1;
  
  public static final int C_CUST_FULL_NAME_TEXT_SRC_SIZE = 60;

  public BaseTplRelationPrvtEntityVO getData()
  {
    return m_data;
  }

}