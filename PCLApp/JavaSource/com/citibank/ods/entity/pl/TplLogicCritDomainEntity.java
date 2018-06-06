package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplLogicCritDomainEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de domínios de crítica lógica.
 */
public class TplLogicCritDomainEntity extends BaseTplLogicCritDomainEntity
{
  /**
   * Cria novo objeto TplLogicCritDomainEntity
   */
  public TplLogicCritDomainEntity()
  {
    m_data = new TplLogicCritDomainEntityVO();
  }
}