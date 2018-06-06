package com.citibank.ods.modules.client.curacctprmntinstr.form;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrMovementDetailForm extends
    BaseCurAcctPrmntInstrDetailForm
{

  // Status do registro - Alteração, Inserção, Exclusão.
  private String m_opernCode = "";

  /**
   * @return Returns m_opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field m_opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}