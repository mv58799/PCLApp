package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErEmEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 *  
 */

public interface TplErEmDAO extends BaseTplErEmDAO
{
  public TplErEmEntity insert( TplErEmEntity erEmEntity_ );

  public void deleteRelations( String erNbr_ );

  public TplErEmEntity update( TplErEmEntity tplErEmEntity_ );

  public DataSet list( String erNbr_, String emNbr_, BigInteger custNbr_,
                      String custFullName_, BigInteger reltnNbr,
                      BigInteger curAcctNbr_, boolean includeInactive_);

  public ArrayList listByErNbr( String erNbr_, String emNbr_ );

  public boolean existsRelationActive( String erNbr_, String emNbr_ );

  public boolean existsRelation( String erNbr_, String emNbr_ );
  
  public boolean existsRelationInactive( String erNbr_, String emNbr_ );

  //Combo de ER.
  public DataSet loadErNbr();
  
  public ArrayList listAllByErEM( String erNbr_,String emNbr_ );

}