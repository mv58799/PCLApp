package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplMrDocCallbackEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de relacionamento Contato X Memória de Risco.
 */
public class BaseTplMrDocCallbackEntity extends BaseEntity
{
  // O entity VO do relacionamento Contato X Memória de Risco.
  protected BaseTplMrDocCallbackEntityVO m_data = null;

  // O entity VO de contatos do cliente.
  protected BaseTplContactCustEntity m_contactCustEntity = null;

  /**
   * Recupera o entity VO de Telefones de Confirmação de Memória de Risco.
   * 
   * @return BaseTplMrDocCallbackEntityVO - O entity VO de Telefones de
   *         Confirmação de Memória de Risco.
   */
  public BaseTplMrDocCallbackEntityVO getData()
  {
    return m_data;
  }

  /**
   * Recupera a entidade de contato do cliente.
   * 
   * @return BaseTplContactCustEntity - Retorna a entidade de contato do
   *         cliente.
   */
  public BaseTplContactCustEntity getTplContactCustEntity()
  {
    return m_contactCustEntity;
  }

  /**
   * Seta a entidade de contato do cliente.
   * 
   * @param m_contactCustEntityVO - A entidade de contato do cliente.
   */
  public void setTplContactCustEntity(
                                      BaseTplContactCustEntity contactCustEntity_ )
  {
    m_contactCustEntity = contactCustEntity_;
  }
}