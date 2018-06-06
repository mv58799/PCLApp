/*
 * Created on Apr 3, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplPlayerEntity;

/**
 * @author acacio.domingos
 *  
 */
public interface TplPlayerDAO extends BaseTplPlayerDAO
{

  public DataSet list( String plyrCnpjNbr_, String plyrName,
                      String plyrRoleTypeCode_ );

  public TplPlayerEntity insert( TplPlayerEntity tplPlayerEntity_ );

  public void update( TplPlayerEntity tplPlayerEntity_ );

  public void delete( TplPlayerEntity tplPlayerEntity_ );

  public boolean exists( TplPlayerEntity tplPlayerEntity_ );

  public boolean existsActive( TplPlayerEntity tplPlayerEntity_ );
  
  public DataSet listEmissor (); 

}