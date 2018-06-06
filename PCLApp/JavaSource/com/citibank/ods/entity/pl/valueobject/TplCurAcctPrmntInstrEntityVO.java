package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 *  
 */

public class TplCurAcctPrmntInstrEntityVO extends
    BaseTplCurAcctPrmntInstrEntityVO
{

  //Data/Hora que o usuário aprovou o cadastro do registro
  private Date m_lastAuthDate;

  //Usuário que aprovou o cadastro
  private String m_lastAuthUserId;

  //Status do registro
  private String m_recStatCode;

  /**
   * @return m_lastAuthDate. Retorna data/hora que o usuário aprovou o cadastro
   *         do registro.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_. Seta data/hora que o usuário aprovou o cadastro do
   *          registro.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return m_lastAuthUserId. Retorna o usuário que aprovou o cadastro do
   *         registro.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_.Seta o usuário que aprovou o registro do cadastro.
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