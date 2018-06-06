package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity;

/**
 * @author Hamilton Matos
 */

public interface TplBkrPortfMgmtMovDAO extends BaseTplBkrPortfMgmtDAO
{

  /**
   * Métodos Abstratos
   *  
   */
  public TplBkrPortfMgmtMovEntity insert(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                             throws UnexpectedException;

  public TplBkrPortfMgmtMovEntity update(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                             throws UnexpectedException;

  public TplBkrPortfMgmtMovEntity delete(
                                         TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                             throws UnexpectedException;

  public TplBkrPortfMgmtMovEntity deleteFromMovement(
                                                     TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
                                                                                                         throws UnexpectedException;

  public boolean exists( TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ );

  public DataSet list( String custNbrSrc_, String custFullNameTextSrc_,
                      String bkrCnpjSrc_, String bkrNameText_,
                      String lastUpdUserIdSrc_ ) throws UnexpectedException;

  public DataSet list( String custNbrSrc_, String custFullNameTextSrc_,
                      BigInteger curAcctNbr_, String custMnmcNameTextSrc_,
                      String portfMgmtProdNameSrc_, String prodCodeSrc_,
                      String lastUpdUserIdSrc_, String loggedUserSrc_ ) throws UnexpectedException;

  public DataSet listPortfolio( BigInteger custNbr_ )
                                                     throws UnexpectedException;

  public DataSet listPortfolio( BigInteger custNbr_, String loggedUser_ )
                                                                         throws UnexpectedException;

  public DataSet listAuthorizedPortfolio( BigInteger custNbr_,
                                         String loggedUser_ )
                                                             throws UnexpectedException;

  public ArrayList listForBkrPorftMgmtGrid( BigInteger prodAcctCode_,
                                           BigInteger prodUnderAcctCode_ );

  public DataSet list( BigInteger prodAcctCode_, BigInteger prodUnderAcctCode_ )
                                                                                throws UnexpectedException;
}