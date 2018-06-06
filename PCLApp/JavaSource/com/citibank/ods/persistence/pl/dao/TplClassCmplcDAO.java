package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplClassCmplcEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.pl.dao; 
 *@version 1.0
 *@author gerson.a.rodrigues,Mar 13, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface TplClassCmplcDAO extends BaseTplClassCmplcDAO
{

  public void update( TplClassCmplcEntity classCmplcEntity_ );

  public void delete( TplClassCmplcEntity classCmplcEntity_ );

  public TplClassCmplcEntity insert( TplClassCmplcEntity classCmplcEntity_ );

  public DataSet list( BigInteger classCmplcCode_, String classCmplcText_, String sensInd_);
  
  public DataSet loadDomain( );
  
  public boolean exists ( TplClassCmplcEntity classCmplcEntity_ );

}
