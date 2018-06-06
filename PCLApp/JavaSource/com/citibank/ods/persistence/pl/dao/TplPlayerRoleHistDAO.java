/*
 * Created on Apr 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplPlayerRoleHistEntity;

/**
 * @author acacio.domingos
 *  
 */
public interface TplPlayerRoleHistDAO extends BaseTplPlayerRoleDAO
{
  public DataSet list( String plyrCnpjNbr_, String plyrName, Date plyrRefDate_ );

  public TplPlayerRoleHistEntity insert(
                                        TplPlayerRoleHistEntity tplPlayerRoleHistEntity_ );
}