/*
 * Created on Apr 22, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.erem.functionality.valueobject;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EREMMovementListFncVO extends BaseEREMListFncVO
{
  private String m_lastUpdUserId;

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }
}