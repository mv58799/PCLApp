package com.citibank.ods.common.functionality;

import org.apache.struts.action.ActionForm;

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

public interface ODSDetailFnc
{

  public void insert(BaseFncVO fncVO_);
  public void update(BaseFncVO fncVO_);
  public void delete(BaseFncVO fncVO_);
  
  public void validateInsert(BaseFncVO fncVO_);
  public void validateUpdate(BaseFncVO fncVO_);
  public void validateDelete(BaseFncVO fncVO_);

  public void loadForConsult(BaseFncVO fncVO_);
  public void loadForInsert(BaseFncVO fncVO_);
  public void loadForUpdate(BaseFncVO fncVO_);
  public void loadForDelete(BaseFncVO fncVO_);
  
}
