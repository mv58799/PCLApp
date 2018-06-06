package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplLoadProcExecEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Execução de Processos de Carga.
 */
public class TplLoadProcExecEntity extends BaseTplLoadProcExecEntity
{
  /**
   * Cria novo objeto TplLoadProcExecEntity
   */
  public TplLoadProcExecEntity()
  {
    m_data = new TplLoadProcExecEntityVO();
  }
}