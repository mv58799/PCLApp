/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;

/**
 * @author acacio.domingos
 *  
 */
public interface BaseTplPlayerRoleDAO
{

  public BaseTplPlayerRoleEntity find(
                                      BaseTplPlayerRoleEntity baseTplPlayerRoleEntity );

  public ArrayList selectByPk( String plyrCnpjNbr_ );
  
  public DataSet selectByPlyr( String plyrCnpjNbr_ );
}