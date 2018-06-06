package com.citibank.ods.persistence.pl.dao;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplPlayerHistEntity;

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

public interface TplPlayerHistDAO extends BaseTplPlayerDAO
{
  public DataSet list(String plyrCnpjNbr_, String plyrName, Date plyrRefDate_);

  public TplPlayerHistEntity insert( TplPlayerHistEntity tplPlayerHistEntity_ );
}