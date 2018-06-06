package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackMovEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela do movimento de relacionamento Contato X Memória de
 * Risco.
 */
public class TplMrDocCallbackMovEntity extends BaseTplMrDocCallbackEntity
{

  /**
   * Cria novo objeto TplMrDocCallbackMovEntity.
   */
  public TplMrDocCallbackMovEntity()
  {
    m_data = new TplMrDocCallbackMovEntityVO();
  }

  /**
   * Cria novo objeto TplMrDocCallbackMovEntity com valores definidos.
   * 
   * @param mrDocPrvtMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param contactCustKey_ - Chave com número do contato e número do cliente.
   * @param opernCode_ - Código da operação realizada no movimento.
   */
  public TplMrDocCallbackMovEntity( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_,
                                   ContactCustKey contactCustKey_,
                                   String opernCode_ )
  {
    m_data = new TplMrDocCallbackMovEntityVO( mrDocPrvtMovEntity_,
                                              contactCustKey_, opernCode_ );
  }
}