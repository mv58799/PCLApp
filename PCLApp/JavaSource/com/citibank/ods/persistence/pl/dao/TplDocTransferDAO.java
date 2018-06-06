package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplDocTransferEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TplDocTransferDAO extends BaseTplDocTransferDAO
{

  public TplDocTransferEntity insert( TplDocTransferEntity tplDocTransferEntity );

  public void update( TplDocTransferEntity tplDocTransferEntity );

  public void delete( TplDocTransferEntity tplDocTransferEntity );

  public boolean exists( TplDocTransferEntity tplDocTransferEntity );

  public boolean existsActive( TplDocTransferEntity tplDocTransferEntity );

  public void deleteAll (BigInteger ipDocCode_, BigInteger custNbr_);
  
  public ArrayList findByPK (BigInteger ipDocCode_, BigInteger custNbr_);
  
  public Long getNextDocTransferCode();
}