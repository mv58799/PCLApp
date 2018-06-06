package com.citibank.ods.persistence.bg.dao;

import com.citibank.ods.entity.bg.TbgBranchEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.bg.dao; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 18, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface TbgBranchDAO extends BaseTbgBranchDAO 
{
  public boolean exists (TbgBranchEntity tbgBranchEntity_);
}
