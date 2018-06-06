/*
 * Created on Mar 4, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;

/**
 * @author fabio.luppi.gil
 *  
 */
public interface TplCustomerPrvtCmplMovDAO extends BaseTplCustomerPrvtCmplDAO
{

  public void update( TplCustomerPrvtCmplMovEntity customerPrvtCmplEntity_ );

  public void delete( TplCustomerPrvtCmplMovEntity customerPrvtCmplEntity_ );

  public TplCustomerPrvtCmplMovEntity insert(
                                             TplCustomerPrvtCmplMovEntity customerPrvtCmplMovEntity_ );

  public boolean exists( TplCustomerPrvtCmplMovEntity customerPrvtCmplMovEntity_ );

  public DataSet list( String emNbr_, BigInteger glbRevenSysOffcrNbr_,
                      BigInteger prvtCustNbr_, BigInteger prvtPrincCustNbr_,
                      BigInteger custNbr_, BigInteger wealthPotnlCode_,
                      BigInteger OffcrNbr_, BigInteger classCmplcCode_,
                      String lastUpdUserId_, String custFullName_,
                      String officerName_, String custPrvtStatCode_, 
                      BigInteger prvtCustTypeCode_ );

  public boolean search( TplCustomerPrvtCmplMovEntity customerPrvtCmplMovEntity_ );

}