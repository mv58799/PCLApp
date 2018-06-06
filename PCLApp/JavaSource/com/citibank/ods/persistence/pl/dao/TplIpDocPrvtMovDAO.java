package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;

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

public interface TplIpDocPrvtMovDAO extends BaseTplIpDocPrvtDAO
{
  public DataSet list( BigInteger custNbrSrc_, BigInteger ipDocCodeSrc_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_,
                      String lastUpdUserId_, String custFullName_ );

  public TplIpDocPrvtMovEntity insert(
                                      TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ );

  public void update( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ );

  public void delete( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ );

  public boolean exists( TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity_ );
  
  public Integer getNextIpCode(BigInteger custNbrPk);

}