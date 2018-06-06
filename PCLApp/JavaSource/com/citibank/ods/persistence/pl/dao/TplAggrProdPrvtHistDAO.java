/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplAggrProdPrvtHistEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TplAggrProdPrvtHistDAO extends BaseTplAggrProdPrvtDAO
{
  public DataSet listProductAggregateHistory( String prvtProdAggrCode_,
                                              String prvtProdAggrText_,
                                             Date prvtProdAggrRefDate_ );
  
  public TplAggrProdPrvtHistEntity insert (TplAggrProdPrvtHistEntity aggrProdPrvtHistEntity_);
}