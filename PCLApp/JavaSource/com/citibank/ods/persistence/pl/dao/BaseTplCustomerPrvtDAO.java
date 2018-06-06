package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.pl.dao; 
 *@version 1.0
 *@author l.braga,14/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface BaseTplCustomerPrvtDAO
{
  public BaseTplCustomerPrvtEntity find( BaseTplCustomerPrvtEntity customerEntity_ );

  public BaseTplCustomerPrvtEntity findByReltn( BaseTplCustomerPrvtEntity customerEntity_ );
}
