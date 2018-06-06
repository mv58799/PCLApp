package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTbgSystemSegmentEntity;

/**
 * @author michele.monteiro,02/05/2007
 */

public interface BaseTbgSystemSegmentDAO
{

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param systemSegmentEntity_ - Entidade que possui as chaves usada para
   *          busca na tabela.
   * @return BaseTbgSystemSegmentEntity - Entidade com os campos de detalhes.
   */
  public BaseTbgSystemSegmentEntity find(
                                         BaseTbgSystemSegmentEntity systemSegmentEntity_ );

  /**
   * Carrega os valores do combobox de código de produtos.
   * 
   * @return DataSet - Retorna DataSet com os valores do combobox com o segmento
   *         do sistema
   */
  public DataSet loadSysSegCodeDomain();

}