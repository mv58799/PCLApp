package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplLoadProcessEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Processos de Carga.
 */
public class TplLoadProcessEntity extends BaseTplLoadProcessEntity
{
  /**
   * Cria novo objeto TplLoadProcessEntity
   */
  public TplLoadProcessEntity()
  {
    m_data = new TplLoadProcessEntityVO();
  }
}