package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplDocTransferMovEntity;

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

public interface TplDocTransferMovDAO extends BaseTplDocTransferDAO
{

  public void update( TplDocTransferMovEntity tplDocTransferMovEntity );

  public void delete( TplDocTransferMovEntity tplDocTransferMovEntity );

  public TplDocTransferMovEntity insert(
                                        TplDocTransferMovEntity tplDocTransferMovEntity );

  public boolean exists( TplDocTransferMovEntity tplDocTransferMovEntity );
  
  public ArrayList findByPK (BigInteger ipDocCode_, BigInteger custNbr_);
  
  
}