package com.citibank.ods.modules.client.officercmpl.form;

import com.citibank.ods.common.form.HistoryDetailable;
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

public class OfficerCmplHistoryDetailForm extends BaseOfficerCmplDetailForm implements HistoryDetailable {
  private String m_lastAuthDate;
  private String m_lastAuthUserId;
  private String m_offcrRefDate;
  /**
   * @return Returns lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }
  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
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
  /**
   * @return Returns offcrRefDate.
   */
  public String getOffcrRefDate()
  {
    return m_offcrRefDate;
  }
  /**
   * @param offcrRefDate_ Field offcrRefDate to be setted.
   */
  public void setOffcrRefDate( String offcrRefDate_ )
  {
    m_offcrRefDate = offcrRefDate_;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.common.form.HistoryDetailable#getSelectedRefDate()
   */
  public String getSelectedRefDate()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.common.form.HistoryDetailable#setSelectedRefDate(java.lang.String)
   */
  public void setSelectedRefDate( String selectedOffcrRefDate_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    m_offcrRefDate = selectedOffcrRefDate_;
  }
}
