package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;

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

public interface TplCurAcctPrmntInstrDAO extends BaseTplCurAcctPrmntInstrDAO
{

  public TplCurAcctPrmntInstrEntity insert(
                                           TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_ );

  public TplCurAcctPrmntInstrEntity update(
                                           TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_ );


  public boolean exists( TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity_ );

  public void delete( String recStatCode_, BigInteger custNbr_,
                     BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_ );

  public DataSet list( BigInteger curAcctNbr_, BigInteger custNbr_,
                      BigInteger prmntInstrCode_,
                      String prmntInstrInvstCurAcctInd_, String custFullName_ );
                      
  public BaseTplCurAcctPrmntInstrEntity findById(BigInteger prmntCode_ );                      

}