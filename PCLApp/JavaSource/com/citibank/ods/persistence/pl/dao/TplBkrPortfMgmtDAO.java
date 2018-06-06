package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity;

/**
 * @author Hamilton Matos
 *  
 */

public interface TplBkrPortfMgmtDAO extends BaseTplBkrPortfMgmtDAO
{

  /**
   * Métodos Abstratos
   *  
   */
  public TplBkrPortfMgmtEntity insert(
                                      TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                                    throws UnexpectedException;

  public void update( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                    throws UnexpectedException;

  public void delete( BigInteger entityKey_ ) throws UnexpectedException;

  public DataSet listPortfolio( BigInteger custNbr_ )
                                                     throws UnexpectedException;

  public boolean existsActive( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                             throws UnexpectedException;

  public boolean exists( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ );

  public ArrayList listForBkrPorftMgmtGrid( BigInteger prodAcctCode_,
                                           BigInteger prodUnderAcctCode_ );

  public DataSet list( BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_ )
                                                                                throws UnexpectedException;

  public void delete( TplBkrPortfMgmtEntity tplBkrPorftMgmtEntity_ )
                                                                    throws UnexpectedException;

  public boolean existsInactive( TplBkrPortfMgmtEntity entity_ );
}