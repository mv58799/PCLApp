package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 *  
 */

public interface TplIpDocCallbackMovDAO extends BaseTplIpDocCallbackDAO
{

  public void update( TplIpDocCallbackMovEntity tplIpDocCallbackEntity );

  public void delete( TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity );

  public TplIpDocCallbackMovEntity insert(
                                          TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity );

  public boolean exists( TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity );

  public ArrayList findByPK( BigInteger ipDocCode_, BigInteger custNbr_ );
  
  public ArrayList findContactCustByPK( BigInteger ipDocPrvt_, BigInteger custNbr_);

}