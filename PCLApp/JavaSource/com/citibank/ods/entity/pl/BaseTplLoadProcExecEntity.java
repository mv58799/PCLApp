package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLoadProcExecEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Execução Processos de Carga.
 */
public class BaseTplLoadProcExecEntity extends BaseODSEntity
{
  // O entity VO da execução do processo de carga.
  protected BaseTplLoadProcExecEntityVO m_data;

  /**
   * Recupera o entity VO da execução do processo de carga.
   * 
   * @return BaseTplLoadProcExecEntityVO - o entity VO da execução do processo
   *         de carga.
   */
  public BaseTplLoadProcExecEntityVO getData()
  {
    return m_data;
  }
}