package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;

/**
 * Esta interface declara os m�todos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaBaseTplBroker,
 * separando o comportamento das opera��es independente do modo como. os dados
 * s�o acessados(Oracle, SQL, XML, etc).
 * @author Hamilton Matos
 * @date 13/07/2007
 */

public interface BaseTplBrokerDAO
{

  /**
   * M�todos Abstratos
   *  
   */

  public BaseTplBrokerEntity find( BaseTplBrokerEntity baseTplBrokerEntity_ )
                                                                             throws UnexpectedException;
}