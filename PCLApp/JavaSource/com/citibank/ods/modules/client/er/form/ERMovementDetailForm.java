/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.form;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERMovementDetailForm extends BaseERDetailForm
{
  // 
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