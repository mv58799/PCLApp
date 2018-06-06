package com.citibank.ods.entity.pl;

import java.util.Date;
import java.util.HashMap;

import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtHistEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Hist�rico de Mem�ria de Risco.
 */
public class TplMrDocPrvtHistEntity extends BaseTplMrDocPrvtEntity
{

  /**
   * Cria novo objeto TplMrDocPrvtHistEntity
   */
  public TplMrDocPrvtHistEntity()
  {
    m_data = new TplMrDocPrvtHistEntityVO();
    m_tplContactCustEntities = new HashMap();
  }

  /**
   * Cria novo objeto TplMrDocPrvtHistEntity com valores definidos.
   * 
   * @param mrDocPrvtEntity_ - Entidade com os valores correntes.
   * @param mrDocRefDate_ - Data de refer�ncia do hist�rico.
   */
  public TplMrDocPrvtHistEntity( TplMrDocPrvtEntity mrDocPrvtEntity_, Date mrDocRefDate_ )
  {
    m_data = new TplMrDocPrvtHistEntityVO( mrDocPrvtEntity_, mrDocRefDate_ );
    m_tplContactCustEntities = new HashMap();
  }
}