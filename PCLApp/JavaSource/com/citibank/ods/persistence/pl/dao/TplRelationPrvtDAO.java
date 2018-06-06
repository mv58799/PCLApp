package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplRelationPrvtDAO extends BaseTplRelationPrvtDAO
{
  public DataSet list( BigInteger reltnNbr_, BigInteger reltnCust1Nbr_,
                      String custFullNameText_, String ownerSelected_,
                      BigInteger acctNbr_, BigInteger custCpfCnpjNbr_ );

  public boolean existsActive( TplRelationPrvtEntity tplRelationPrvtEntity_ );
}