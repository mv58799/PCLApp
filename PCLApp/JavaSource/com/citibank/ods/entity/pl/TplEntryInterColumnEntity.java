package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplEntryInterColumnEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Interface de Entrada de Atributos.
 */
public class TplEntryInterColumnEntity extends BaseTplEntryInterColumnEntity
{
  /**
   * Cria novo objeto TplEntryInterColumnEntity
   */
  public TplEntryInterColumnEntity()
  {
    m_data = new TplEntryInterColumnEntityVO();
  }
}