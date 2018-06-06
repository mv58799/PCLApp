package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;

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

public interface TplIpDocCallbackDAO extends BaseTplIpDocCallbackDAO
{
  public TplIpDocCallbackEntity insert(
                                       TplIpDocCallbackEntity tplIpDocCallbackEntity );

  public void update( TplIpDocCallbackEntity tplIpDocCallbackEntity );

  public void delete( TplIpDocCallbackEntity tplIpDocCallbackEntity );

  public boolean exists( TplIpDocCallbackEntity tplIpDocCallbackEntity );

  public boolean existsActive( TplIpDocCallbackEntity tplIpDocCallbackEntity );

  public void deleteAll( BigInteger ipDocCode_, BigInteger custNbr_ );

  public ArrayList findByPK( BigInteger ipDocCode_, BigInteger custNbr_ );

  public Integer getNextIpCallBackCode();

}