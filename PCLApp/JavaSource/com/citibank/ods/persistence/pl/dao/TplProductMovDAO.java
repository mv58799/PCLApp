package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProductMovEntity;

/**
 * Esta interface declara os método abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplProductMov,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public interface TplProductMovDAO extends BaseTplProductDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplProductMovEntity insert( TplProductMovEntity tplProductMovEntity_ );

  public TplProductMovEntity update( TplProductMovEntity tplProductMovEntity_ );

  public TplProductMovEntity delete( TplProductMovEntity tplProductMovEntity_ );

  public DataSet list( String prodCode_, BigInteger prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
                      String lastUpdUserId_ );
  
  public boolean exists( TplProductMovEntity tplProductMovEntity_ );

}