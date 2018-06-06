package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLoadProcExecRequestEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Requerimento de Execu��o Processos de Carga.
 */
public class BaseTplLoadProcExecRequestEntity extends BaseODSEntity
{
  // O entity VO do requerimento de execu��o do processo de carga.
  protected BaseTplLoadProcExecRequestEntityVO m_data;

  /**
   * Recupera o entity VO do requerimento da execu��o do processo de carga.
   * 
   * @return BaseTplLoadProcExecEntityVO - o entity VO do requerimento da
   *         execu��o do processo de carga.
   */
  public BaseTplLoadProcExecRequestEntityVO getData()
  {
    return m_data;
  }

  /**
   * 
   * Tamanho da coluna motivo da re-execu��o
   */
  public static final int C_EXEC_REAS_TEXT_SIZE = 18;

  /**
   * 
   * Tamanho da coluna descri��o do processo
   */
  public static final int C_LOAD_PROC_TEXT_SIZE = 60;

  /**
   * 
   * Tamanho da coluna c�digo do processo
   */
  public static final int C_LOAD_PROC_NBR_SIZE = 5;

}