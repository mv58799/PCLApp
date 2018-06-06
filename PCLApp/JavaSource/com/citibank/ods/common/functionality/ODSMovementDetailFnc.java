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

public interface ODSMovementDetailFnc
{

  public void update(BaseFncVO fncVO_);
  public void approve(BaseFncVO fncVO_);
  public void reprove(BaseFncVO fncVO_);
  
  public void validateUpdate(BaseFncVO fncVO_);
  public void validateApprove(BaseFncVO fncVO_);
  public void validateReprove(BaseFncVO fncVO_);  

  public void loadForUpdate(BaseFncVO fncVO_);
  public void loadForApprove(BaseFncVO fncVO_);
  public void loadForConsult(BaseFncVO fncVO_);
}
