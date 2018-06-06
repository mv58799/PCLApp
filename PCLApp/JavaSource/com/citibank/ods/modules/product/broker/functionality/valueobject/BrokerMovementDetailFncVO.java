package com.citibank.ods.modules.product.broker.functionality.valueobject;

import com.citibank.ods.entity.pl.TplBrokerMovEntity;

/**
 * @author Hamilton Matos
 *  
 */

public class BrokerMovementDetailFncVO extends BaseBrokerDetailFncVO
{

  public BrokerMovementDetailFncVO()
  {
    m_baseTplBrokerEntity = new TplBrokerMovEntity();
  }

}