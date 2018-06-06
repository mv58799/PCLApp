package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLogicCritDomainEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de domínios de crítica lógica.
 */
public class BaseTplLogicCritDomainEntity extends BaseODSEntity
{
  // O entity VO de domínios de crítica lógica.
  protected BaseTplLogicCritDomainEntityVO m_data;

  /**
   * Recupera o entity VO de domínios de crítica lógica.
   * 
   * @return BaseTplLogicCritDomainEntityVO - o entity VO de domínios de crítica
   *         lógica.
   */
  public BaseTplLogicCritDomainEntityVO getData()
  {
    return m_data;
  }
}