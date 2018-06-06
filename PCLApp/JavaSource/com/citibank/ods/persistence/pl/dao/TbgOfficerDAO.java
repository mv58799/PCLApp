package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TbgOfficerEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.pl.dao; 
 *@version 1.0
 *@author gerson.a.rodrigues,Mar 26, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface TbgOfficerDAO extends BaseTbgOfficerDAO
{
  public void update( TbgOfficerEntity officerEntity_ );

  public void delete( TbgOfficerEntity officerEntity_ );

  public TbgOfficerEntity insert( TbgOfficerEntity officerEntity_ );

  public DataSet list( BigInteger offcrNbr_, String offcrNameText_, BigInteger offcrRealNbr_);
  
  public boolean exists ( TbgOfficerEntity officerEntity_ );
  

}
