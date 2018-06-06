package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackHistEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela do histórico de relacionamento Contato X Memória de
 * Risco.
 */
public class TplMrDocCallbackHistEntity extends BaseTplMrDocCallbackEntity
{

  /**
   * Cria novo objeto TplMrDocCallbackHistEntity.
   */
  public TplMrDocCallbackHistEntity()
  {
    m_data = new TplMrDocCallbackHistEntityVO();
  }

  /**
   * Cria novo objeto TplMrDocCallbackHistEntity com valores definidos.
   * 
   * TplMrDocCallbackEntity mrDocCallbackEntity_ - Entidade com os valores de
   * Current Date mrDocRefDate_ - Data de referência do histórico.
   */
  public TplMrDocCallbackHistEntity(
                                    TplMrDocCallbackEntity mrDocCallbackEntity_,
                                    Date mrDocRefDate_ )
  {
    m_data = new TplMrDocCallbackHistEntityVO( mrDocCallbackEntity_,
                                               mrDocRefDate_ );
  }
}