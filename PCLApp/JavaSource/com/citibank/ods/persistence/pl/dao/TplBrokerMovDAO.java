package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplBrokerMovEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplBrokerMov,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 */

public interface TplBrokerMovDAO extends BaseTplBrokerDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplBrokerMovEntity insert( TplBrokerMovEntity tplBrokerMovEntity_ )
                                                                            throws UnexpectedException;

  public TplBrokerMovEntity update( TplBrokerMovEntity tplBrokerMovEntity_ )
                                                                            throws UnexpectedException;

  public TplBrokerMovEntity delete( TplBrokerMovEntity tplBrokerMovEntity_ )
                                                                            throws UnexpectedException;

//  public DataSet list( String opernCode, String bkrAddrText,
//                      BigDecimal bkrApprvCrLimDlrAmt,
//                      BigDecimal bkrApprvCrLimLcyAmt, Date bkrApprvDate,
//                      String bkrApprvStatCode, String bkrAuthProcSitText,
//                      String bkrBmfMktCode, String bkrBovespaMktCode,
//                      String bkrCnpjNbr, String bkrCommentText,
//                      String bkrNameText, BigDecimal bkrRbtBmfRate,
//                      BigDecimal bkrRbtBovespaRate,
//                      BigDecimal bkrReqCrLimDlrAmt,
//                      BigDecimal bkrReqCrLimLcyAmt, Date bkrReqDate,
//                      Date bkrRnwDate, String bkrSuplServText,
//                      Date lastUpdDate, String lastUpdUserId )
//                                                              throws UnexpectedException;

  public boolean exists( TplBrokerMovEntity tplBrokerMovEntity_ );

  public DataSet list( String bkrCnpjNbrSrc_, String bkrNameTextSrc_,
                      String lastUpdUserIdSrc_ );

}