package com.citibank.ods.modules.client.officercmpl.functionality.valueobject;

/**
 * @author bruno.zanetti
 *  
 */
public class OfficerCmplMovementListFncVO extends BaseOfficerCmplListFncVO
{
  private String m_lastUpdUserIdSrc = null;

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
}