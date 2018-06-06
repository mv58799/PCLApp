/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.TplPlayerRoleEntity;

/**
 * @author acacio.domingos
 * 
 */
public interface TplPlayerRoleDAO extends BaseTplPlayerRoleDAO
{
  public TplPlayerRoleEntity insert( TplPlayerRoleEntity tplPlayerRoleEntity_ );
  
  public TplPlayerRoleEntity activate( TplPlayerRoleEntity tplPlayerRoleEntity_ );

  public void delete( String plyrCnpjNbr_ );

  public void inactivate( String plyrCnpjNbr_ );
  
  public boolean exists(TplPlayerRoleEntity tplPlayerRoleEntity_);
}