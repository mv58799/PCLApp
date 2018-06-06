package com.citibank.ods.entity.pl;

import java.math.BigInteger;
import java.util.HashMap;

import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtMovEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Movimento de Memória de Risco.
 */
public class TplMrDocPrvtMovEntity extends BaseTplMrDocPrvtEntity
{
  /**
   * Cria novo objeto TplMrDocPrvtMovEntity.
   */
  public TplMrDocPrvtMovEntity()
  {
    m_data = new TplMrDocPrvtMovEntityVO();
    m_tplContactCustEntities = new HashMap();
  }

  /**
   * Cria novo objeto TplMrDocPrvtEntity com valores definidos.
   * 
   * @param mrDocPrvtMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param lastAuthDate_ - A data da aprovação.
   * @param lastAuthUserId_ - O usuário da aprovação.
   */
  public TplMrDocPrvtMovEntity( TplMrDocPrvtEntity mrDocPrvtEntity_,
                               BigInteger prvtMrCode_, String prodAcctCode_,
                               String prodUnderAcctCode_ )
  {
    m_data = new TplMrDocPrvtMovEntityVO( mrDocPrvtEntity_, prvtMrCode_,
                                          prodAcctCode_, prodUnderAcctCode_ );
  }
}