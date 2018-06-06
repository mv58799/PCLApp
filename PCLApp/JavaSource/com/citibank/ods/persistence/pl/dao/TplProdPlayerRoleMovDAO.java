package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdPlayerRoleMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author acacio.domingos,Apr 11, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplProdPlayerRoleMovDAO extends BaseTplProdPlayerRoleDAO
{
  public TplProdPlayerRoleMovEntity insert(
                                           TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity_ );

  public DataSet list( String plyrCnpjNbr_, String plyrName_, String plyrRoleTypeCode_,
                      String prodCode_, String sysCode_, String sysSegCode_,
                      String productName_, String lastUpdUserId );

  public void delete( String plyrCnpjNbr_ );

  public boolean existsRelation( String plyrCnpjNbr_, String plyrRoleTypeCode_ );

  public boolean exists( String plyrCnpjNbr_ );

}