package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplEntryInterEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Interface de Entrada.
 */
public class TplEntryInterEntity extends BaseTplEntryInterEntity
{
  /**
   * Cria novo objeto TplEntryInterEntity
   */
  public TplEntryInterEntity()
  {
    m_data = new TplEntryInterEntityVO();
  }
}