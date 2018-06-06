package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplRelationEgEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplRelationEg,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public interface TplRelationEgDAO extends BaseTplRelationEgDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplRelationEgEntity insert( TplRelationEgEntity tplRelationEgEntity_ )
                                                                               throws UnexpectedException;

  public void deleteRelations( String egNbr_ ) throws UnexpectedException;

  public DataSet list( BigInteger reltnNbr_, String egNbr_, String erNbr_ );

  public ArrayList listByEgNbr( String egNbr_, String erNbr_ );

  public boolean existsRelationActive( String egNbr_, String erNbr_ );

  public boolean existsRelation( String egNbr_, BigInteger reltnNbr_,
                                String erNbr_ );

  public TplRelationEgEntity update( TplRelationEgEntity tplRelationEgEntity_ );
  
  public boolean existsEG( BigInteger reltnNbr_);

}