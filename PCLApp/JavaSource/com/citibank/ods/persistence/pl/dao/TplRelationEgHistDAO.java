package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplRelationEgHistEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplRelationEgHist,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public interface TplRelationEgHistDAO extends BaseTplRelationEgDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplRelationEgHistEntity insert(
                                        TplRelationEgHistEntity tplRelationEgHistEntity_ )
                                                                                          throws UnexpectedException;
}