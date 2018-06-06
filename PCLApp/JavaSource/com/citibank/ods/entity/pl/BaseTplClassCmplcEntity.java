package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplClassCmplcEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 13, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplClassCmplcEntity extends BaseODSEntity
{

  public static final int C_CLASS_CMPLC_CODE_SIZE = 6;

  public static final int C_CLASS_CMPLC_TEXT_SIZE = 40;

  public static final int C_CLASS_CMPLC_CODE_SRC_SIZE = 6;

  public static final int C_CLASS_CMPLC_TEXT_SRC_SIZE = 40;

  public static final int C_SENS_IND_SIZE = 1;

  protected BaseTplClassCmplcEntityVO m_data;

  public BaseTplClassCmplcEntity()
  {
    m_data = new BaseTplClassCmplcEntityVO();
  }

  public BaseTplClassCmplcEntityVO getData()
  {
    return m_data;
  }

}