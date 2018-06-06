/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public interface TplErDAO extends BaseTplErDAO
{
  public TplErEntity insert( TplErEntity erEntity_ );

  public void deleteRelations( String erNbr_ );

  public TplErEntity update( TplErEntity tplErEntity_ );

  public boolean existsRelationActive( String erNbr_ );

  public boolean existsRelation( String erNbr_, String emNbr_ );

  //Combo de ER.
  public DataSet loadErNbr();

}