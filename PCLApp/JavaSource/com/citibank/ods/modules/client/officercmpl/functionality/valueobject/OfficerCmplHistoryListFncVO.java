package com.citibank.ods.modules.client.officercmpl.functionality.valueobject;

import java.util.Date;

/**
 * @author bruno.zanetti
 *  
 */
public class OfficerCmplHistoryListFncVO extends BaseOfficerCmplListFncVO
{

  private Date m_offcrRefDateSrc = null;

  /**
   * @return Returns offcrRefDate.
   */
  public Date getOffcrRefDateSrc()
  {
    return m_offcrRefDateSrc;
  }

  /**
   * @param offcrRefDate_ Field offcrRefDate to be setted.
   */
  public void setOffcrRefDateSrc( Date offcrRefDate_ )
  {
    m_offcrRefDateSrc = offcrRefDate_;
  }
}