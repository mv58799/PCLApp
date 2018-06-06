package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplBrokerHistEntity;

/**
 * Esta interface declara os m�todos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplBrokerHist,
 * separando o comportamento das opera��es independente do modo como. os dados
 * s�o acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 */

public interface TplBrokerHistDAO extends BaseTplBrokerDAO
{

  /**
   * M�todos Abstratos
   *  
   */

  public TplBrokerHistEntity insert( TplBrokerHistEntity tplBrokerHistEntity_ )
                                                                               throws UnexpectedException;

  public void update( TplBrokerHistEntity tplBrokerHistEntity_ )
                                                                throws UnexpectedException;

  public void delete( BigInteger entityKey_ ) throws UnexpectedException;

  public DataSet list( Date bkrRefDate_, Date lastAuthDate_,
                      String lastAuthUserId_, String recStatCode_,
                      String bkrAddrText_, BigInteger bkrApprvCrLimDlrAmt_,
                      BigInteger bkrApprvCrLimLcyAmt_, Date bkrApprvDate_,
                      String bkrApprvStatCode_, String bkrAuthProcSitText_,
                      String bkrBmfMktCode_, String bkrBovespaMktCode_,
                      String bkrCnpjNbr_, String bkrCommentText_,
                      String bkrNameText_, BigInteger bkrRbtBmfRate_,
                      BigInteger bkrRbtBovespaRate_,
                      BigInteger bkrReqCrLimDlrAmt_,
                      BigInteger bkrReqCrLimLcyAmt_, Date bkrReqDate_,
                      Date bkrRnwDate_, String bkrSuplServText_,
                      Date lastUpdDate_, String lastUpdUserId_ );

  public TplBrokerHistEntity find( TplBrokerHistEntity tplBrokerHistEntity_ )
                                                                             throws UnexpectedException;
}