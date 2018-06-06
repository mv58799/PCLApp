package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplEntryInterEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Interface de Entrada.
 */
public class BaseTplEntryInterEntity extends BaseODSEntity
{

  // A entidade de Interface de Entrada.
  protected BaseTplEntryInterEntityVO m_data;

  /**
   * Recupera o entity VO de Interface de Entrada.
   * 
   * @return BaseTplEntryInterEntityVO - o entity VO de Interface de Entrada.
   */
  public BaseTplEntryInterEntityVO getData()
  {
    return m_data;
  }

}