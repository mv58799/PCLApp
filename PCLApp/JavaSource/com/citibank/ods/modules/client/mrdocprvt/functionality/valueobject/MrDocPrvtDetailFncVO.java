package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Agregador da entidade que representa a tabela memória de risco.
 */
public class MrDocPrvtDetailFncVO extends BaseMrDocPrvtDetailFncVO
{
  /**
   * Cria novo objeto MrDocPrvtDetailFncVO.
   */
  public MrDocPrvtDetailFncVO()
  {
    m_tplMrDocPrvtEntity = new TplMrDocPrvtEntity();
    m_to3ProductAccountEntity = new To3ProductAccountEntity();
    m_tplContactCustEntity = new TplContactCustEntity();
    m_insertedContactCust = new ArrayList();
    m_deletedContactCust = new ArrayList();
  }
}