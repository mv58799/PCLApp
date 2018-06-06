package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTbgOfficerEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.entity.pl; 
 *@version 1.0
 *@author gerson.a.rodrigues,Mar 26, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class BaseTbgOfficerEntity extends BaseODSEntity
{

  protected BaseTbgOfficerEntityVO m_data;
  
  public static final String C_OFFCR_CAT_CODE_INTERNAL = "Interna";
  public static final String C_OFFCR_CAT_CODE_EXTERNAL = "Externa";
  public static final String C_OFFCR_CAT_CODE_TELEMARK = "Telemark";
  public static final String C_OFFCR_STAT_CODE_INCLUDED = "Incluído";
  public static final String C_OFFCR_STAT_CODE_ACTIVE = "Ativo";  
  
  public static final int C_OFFCR_NBR_SRC_SIZE = 6;
  public static final int C_OFFCR_NAME_TEXT_SRC_SIZE = 40;
  public static final int C_OFFCR_REAL_NBR_SRC_SIZE = 6;
  
  public static final String C_OFFCR_NAME_TEXT = "OFFCR_NAME_TEXT";

  public BaseTbgOfficerEntityVO getData()
  {
    return m_data;
  }
}
