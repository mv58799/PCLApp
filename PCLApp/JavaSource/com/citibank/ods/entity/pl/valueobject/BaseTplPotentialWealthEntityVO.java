package com.citibank.ods.entity.pl.valueobject;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gerson.a.rodrigues
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseTplPotentialWealthEntityVO extends BaseEntityVO
{

  /**
   */  
  private BigInteger m_wealthPotnlCode;
  private String m_wealthPotnlText;
  private Date m_lastUpdDate;    
  private String m_lastUpdUserId; 
  private String m_recStatCode;
  
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
   * @return Returns wealthPotnlCode.
   */
  public BigInteger getWealthPotnlCode()
  {
    return m_wealthPotnlCode;
  }
  /**
   * @param wealthPotnlCode_ Field wealthPotnlCode to be setted.
   */
  public void setWealthPotnlCode( BigInteger wealthPotnlCode_ )
  {
    m_wealthPotnlCode = wealthPotnlCode_;
  }
  /**
   * @return Returns wealthPotnlText.
   */
  public String getWealthPotnlText()
  {
    return m_wealthPotnlText;
  }
  /**
   * @param wealthPotnlText_ Field wealthPotnlText to be setted.
   */
  public void setWealthPotnlText( String wealthPotnlText_ )
  {
    m_wealthPotnlText = wealthPotnlText_;
  }
}