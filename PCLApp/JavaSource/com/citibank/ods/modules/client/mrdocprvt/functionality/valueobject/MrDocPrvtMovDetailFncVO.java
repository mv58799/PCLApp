package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;

/**
 * @author m.nakamura
 * 
 * Agregador da entidade que representa a tabela de movimento de memória de
 * risco.
 */
public class MrDocPrvtMovDetailFncVO extends BaseMrDocPrvtDetailFncVO
{
  /**
   * Cria novo objeto MrDocPrvtMovementDetailFncVO.
   */
  public MrDocPrvtMovDetailFncVO()
  {
    m_tplMrDocPrvtEntity = new TplMrDocPrvtMovEntity();
    m_to3ProductAccountEntity = new To3ProductAccountEntity();
	m_tplContactCustEntity = new TplContactCustEntity();
    m_insertedContactCust = new ArrayList();
    m_deletedContactCust = new ArrayList();
  }
  
  ArrayList m_callBackMovList = new ArrayList();
  
  
  /**
   * @return Returns callBackMovList.
   */
  public ArrayList getCallBackMovList()
  {
    return m_callBackMovList;
  }
  /**
   * @param callBackMovList_ Field callBackMovList to be setted.
   */
  public void setCallBackMovList( ArrayList callBackMovList_ )
  {
    m_callBackMovList = callBackMovList_;
  }
}