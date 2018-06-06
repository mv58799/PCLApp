package com.citibank.ods.persistence.bg.dao;

import com.citibank.ods.common.dataset.DataSet;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.bg.dao; 
 *@version 1.0
 *@author gerson.a.rodrigues,May 26, 2006
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface BaseTbgCustMailDAO 
{
  public DataSet list (Long custNbr);

}