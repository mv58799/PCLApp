package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.util.Date;

import com.citibank.ods.entity.pl.TplDocTransferHistEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtHistEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtHistoryDetailFncVO extends BaseIpDocPrvtDetailFncVO
{
  public IpDocPrvtHistoryDetailFncVO()
  {
    m_baseTplIpDocPrvtEntity = new TplIpDocPrvtHistEntity();

    m_baseTplDocTransferEntity = new TplDocTransferHistEntity();

    m_baseTplIpDocCallbackEntity = new TplIpDocCallbackHistEntity();
  }

  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private Date m_lastAuthDate = null;

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  /**
   * @return Returns lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }
}