package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;

/**
 * Esta interface declara os m�todos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplRelationEgMov,
 * separando o comportamento das opera��es independente do modo como. os dados
 * s�o acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public interface TplRelationEgMovDAO extends BaseTplRelationEgDAO
{

  /**
   * M�todos Abstratos
   *  
   */

  public TplRelationEgMovEntity insert(
                                       TplRelationEgMovEntity tplRelationEgMovEntity_ )
                                                                                       throws UnexpectedException;

  public void deleteRelations( String erNbr, String egNbr, BigInteger reltnNbr ) throws UnexpectedException;

  public DataSet list( String egNbr_, String lastUpdUserId_,
                      BigInteger reltnNbr, String erNbr_ );

  public ArrayList listByEgNbr( String egNbr_, String erNbr_ );

  public boolean existsRelation( String egNbr_, String erNbr_ );
  
  public void deleteRelationsByEgNr( String erNbr_, String egNbr_);

}