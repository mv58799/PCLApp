package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTbgSystemEntity;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @author michele.monteiro,02/05/2007
 *  
 */

public interface BaseTbgSystemDAO
{

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param systemEntity_ - Entidade que possui as chaves usada para busca na
   *          tabela.
   * @return BaseTbgSystemEntity - Entidade com os campos de detalhes.
   */
  public BaseTbgSystemEntity find( BaseTbgSystemEntity systemEntity_ );

  /**
   * Carrega os valores do combobox de código de produtos.
   * 
   * @return DataSet - Retorna DataSet com os valores do combobox com o código
   *         do sistema
   */
  public DataSet loadSysCodeDomain();

}
