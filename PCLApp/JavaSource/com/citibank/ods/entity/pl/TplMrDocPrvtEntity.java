package com.citibank.ods.entity.pl;

import java.util.Date;
import java.util.HashMap;

import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Mem�ria de Risco.
 */
public class TplMrDocPrvtEntity extends BaseTplMrDocPrvtEntity
{

  /**
   * Cria novo objeto TplMrDocPrvtEntity.
   */
  public TplMrDocPrvtEntity()
  {
    m_data = new TplMrDocPrvtEntityVO();
    m_tplContactCustEntities = new HashMap();
  }

  /**
   * Cria novo objeto TplMrDocPrvtEntity com valores definidos.
   * 
   * @param mrDocPrvtMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param lastAuthDate_ - A data da aprova��o.
   * @param lastAuthUserId_ - O usu�rio da aprova��o.
   */
  public TplMrDocPrvtEntity( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_,
                            Date lastAuthDate_, String lastAuthUserId_ )
  {
    m_data = new TplMrDocPrvtEntityVO( mrDocPrvtMovEntity_, lastAuthDate_,
                                       lastAuthUserId_ );
  }
}