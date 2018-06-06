package com.citibank.ods.persistence.pl.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplBrokerEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplBroker, separando
 * o comportamento das operações independente do modo como. os dados são
 * acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 * @date 13/07/2007
 */

public interface TplBrokerDAO extends BaseTplBrokerDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplBrokerEntity insert( TplBrokerEntity tplBrokerEntity_ )
                                                                   throws UnexpectedException;

  public void update( TplBrokerEntity tplBrokerEntity_ )
                                                        throws UnexpectedException;

  public void delete( BigInteger entityKey_ ) throws UnexpectedException;

//  public DataSet list( Date lastAuthDate_, String lastAuthUserId_,
//                      String opernCode_, String recStatCode_,
//                      String bkrAddrText_, BigDecimal bkrApprvCrLimDlrAmt_,
//                      BigDecimal bkrApprvCrLimLcyAmt_, Date bkrApprvDate_,
//                      String bkrApprvStatCode_, String bkrAuthProcSitText_,
//                      String bkrBmfMktCode_, String bkrBovespaMktCode_,
//                      String bkrCnpjNbr_, String bkrCommentText_,
//                      String bkrNameText_, BigDecimal bkrRbtBmfRate_,
//                      BigDecimal bkrRbtBovespaRate_,
//                      BigDecimal bkrReqCrLimDlrAmt_,
//                      BigDecimal bkrReqCrLimLcyAmt_, Date bkrReqDate_,
//                      Date bkrRnwDate_, String bkrSuplServText_,
//                      Date lastUpdDate_, String lastUpdUserId_ )
//                                                                throws UnexpectedException;

  public DataSet list( String bkrCnpjNbr_, String bkrNameText_ )
                                                                throws UnexpectedException;

  public boolean existsActive( TplBrokerEntity tplBrokerEntity_ )
                                                                 throws UnexpectedException;

  public boolean exists( TplBrokerEntity tplBrokerEntity_ );

  public ArrayList listForBrokerGrid( String bkrCnpjNbr_, String bkrNameText_ );
}