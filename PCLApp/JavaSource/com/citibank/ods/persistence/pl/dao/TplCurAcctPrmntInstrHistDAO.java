package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * *
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author michele.monteiro,15/06/2007
 *  
 */

public interface TplCurAcctPrmntInstrHistDAO extends
    BaseTplCurAcctPrmntInstrDAO
{

  public DataSet list( BigInteger curAcctNbr_, BigInteger custNbr_,
                      BigInteger prmntInstrCode_,
                      String prmntInstrInvstCurAcctInd_,
                      Date curAcctPrmntInstrRefDate_, String custFullName_ );

  public TplCurAcctPrmntInstrHistEntity insert(
                                               TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity_ );

  public ArrayList selectByPK( BigInteger custNbr_, BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_, Date refDate_ );

}