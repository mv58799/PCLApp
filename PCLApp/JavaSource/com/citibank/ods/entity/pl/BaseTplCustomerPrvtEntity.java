package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplCustomerPrvtEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.entity.pl; 
 *@version 1.0
 *@author l.braga,14/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class BaseTplCustomerPrvtEntity extends BaseEntity
{
  protected BaseTplCustomerPrvtEntityVO m_data;

  public BaseTplCustomerPrvtEntityVO getData()
  {
    return m_data;
  }
  
  public static final int C_CUST_NBR_SIZE = 11;
  public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;
  public static final int C_CUST_CPF_CNPJ_NBR_SIZE = 14;
  public static final int C_CUR_ACCT_NBR_SIZE = 15;
  public static final int C_RELTN_NBR_SIZE = 11;    

}
