package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplErEmMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.pl.dao; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 9, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface TplErEmMovDAO extends BaseTplErEmDAO
{

  public TplErEmMovEntity insert( TplErEmMovEntity tplRelationEgMovEntity_ )
                                                                            throws UnexpectedException;

  public void deleteRelations( String erNbr_ ) throws UnexpectedException;

  public DataSet list( String erNbr_, String emNbr_, BigInteger custNbr_,
                      String custFullName_, BigInteger reltnNbr_,
                      BigInteger curAcctNbr_, String lastUpdUserId_ );

  public ArrayList listByErNbr( String erNbr_ );

  public boolean existsRelation( String erNbr_ );

}