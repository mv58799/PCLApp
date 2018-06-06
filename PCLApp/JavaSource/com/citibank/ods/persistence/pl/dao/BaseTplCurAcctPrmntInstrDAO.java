package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author michele.monteiro,05/06/2007
 *  
 */

public interface BaseTplCurAcctPrmntInstrDAO
{
  public ArrayList selectByPK( BigInteger custNbr_, BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ );

}