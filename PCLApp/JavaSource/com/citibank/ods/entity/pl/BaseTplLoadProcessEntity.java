package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplLoadProcessEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Processos de Carga.
 */
public class BaseTplLoadProcessEntity extends BaseODSEntity
{

  // O entity VO do processo de carga.
  protected BaseTplLoadProcessEntityVO m_data;

  /**
   * Recupera o entity VO do processo de carga.
   * 
   * @return BaseTplLoadProcessEntityVO - o entity VO do processo de carga.
   */
  public BaseTplLoadProcessEntityVO getData()
  {
    return m_data;
  }
  
  /**
   * Constante do código do processo 
   */
  public static final int C_LOAD_PROC_NBR_SIZE = 5;
  
  /**
   * Constante descrição do processo
   */
  public static final int  C_LOAD_PROC_TEXT_SIZE = 60;
  
  
}