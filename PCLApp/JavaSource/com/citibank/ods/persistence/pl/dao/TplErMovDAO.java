/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplErMovEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public interface TplErMovDAO extends BaseTplErDAO
{

  public TplErMovEntity insert( TplErMovEntity tplRelationEgMovEntity_ )
																			throws UnexpectedException;

  public void delete( TplErMovEntity tplErMovEntity_ );

  public DataSet list( String erNbr_ );

  public ArrayList listByErNbr( String erNbr_ );

  public boolean existsRelation( String erNbr_ );
  
  public boolean existsMovement( TplErMovEntity tplRelationEgMovEntity_ );
  
  public TplErMovEntity update( TplErMovEntity tplErMovEntity_ );  

}