package com.citibank.ods.modules.client.curacctprmntinstr.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.form;
 * @version 1.0
 * @author michele.monteiro,20/06/2007
 */

public class CurAcctPrmntInstrMovementListForm extends
    BaseCurAcctPrmntInstrListForm
{
  //Ação do registro( Insert, Delete, Update)
  private String m_opernCode = "";

  //Código do usuário da última alteração
  private String m_lastUpdUserIdSrc = "";

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}