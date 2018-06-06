package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplCustomerBrokerEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaBaseTplBroker,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 * @date 13/07/2007
 */

public interface BaseTplCustomerBrokerDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public BaseTplCustomerBrokerEntity find( BaseTplCustomerBrokerEntity baseTplCustomerBrokerEntity_ )
                                                                             throws UnexpectedException;
}