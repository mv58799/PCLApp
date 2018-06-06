package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackMovEntityVO;

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

public class TplIpDocCallbackMovEntity extends BaseTplIpDocCallbackEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplIpDocCallbackMovEntity()
  {
    m_data = new TplIpDocCallbackMovEntityVO();
  }

  public TplIpDocCallbackMovEntity(
                                   BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity )
  {
    m_data = new TplIpDocCallbackMovEntityVO();
    m_data.setCtcNbr(baseTplIpDocCallbackEntity.getData().getCtcNbr());
    m_data.setCustNbr(baseTplIpDocCallbackEntity.getData().getCustNbr());
    m_data.setIpCallbackCode(baseTplIpDocCallbackEntity.getData().getIpCallbackCode());
    m_data.setIpDocCode(baseTplIpDocCallbackEntity.getData().getIpDocCode());
    m_data.setLastAuthDate(baseTplIpDocCallbackEntity.getData().getLastAuthDate());
    m_data.setLastAuthUserId(baseTplIpDocCallbackEntity.getData().getLastAuthUserId());
    m_data.setLastUpdDate(baseTplIpDocCallbackEntity.getData().getLastUpdDate());
    m_data.setLastUpdUserId(baseTplIpDocCallbackEntity.getData().getLastUpdUserId());
    m_data.setRecStatCode(baseTplIpDocCallbackEntity.getData().getRecStatCode());	
  }

}
