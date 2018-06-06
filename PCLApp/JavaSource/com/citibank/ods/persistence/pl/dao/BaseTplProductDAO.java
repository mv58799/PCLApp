package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProductEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaBaseTplProduct,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public interface BaseTplProductDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public BaseTplProductEntity find( BaseTplProductEntity BaseTplProductEntity_ )
                                                                                throws UnexpectedException;
}