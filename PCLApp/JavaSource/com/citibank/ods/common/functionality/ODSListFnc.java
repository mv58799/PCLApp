package com.citibank.ods.common.functionality;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.common.functionality; 
 *@version 1.0
 *@author bruno.zanetti,Mar 9, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface ODSListFnc
{
  public static final int MAXIMUM_REGISTERS = 2000;
  public void list(BaseFncVO fncVO_);
  public void load(BaseFncVO fncVO_);
  
  public void validateList(BaseFncVO fncVO_);
}
