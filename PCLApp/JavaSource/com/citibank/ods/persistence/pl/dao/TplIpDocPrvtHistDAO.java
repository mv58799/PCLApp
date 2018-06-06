package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplIpDocPrvtHistEntity;

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

public interface TplIpDocPrvtHistDAO extends BaseTplIpDocPrvtDAO
{

  public DataSet list( BigInteger custNbrSrc_, BigInteger ipDocCodeSrc_,
                      String ipDocTextSrc_, String ipInvstCurAcctInd_,
                      Date ipDocRefDateSrc_, String custFullName_);

  public void insert( TplIpDocPrvtHistEntity tplIpDocPrvtHistEntity_ );

}