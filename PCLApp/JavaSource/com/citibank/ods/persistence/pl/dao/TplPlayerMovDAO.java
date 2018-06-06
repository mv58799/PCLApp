package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplPlayerMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author angelica.almeida,03/04/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplPlayerMovDAO extends BaseTplPlayerDAO
{
  public DataSet list( String plyrCnpjNbr_, String plyrName, String plyrRoleTypeCode_,
                      String lastUpdUserId_ );

  public TplPlayerMovEntity insert( TplPlayerMovEntity tplPlayerMovEntity_ );
  
  public TplPlayerMovEntity update( TplPlayerMovEntity tplPlayerMovEntity_ );

  public TplPlayerMovEntity delete( TplPlayerMovEntity tplPlayerMovEntity_ );

  public boolean exists( TplPlayerMovEntity tplPlayerMovEntity_ );

}