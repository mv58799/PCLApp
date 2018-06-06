/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplPlayerRoleMovEntity;

/**
 * @author acacio.domingos
 *  
 */
public interface TplPlayerRoleMovDAO extends BaseTplPlayerRoleDAO
{
  public DataSet list( String plyrCnpjNbr_, String plyrName,
                      String lastUpdUserId_ );

  public TplPlayerRoleMovEntity insert(
                                       TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ );

  public TplPlayerRoleMovEntity delete(
                                       TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ );

  public void delete( String plyrCnpjNbr_ );

  public boolean exists( TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ );

}