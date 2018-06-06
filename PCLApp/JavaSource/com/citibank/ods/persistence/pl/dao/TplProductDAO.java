package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProductEntity;

/**
 * Esta interface declara os métodos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplProduct,
 * separando o comportamento das operações independente do modo como. os dados
 * são acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public interface TplProductDAO extends BaseTplProductDAO
{

  /**
   * Métodos Abstratos
   *  
   */

  public TplProductEntity insert( TplProductEntity tplProductEntity_ )
                                                                      throws UnexpectedException;

  public void update( TplProductEntity tplProductEntity_ )
                                                          throws UnexpectedException;

  public void delete( TplProductEntity tplProductEntity_ )
                                                          throws UnexpectedException;

  public DataSet list( String prodCode_, BigInteger prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,String orderBy_ );

  public TplProductEntity find( TplProductEntity TplProductEntity_ )
                                                                    throws UnexpectedException;

  public boolean exists( TplProductEntity TplProductEntity_ )
                                                             throws UnexpectedException;

  public boolean existsActive( TplProductEntity TplProductEntity_ )
                                                                   throws UnexpectedException;

  public boolean existsProductByForeignKey( String prvtProdAggrCode_,
                                           BigInteger prodQlfyCode_,
                                           BigInteger prodRiskCatCode_,
                                           BigInteger prodSubFamlCode_,
                                           BigInteger assetTypeCode_ )
                                                                        throws UnexpectedException;

  public DataSet loadDomain();

  public DataSet loadDomainPMA();

}