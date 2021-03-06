package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.entity.pl.valueobject; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 16, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class TplIpDocCallbackHistEntityVO extends BaseTplIpDocCallbackEntityVO
{

  private Date m_ipDocCallbackRefDate;

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private String m_recStatCode;    
  
 
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
  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }
  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
  /**
   * @return Returns ipDocCallbackRefDate.
   */
  public Date getIpDocCallbackRefDate()
  {
    return m_ipDocCallbackRefDate;
  }
  /**
   * @param ipDocCallbackRefDate_ Field ipDocCallbackRefDate to be setted.
   */
  public void setIpDocCallbackRefDate( Date ipDocCallbackRefDate_ )
  {
    m_ipDocCallbackRefDate = ipDocCallbackRefDate_;
  }
}
