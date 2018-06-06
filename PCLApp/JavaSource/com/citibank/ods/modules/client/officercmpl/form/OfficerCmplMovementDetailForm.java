package com.citibank.ods.modules.client.officercmpl.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.officercmpl.form; 
 *@version 1.0
 *@author bruno.zanetti,Mar 6, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class OfficerCmplMovementDetailForm extends BaseOfficerCmplDetailForm
{

  private String m_opernCode = "";

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
