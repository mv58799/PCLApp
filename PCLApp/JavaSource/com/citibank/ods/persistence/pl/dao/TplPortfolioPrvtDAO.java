package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author l.braga,28/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplPortfolioPrvtDAO extends BaseTplPortfolioPrvtDAO
{
  public DataSet listPortfolio( String portfCode_, String portfNameText_,
                               BigInteger portfOffcrNbr_, String offcrNameText_ );
}