package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.BaseTplMrDocCallbackEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Telefones de Confirmação da
 * Memória de Risco.
 */
public interface BaseTplMrDocCallbackDAO
{
  /**
   * Retorna uma entidade com os campos de detalhe.
   * 
   * @param baseTplMrDocCallbackEntity_ - Entidade que possui os dados
   *          necessários para busca na tabela.
   * @return BaseTplMrDocCallbackEntity - Entidade com os campos de detalhe.
   */
  public BaseTplMrDocCallbackEntity find(
                                         BaseTplMrDocCallbackEntity baseTplMrDocCallbackEntity_ );
}