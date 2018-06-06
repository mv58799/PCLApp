package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author michele.monteiro,13/06/2007
 *  
 */

public interface TplCurAcctPrmntInstrMovDAO extends BaseTplCurAcctPrmntInstrDAO
{

  public TplCurAcctPrmntInstrMovEntity insert(
                                              TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity_ );

  public DataSet list( BigInteger curAcctNbr_, BigInteger custNbr_,
                      BigInteger prmntInstrCode_,
                      String prmntInstrInvstCurAcctInd_, String lastUpdUserId_, String custFullName_ );

  public void delete( BigInteger custNbr_, BigInteger prodCode_,
                     BigInteger prodUnderCode_ );
                     
  public void deleteById(BigInteger prmntCode_ );                     

  public boolean existsRelation( BigInteger prmntInstrCode_,
                                BigInteger prodAcctCode_,
                                BigInteger prodUnderAcctCode_ );

  public boolean exists( BigInteger prodAcctCode_,
                        BigInteger prodUnderAcctCode_, BigInteger custNbr_ );
                        
  public BaseTplCurAcctPrmntInstrEntity findById(BigInteger prmntCode_ );                        

}