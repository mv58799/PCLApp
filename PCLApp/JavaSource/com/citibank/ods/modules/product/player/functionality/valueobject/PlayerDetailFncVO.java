/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality.valueobject;

import com.citibank.ods.entity.pl.TplPlayerEntity;

/**
 * @author atilio.l.araujo
 *  
 */
public class PlayerDetailFncVO extends BasePlayerDetailFncVO
{
  public PlayerDetailFncVO()
  {
    m_baseTplPlayerEntity = new TplPlayerEntity();
  }
}