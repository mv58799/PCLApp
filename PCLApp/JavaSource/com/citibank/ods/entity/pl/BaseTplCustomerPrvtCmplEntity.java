/*
 * Created on Mar 8, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplCustomerPrvtCmplEntityVO;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTplCustomerPrvtCmplEntity extends BaseODSEntity
{
  protected BaseTplCustomerPrvtCmplEntityVO m_data;
  
  public BaseTplCustomerPrvtCmplEntityVO getData()
  {
    return m_data;
  }
  
  public static final int C_CUST_NBR_SIZE = 11;
  public static final int C_EM_NBR_SIZE = 30;
  public static final int C_MAIL_RECV_IND_SIZE = 1;
  public static final int C_OFFCL_MAIL_RECV_IND_SIZE = 1;
  public static final int C_PRVT_CUST_NBR_SIZE = 8;
  public static final int C_PRVT_KEY_NBR_SIZE = 8;
  public static final int C_WEALTH_POTNL_CODE_SIZE = 11;
  public static final int C_CLASS_CMPLC_CODE_SIZE = 6;
  public static final int C_OFFCR_NBR_SIZE = 6;
  public static final int C_GLB_REVEN_SYS_OFFCR_NBR_SIZE = 6;
  public static final int C_EM_NBR_SRC_SIZE = 30;  
  public static final int C_GLB_REVEN_SYS_OFFCR_NBR_SRC_SIZE = 6;
  public static final int C_PRVT_CUST_NBR_SRC_SIZE = 8;
  public static final int C_PRVT_KEY_NBR_SRC_SIZE = 8;
  public static final int C_WEALTH_POTNL_CODE_SRC_SIZE = 11;
  public static final int C_CUST_NBR_SRC_SIZE = 11;
  public static final int C_OFFCR_NBR_SRC_SIZE = 6;
  public static final int C_CLASS_CMPLC_CODE_SRC_SIZE = 6;  
  public static final int C_OFFCR_TEXT_SRC_SIZE = 40;

}