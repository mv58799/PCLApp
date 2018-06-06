package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplLoadProcExecRequestEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Requerimento de Execução de Processos de Carga.
 */
public class TplLoadProcExecRequestEntity extends
    BaseTplLoadProcExecRequestEntity
{
  /**
   * Cria novo objeto TplLoadProcExecRequestEntity
   */
  public TplLoadProcExecRequestEntity()
  {
    m_data = new TplLoadProcExecRequestEntityVO();
  }
}