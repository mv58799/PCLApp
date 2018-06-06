package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author angelica.almeida,18/06/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class CurAcctPrmntInstrMovementListFncVO extends
    BaseCurAcctPrmntInstrListFncVO
{
  /**
   * Constante de descrição do campo do último usuario a atualizar
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usuário";

  private String m_lastUpdUserIdSrc;

  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }
}