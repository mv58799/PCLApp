package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import com.citibank.ods.entity.pl.TplMrDocPrvtHistEntity;

/**
 * @author m.nakamura
 * 
 * Agregador da entidade que representa a tabela de hist�rico de mem�ria de
 * risco.
 */
public class MrDocPrvtHistDetailFncVO extends BaseMrDocPrvtDetailFncVO
{
  /**
   * Cria novo objeto MrDocPrvtHistoryDetailFncVO.
   */
  public MrDocPrvtHistDetailFncVO()
  {
    m_tplMrDocPrvtEntity = new TplMrDocPrvtHistEntity();
  }
}