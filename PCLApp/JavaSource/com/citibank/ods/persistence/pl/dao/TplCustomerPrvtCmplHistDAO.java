/*
 * Created on Mar 4, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplHistEntity;

/**
 * @author fabio.luppi.gil
 *
 */
public interface TplCustomerPrvtCmplHistDAO extends BaseTplCustomerPrvtCmplDAO
{
  
  public TplCustomerPrvtCmplHistEntity insert( TplCustomerPrvtCmplHistEntity customerPrvtCmplEntity_ );
  
  public DataSet list( String emNbr_, 
                               String mailRecvInd_,
                               String offclMailRecvInd_,
                               BigInteger glbRevenSysOffcrNbr_,
                               BigInteger prvtCustNbr_,
                               BigInteger prvtPrincCustNbr_,
                               Long custNbr_, 
                               Long wealthPotnlCode_,
                               BigInteger OffcrNbr_, 
                               BigInteger classCmplcCode_,
                               Date custRefDate_,
                               BigInteger prvtCustTypeCode_);  

  public boolean search( TplCustomerPrvtCmplHistEntity customerPrvtCmplHistEntity_ );
}
