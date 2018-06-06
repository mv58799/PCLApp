package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplErEmEntityVO extends BaseEntityVO
{

  private String m_emNbr = null;

  private String m_erNbr = null;

  private Date m_lastUpdDate = null;

  private String m_lastUpdUserId = null;

  private String m_roleCustCode = null;
    
  /**
   * @return Returns emNbr.
   */
  public String getEmNbr()
  {
    return m_emNbr;
  }

  /**
   * @param emNbr_ Field emNbr to be setted.
   */
  public void setEmNbr( String emNbr_ )
  {
    m_emNbr = emNbr_;
  }

  /**
   * @return Returns erNbr.
   */
  public String getErNbr()
  {
    return m_erNbr;
  }

  /**
   * @param erNbr_ Field erNbr to be setted.
   */
  public void setErNbr( String erNbr_ )
  {
    m_erNbr = erNbr_;
  }

  /**
   * @return Returns lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns roleCustCode.
   */
  public String getRoleCustCode()
  {
    return m_roleCustCode;
  }

  /**
   * @param roleCustCode_ Field roleCustCode to be setted.
   */
  public void setRoleCustCode( String roleCustCode_ )
  {
    m_roleCustCode = roleCustCode_;
  }
  
}