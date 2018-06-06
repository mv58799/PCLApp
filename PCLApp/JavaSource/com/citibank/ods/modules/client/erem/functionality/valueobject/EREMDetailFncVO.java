package com.citibank.ods.modules.client.erem.functionality.valueobject;

import com.citibank.ods.entity.pl.TplErEmEntity;
import com.citibank.ods.entity.pl.TplErEntity;

/**
 * @author gerson.a.rodrigues
 *
 */

public class EREMDetailFncVO extends BaseEREMDetailFncVO
{

  public EREMDetailFncVO()
  {
    m_baseTplErEmEntity = new TplErEmEntity();
    baseTplErEntity = new TplErEntity();
  }

}