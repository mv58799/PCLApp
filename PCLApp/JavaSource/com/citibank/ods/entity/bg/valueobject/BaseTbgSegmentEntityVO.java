package com.citibank.ods.entity.bg.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.entity.bg.valueobject; 
 *@version 1.0
 *@author acacio.domingos,Apr 24, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class BaseTbgSegmentEntityVO extends BaseEntityVO
{
   private String m_segNameCode;
   
   private String m_segNameText;
   
   private String m_segCustTypeCode;
   
   private String m_segStartDate;
   
   private String m_segStatCode;
   
   private String m_segMthIncoMinCode;
   
   private BigInteger m_segTotalSaleMinAmt;
   
   private String m_segCitiBusCode;
   
   private BigInteger m_segPriorCode;

   
  /**
   * @return Returns segCitiBusCode.
   */
  public String getSegCitiBusCode()
  {
    return m_segCitiBusCode;
  }
  /**
   * @param segCitiBusCode_ Field segCitiBusCode to be setted.
   */
  public void setSegCitiBusCode( String segCitiBusCode_ )
  {
    m_segCitiBusCode = segCitiBusCode_;
  }
  /**
   * @return Returns segCustTypeCode.
   */
  public String getSegCustTypeCode()
  {
    return m_segCustTypeCode;
  }
  /**
   * @param segCustTypeCode_ Field segCustTypeCode to be setted.
   */
  public void setSegCustTypeCode( String segCustTypeCode_ )
  {
    m_segCustTypeCode = segCustTypeCode_;
  }
  /**
   * @return Returns segMthIncoMinCode.
   */
  public String getSegMthIncoMinCode()
  {
    return m_segMthIncoMinCode;
  }
  /**
   * @param segMthIncoMinCode_ Field segMthIncoMinCode to be setted.
   */
  public void setSegMthIncoMinCode( String segMthIncoMinCode_ )
  {
    m_segMthIncoMinCode = segMthIncoMinCode_;
  }
  /**
   * @return Returns segNameCode.
   */
  public String getSegNameCode()
  {
    return m_segNameCode;
  }
  /**
   * @param segNameCode_ Field segNameCode to be setted.
   */
  public void setSegNameCode( String segNameCode_ )
  {
    m_segNameCode = segNameCode_;
  }
  /**
   * @return Returns segNameText.
   */
  public String getSegNameText()
  {
    return m_segNameText;
  }
  /**
   * @param segNameText_ Field segNameText to be setted.
   */
  public void setSegNameText( String segNameText_ )
  {
    m_segNameText = segNameText_;
  }
  /**
   * @return Returns segPriorCode.
   */
  public BigInteger getSegPriorCode()
  {
    return m_segPriorCode;
  }
  /**
   * @param segPriorCode_ Field segPriorCode to be setted.
   */
  public void setSegPriorCode( BigInteger segPriorCode_ )
  {
    m_segPriorCode = segPriorCode_;
  }
  /**
   * @return Returns segStartDate.
   */
  public String getSegStartDate()
  {
    return m_segStartDate;
  }
  /**
   * @param segStartDate_ Field segStartDate to be setted.
   */
  public void setSegStartDate( String segStartDate_ )
  {
    m_segStartDate = segStartDate_;
  }
  /**
   * @return Returns segStatCode.
   */
  public String getSegStatCode()
  {
    return m_segStatCode;
  }
  /**
   * @param segStatCode_ Field segStatCode to be setted.
   */
  public void setSegStatCode( String segStatCode_ )
  {
    m_segStatCode = segStatCode_;
  }
  /**
   * @return Returns segTotalSaleMinAmt.
   */
  public BigInteger getSegTotalSaleMinAmt()
  {
    return m_segTotalSaleMinAmt;
  }
  /**
   * @param segTotalSaleMinAmt_ Field segTotalSaleMinAmt to be setted.
   */
  public void setSegTotalSaleMinAmt( BigInteger segTotalSaleMinAmt_ )
  {
    m_segTotalSaleMinAmt = segTotalSaleMinAmt_;
  }
}
