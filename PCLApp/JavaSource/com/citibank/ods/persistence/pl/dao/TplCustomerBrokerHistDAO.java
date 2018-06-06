package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplCustomerBrokerHistEntity;

/**
 * Esta interface declara os m�todos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplBrokerHist,
 * separando o comportamento das opera��es independente do modo como. os dados
 * s�o acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 */

public interface TplCustomerBrokerHistDAO extends BaseTplCustomerBrokerDAO
{

  /**
   * M�todos Abstratos
   *  
   */

  public TplCustomerBrokerHistEntity insert(
                                            TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                                                      throws UnexpectedException;

  public void update( TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                                throws UnexpectedException;

  public void delete( BigInteger entityKey_ ) throws UnexpectedException;

  public TplCustomerBrokerHistEntity find(
                                          TplCustomerBrokerHistEntity tplCustomerBrokerHistEntity_ )
                                                                                                    throws UnexpectedException;
}