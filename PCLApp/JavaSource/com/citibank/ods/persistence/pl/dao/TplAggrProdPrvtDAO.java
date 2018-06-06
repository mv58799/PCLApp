/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplAggrProdPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplAggrProdPrvtDAO extends BaseTplAggrProdPrvtDAO
{
  public DataSet listProductAggregate( String prvtProdAggrCode_,
                                      String prvtProdAggrText_ );

  public TplAggrProdPrvtEntity insert(
                                      TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ );

  public void update( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ );

  public void delete( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ );

  public boolean exists( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ );

  public boolean existsActive( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ );
  
  public DataSet loadDomain();
  
  public DataSet loadIndexAggrs();
}