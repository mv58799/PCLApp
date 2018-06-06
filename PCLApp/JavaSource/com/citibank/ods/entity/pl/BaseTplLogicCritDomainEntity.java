package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLogicCritDomainEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de dom�nios de cr�tica l�gica.
 */
public class BaseTplLogicCritDomainEntity extends BaseODSEntity
{
  // O entity VO de dom�nios de cr�tica l�gica.
  protected BaseTplLogicCritDomainEntityVO m_data;

  /**
   * Recupera o entity VO de dom�nios de cr�tica l�gica.
   * 
   * @return BaseTplLogicCritDomainEntityVO - o entity VO de dom�nios de cr�tica
   *         l�gica.
   */
  public BaseTplLogicCritDomainEntityVO getData()
  {
    return m_data;
  }
}