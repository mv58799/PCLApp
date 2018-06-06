package com.citibank.ods.persistence.pl.dao;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdPlayerRoleHistEntity;

/**
 * 
 *@author acacio.domingos,Apr 12, 2007
 *
 */

public interface TplProdPlayerRoleHistDAO extends BaseTplProdPlayerRoleDAO
{
  public DataSet list( String plyrCnpjNbr_, String plyrName, Date plyrRefDate_ );

  public TplProdPlayerRoleHistEntity insert(
                                        TplProdPlayerRoleHistEntity tplProdPlayerRoleHistEntity_ );

}
