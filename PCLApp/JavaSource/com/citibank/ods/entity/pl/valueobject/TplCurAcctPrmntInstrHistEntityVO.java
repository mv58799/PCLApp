package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author michele.monteiro,15/06/2007
 *  
 */

public class TplCurAcctPrmntInstrHistEntityVO extends
    BaseTplCurAcctPrmntInstrEntityVO
{
  //Data/hora que o usuário aprovou o registro
  private Date m_lastAuthDate = null;

  //Usuário que aprovou o registro
  private String m_lastAuthUserId = "";

  //Data de referência
  private Date m_curAcctPrmntInstrRefDate = null;

  //Status do registro - Ativo ou Inativo
  private String m_recStatCode = "";


  /**
   * @return Returns curAcctPrmntInstrRefDate.
   */
  public Date getCurAcctPrmntInstrRefDate()
  {
    return m_curAcctPrmntInstrRefDate;
  }
  /**
   * @param curAcctPrmntInstrRefDate_ Field curAcctPrmntInstrRefDate to be setted.
   */
  public void setCurAcctPrmntInstrRefDate( Date curAcctPrmntInstrRefDate_ )
  {
    m_curAcctPrmntInstrRefDate = curAcctPrmntInstrRefDate_;
  }
  /**
   * @return m_lastAuthDate. Data/hora que o usuário aprovou o registro.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_.Seta a data/hora que o usuário aprovou o registro.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return m_lastAuthUserId. Retorna o usuário que aprovou o registro.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_.Seta o usuário que aprovou o registro.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return m_recStatCode. Retorna o status do registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_.Seta o status do registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}