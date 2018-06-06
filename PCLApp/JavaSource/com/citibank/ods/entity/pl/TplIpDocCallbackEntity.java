package com.citibank.ods.entity.pl;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class TplIpDocCallbackEntity extends BaseTplIpDocCallbackEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplIpDocCallbackEntity()
  {
    m_data = new TplIpDocCallbackEntityVO();
  }

  public TplIpDocCallbackEntity(
                                TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity_,
                                Date lastAuthDate_, String lastAuthUserID_,
                                String recStatCode_ )
  {
    m_data = new TplIpDocCallbackEntityVO();
  }

  public TplIpDocCallbackEntity(
                                BaseTplIpDocCallbackEntity tplIpDocCallbackEntity_ )
  {
    m_data = new TplIpDocCallbackEntityVO();

    m_data.setCtcNbr( tplIpDocCallbackEntity_.getData().getCtcNbr() != null
                                                                           ? new BigInteger(
                                                                                             tplIpDocCallbackEntity_.getData().getCtcNbr().toString() )
                                                                           : null );
    m_data.setCustNbr( tplIpDocCallbackEntity_.getData().getCustNbr() != null
                                                                             ? new BigInteger(
                                                                                               tplIpDocCallbackEntity_.getData().getCustNbr().toString() )
                                                                             : null );
    m_data.setIpCallbackCode( tplIpDocCallbackEntity_.getData().getIpCallbackCode() != null
                                                                                           ? new BigInteger(
                                                                                                             tplIpDocCallbackEntity_.getData().getIpCallbackCode().toString() )
                                                                                           : null );
    m_data.setIpDocCode( tplIpDocCallbackEntity_.getData().getIpDocCode() != null
                                                                                 ? new BigInteger(
                                                                                                   tplIpDocCallbackEntity_.getData().getIpDocCode().toString() )
                                                                                 : null );
    m_data.setLastAuthDate( tplIpDocCallbackEntity_.getData().getLastAuthDate() != null
                                                                                       ? tplIpDocCallbackEntity_.getData().getLastAuthDate()
                                                                                       : null );
    m_data.setLastAuthUserId( tplIpDocCallbackEntity_.getData().getLastAuthUserId() != null
                                                                                           ? tplIpDocCallbackEntity_.getData().getLastAuthUserId()
                                                                                           : null );
    m_data.setLastUpdDate( tplIpDocCallbackEntity_.getData().getLastUpdDate() != null
                                                                                     ? tplIpDocCallbackEntity_.getData().getLastUpdDate()
                                                                                     : null );
    m_data.setLastUpdUserId( tplIpDocCallbackEntity_.getData().getLastUpdUserId() != null
                                                                                         ? tplIpDocCallbackEntity_.getData().getLastUpdUserId()
                                                                                         : null );
    m_data.setRecStatCode( tplIpDocCallbackEntity_.getData().getRecStatCode() != null
                                                                                     ? tplIpDocCallbackEntity_.getData().getRecStatCode()
                                                                                     : null );
  }
}

