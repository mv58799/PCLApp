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
 * @author gerson.a.rodrigues,Mar 13, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplClassCmplcEntityVO extends BaseEntityVO
{

  private BigInteger m_classCmplcCode;

  private String m_classCmplcText;

  private String m_sensInd;

  private Date m_lastUpdDate;

  private String m_lastUpdUserId;

  private String m_recStatCode;

  /**
   * @return Returns classCmplcCode.
   */
  public BigInteger getClassCmplcCode()
  {
    return m_classCmplcCode;
  }

  /**
   * @param classCmplcCode_ Field classCmplcCode to be setted.
   */
  public void setClassCmplcCode( BigInteger classCmplcCode_ )
  {
    m_classCmplcCode = classCmplcCode_;
  }

  /**
   * @return Returns classCmplcText.
   */
  public String getClassCmplcText()
  {
    return m_classCmplcText;
  }

  /**
   * @param classCmplcText_ Field classCmplcText to be setted.
   */
  public void setClassCmplcText( String classCmplcText_ )
  {
    m_classCmplcText = classCmplcText_;
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
   * @return Returns sensInd.
   */
  public String getSensInd()
  {
    return m_sensInd;
  }

  /**
   * @param sensInd_ Field sensInd to be setted.
   */
  public void setSensInd( String sensInd_ )
  {
    m_sensInd = sensInd_;
  }
}