package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplCustomerBrokerEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplBroker, separando
 * o comportamento das operações independente do modo como. os dados são
 * acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 * @date 13/07/2007
 */

public interface TplCustomerBrokerDAO extends BaseTplCustomerBrokerDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplCustomerBrokerEntity insert(
                                        TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                                          throws UnexpectedException;

  public void update( TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                        throws UnexpectedException;

  public void delete( BigInteger entityKey_ ) throws UnexpectedException;

  public DataSet list( BigInteger custNbr_ ) throws UnexpectedException;

  public boolean existsActive( TplCustomerBrokerEntity tplCustomerBrokerEntity_ )
                                                                                 throws UnexpectedException;

  public boolean exists( TplCustomerBrokerEntity tplCustomerBrokerEntity_ );

  public ArrayList listForCustomerBrokerGrid( String custNbr_ );

}