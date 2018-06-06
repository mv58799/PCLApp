package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplLogicCritDomainEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de dom�nios de cr�tica l�gica.
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