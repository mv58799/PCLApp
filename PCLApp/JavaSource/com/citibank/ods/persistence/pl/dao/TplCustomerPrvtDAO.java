package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author l.braga,14/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplCustomerPrvtDAO extends BaseTplCustomerPrvtDAO
{
  public DataSet listCustomerPrvt( BigInteger custNbr_,
                                  String custFullNameText_,
	                              String custFullName2Text_, 
	                              String custFullName3Text_, 
						          String custFullName4Text_,
                                  BigInteger custCpfCnpjNbr_,
                                  BigInteger curAcctNbr_, 
	                              String invstCurAcctNbr_, BigInteger reltnNbr_ );

  public DataSet list( BigInteger custNbr_, String custFullNameText_,
                      String custFullName2Text_, String custFullName3Text_, 
                      String custFullName4Text_, BigInteger curAcctNbr_, 
	                  String invstCurAcctNbr_, BigInteger custCpfCnpjNbr_, 
                      BigInteger reltnNbr_, BigInteger prvtCustNbr_,
                      BigInteger prvtPrincCustNbr_, String emNbr_,
                      BigInteger offcrNbr_, BigInteger wealthPotnlCode_,
                      BigInteger classCmplcCode_, String officerName_,
                      String custPrvtStatCode_, BigInteger prvtCustTypeCode_,
                      String orderBy_ );
  
  public DataSet list( BigInteger customerNumber, String customerNameText );
  
  public DataSet findQuestionary( BigInteger customerNumber );

  public boolean exists( TplCustomerPrvtEntity tplCustomerPrvtEntity_ );
}