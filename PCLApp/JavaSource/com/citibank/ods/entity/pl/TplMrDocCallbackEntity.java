package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de relacionamento Contato X Memória de Risco.
 */
public class TplMrDocCallbackEntity extends BaseTplMrDocCallbackEntity
{

  /**
   * Cria novo objeto TplMrDocCallbackEntity.
   */
  public TplMrDocCallbackEntity()
  {
    m_data = new TplMrDocCallbackEntityVO();
  }

  /**
   * Cria novo objeto TplMrDocCallbackEntity com valores definidos.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param lastAuthDate_ - A data da aprovação.
   * @param lastAuthUserId_ - O usuário da aprovação.
   */
  public TplMrDocCallbackEntity(
                                TplMrDocCallbackMovEntity mrDocCallbackMovEntity_,
                                Date lastAuthDate_, String lastAuthUserId_ )
  {
    m_data = new TplMrDocCallbackEntityVO( mrDocCallbackMovEntity_,
                                           lastAuthDate_, lastAuthUserId_ );
  }
}