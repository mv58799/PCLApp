package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.valueobject.TplLogicCritEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Crítica Lógica.
 */
public class TplLogicCritEntity extends BaseTplLogicCritEntity
{
  /**
   * Cria novo objeto TplLogicCritEntity
   */
  public TplLogicCritEntity()
  {
    m_data = new TplLogicCritEntityVO();
    m_logicCritDomainEntities = new ArrayList();
  }
}