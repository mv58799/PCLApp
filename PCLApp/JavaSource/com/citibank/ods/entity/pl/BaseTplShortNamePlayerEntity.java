package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplShortNamePlayerEntityVO;

/**
 * 
 * @author aribas
 *
 */
public class BaseTplShortNamePlayerEntity extends BaseODSEntity {
	
  protected BaseTplShortNamePlayerEntityVO m_data;

  public BaseTplShortNamePlayerEntity()
  {
    m_data = new BaseTplShortNamePlayerEntityVO();
  }

  public BaseTplShortNamePlayerEntityVO getData()
  {
    return m_data;
  }
}
