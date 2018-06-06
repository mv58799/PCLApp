package com.citibank.ods.modules.client.erem.functionality.valueobject;

import java.util.Date;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class EREMHistoryListFncVO extends BaseEREMListFncVO
{
  private Date m_erEmRefDate;

  private String m_erNbrHistSrc = "";

  private String m_emNbrHistSrc = "";

  private boolean m_isFromCurrent;

  /**
   * @return Returns erEmRefDate.
   */
  public Date getErEmRefDate()
  {
    return m_erEmRefDate;
  }

  /**
   * @param erEmRefDate_ Field erEmRefDate to be setted.
   */
  public void setErEmRefDate( Date erEmRefDate_ )
  {
    m_erEmRefDate = erEmRefDate_;
  }

  public String getEmNbrHistSrc()
  {
    return m_emNbrHistSrc;
  }

  public void setEmNbrHistSrc( String emNbrHistSrc_ )
  {
    m_emNbrHistSrc = emNbrHistSrc_;
  }

  public String getErNbrHistSrc()
  {
    return m_erNbrHistSrc;
  }

  public void setErNbrHistSrc( String erNbrHistSrc_ )
  {
    m_erNbrHistSrc = erNbrHistSrc_;
  }

  public boolean isFromCurrent()
  {
    return m_isFromCurrent;
  }

  public void setFromCurrent( boolean isFromCurrent_ )
  {
    m_isFromCurrent = isFromCurrent_;
  }
}