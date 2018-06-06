/*
 * Created on Mar 4, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;

/**
 * @author fabio.luppi.gil
 *  
 */
public interface TplCustomerPrvtCmplDAO extends BaseTplCustomerPrvtCmplDAO
{
  public void update( TplCustomerPrvtCmplEntity customerPrvtCmplEntity_ );

  public void delete( TplCustomerPrvtCmplEntity customerPrvtCmplEntity_ );

  public TplCustomerPrvtCmplEntity insert(
                                          TplCustomerPrvtCmplEntity customerPrvtCmplEntity_ );

  public DataSet list( String emNbr_, BigInteger glbRevenSysOffcrNbr_,
                      BigInteger prvtCustNbr_, BigInteger prvtKeyNbr_,
                      BigInteger custNbr_, BigInteger wealthPotnlCode_,
                      BigInteger OffcrNbr_, BigInteger classCmplcCode_,
                      String recStatCode_, String custFullName_ );

  public boolean existsActive( TplCustomerPrvtCmplEntity customerPrvtCmplEntity_ );

  public boolean search( TplCustomerPrvtCmplEntity customerPrvtCmplEntity_ );

  public boolean existsEmNbr( String emNbr_ );

  public TplCustomerPrvtCmplEntity findEmNbr(
                                             TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ );
}