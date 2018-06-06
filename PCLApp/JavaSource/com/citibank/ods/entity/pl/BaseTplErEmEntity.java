package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplErEmEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplErEmEntity extends BaseODSEntity
{
  protected BaseTplErEmEntityVO m_data;

  private TplRoleCustEntity m_roleCustEntity;

  private TplCustomerPrvtEntity m_customerPrvtEntity;
  
  private TplErEntity m_erEntity;

  public static final int C_ER_NBR_SRC_SIZE = 30;

  public static final int C_EM_NBR_SRC_SIZE = 30;

  public static final int C_CUST_NBR_SRC_SIZE = 11;

  public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;

  public static final int C_CUR_ACCT_NBR_SIZE = 15;

  public static final int C_RELTN_NBR_SIZE = 11;

  public BaseTplErEmEntityVO getData()
  {
    return m_data;
  }

  /**
   * @return Returns the roleCustEntity.
   */
  public TplRoleCustEntity getRoleCustEntity()
  {
    return m_roleCustEntity;
  }

  /**
   * @param roleCustEntity_ The roleCustEntity to set.
   */
  public void setRoleCustEntity( TplRoleCustEntity roleCustEntity_ )
  {
    m_roleCustEntity = roleCustEntity_;
  }

  public TplCustomerPrvtEntity getCustomerPrvtEntity()
  {
    return m_customerPrvtEntity;
  }

  public void setCustomerPrvtEntity( TplCustomerPrvtEntity customerPrvtEntity_ )
  {
    m_customerPrvtEntity = customerPrvtEntity_;
  }
  public void setErEntity( TplErEntity erEntity_ )
  {
	m_erEntity = erEntity_;
  }

}