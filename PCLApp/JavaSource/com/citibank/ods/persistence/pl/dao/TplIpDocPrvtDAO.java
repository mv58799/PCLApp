package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplIpDocPrvtDAO extends BaseTplIpDocPrvtDAO
{
  public DataSet list( BigInteger custNbrSrc_, BigInteger ipDocCodeSrc_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_, String custFullName_ );

  public TplIpDocPrvtEntity insert( TplIpDocPrvtEntity tplIpDocPrvtEntity_ );

  public void update( TplIpDocPrvtEntity tplIpDocPrvtEntity_ );

  public void delete( TplIpDocPrvtEntity tplIpDocPrvtEntity_ );

  public boolean exists( TplIpDocPrvtEntity tplIpDocPrvtEntity_ );

  public boolean existsActive( TplIpDocPrvtEntity tplIpDocPrvtEntity_ );

  public Integer getNextIpCode();
  
  }