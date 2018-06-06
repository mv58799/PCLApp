package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackHistEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela do hist�rico de relacionamento Contato X Mem�ria de
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
   * Current Date mrDocRefDate_ - Data de refer�ncia do hist�rico.
   */
  public TplMrDocCallbackHistEntity(
                                    TplMrDocCallbackEntity mrDocCallbackEntity_,
                                    Date mrDocRefDate_ )
  {
    m_data = new TplMrDocCallbackHistEntityVO( mrDocCallbackEntity_,
                                               mrDocRefDate_ );
  }
}