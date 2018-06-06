package com.citibank.ods.entity.bg.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.bg.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 18, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTbgBranchEntityVO extends BaseEntityVO
{

  private String m_agnBankCode;

  private String m_agnCode;

  private String m_agnVrfyCode;

  private String m_agnText;

  private String m_agnAddrText;

  private String m_agencyTwshpText;

  private String m_agencyTwshpStateCode;

  private String m_agnZipCode;

  private String m_agnAreaCode;

  private String m_agnPhoneNbr;

  private String m_agnFaxNbr;

  private String m_agnTelexNbr;

  private BigInteger m_agnClearHouseCode;

  private String m_actyCode;

  /**
   * @return Returns actyCode.
   */
  public String getActyCode()
  {
    return m_actyCode;
  }
  /**
   * @param actyCode_ Field actyCode to be setted.
   */
  public void setActyCode( String actyCode_ )
  {
    m_actyCode = actyCode_;
  }
  /**
   * @return Returns agencyTwshpStateCode.
   */
  public String getAgencyTwshpStateCode()
  {
    return m_agencyTwshpStateCode;
  }
  /**
   * @param agencyTwshpStateCode_ Field agencyTwshpStateCode to be setted.
   */
  public void setAgencyTwshpStateCode( String agencyTwshpStateCode_ )
  {
    m_agencyTwshpStateCode = agencyTwshpStateCode_;
  }
  /**
   * @return Returns agencyTwshpText.
   */
  public String getAgencyTwshpText()
  {
    return m_agencyTwshpText;
  }
  /**
   * @param agencyTwshpText_ Field agencyTwshpText to be setted.
   */
  public void setAgencyTwshpText( String agencyTwshpText_ )
  {
    m_agencyTwshpText = agencyTwshpText_;
  }
  /**
   * @return Returns agnAddrText.
   */
  public String getAgnAddrText()
  {
    return m_agnAddrText;
  }
  /**
   * @param agnAddrText_ Field agnAddrText to be setted.
   */
  public void setAgnAddrText( String agnAddrText_ )
  {
    m_agnAddrText = agnAddrText_;
  }
  /**
   * @return Returns agnAreaCode.
   */
  public String getAgnAreaCode()
  {
    return m_agnAreaCode;
  }
  /**
   * @param agnAreaCode_ Field agnAreaCode to be setted.
   */
  public void setAgnAreaCode( String agnAreaCode_ )
  {
    m_agnAreaCode = agnAreaCode_;
  }
  /**
   * @return Returns agnBankCode.
   */
  public String getAgnBankCode()
  {
    return m_agnBankCode;
  }
  /**
   * @param agnBankCode_ Field agnBankCode to be setted.
   */
  public void setAgnBankCode( String agnBankCode_ )
  {
    m_agnBankCode = agnBankCode_;
  }
  /**
   * @return Returns agnClearHouseCode.
   */
  public BigInteger getAgnClearHouseCode()
  {
    return m_agnClearHouseCode;
  }
  /**
   * @param agnClearHouseCode_ Field agnClearHouseCode to be setted.
   */
  public void setAgnClearHouseCode( BigInteger agnClearHouseCode_ )
  {
    m_agnClearHouseCode = agnClearHouseCode_;
  }
  /**
   * @return Returns agnCode.
   */
  public String getAgnCode()
  {
    return m_agnCode;
  }
  /**
   * @param agnCode_ Field agnCode to be setted.
   */
  public void setAgnCode( String agnCode_ )
  {
    m_agnCode = agnCode_;
  }
  /**
   * @return Returns agnFaxNbr.
   */
  public String getAgnFaxNbr()
  {
    return m_agnFaxNbr;
  }
  /**
   * @param agnFaxNbr_ Field agnFaxNbr to be setted.
   */
  public void setAgnFaxNbr( String agnFaxNbr_ )
  {
    m_agnFaxNbr = agnFaxNbr_;
  }
  /**
   * @return Returns agnPhoneNbr.
   */
  public String getAgnPhoneNbr()
  {
    return m_agnPhoneNbr;
  }
  /**
   * @param agnPhoneNbr_ Field agnPhoneNbr to be setted.
   */
  public void setAgnPhoneNbr( String agnPhoneNbr_ )
  {
    m_agnPhoneNbr = agnPhoneNbr_;
  }
  /**
   * @return Returns agnTelexNbr.
   */
  public String getAgnTelexNbr()
  {
    return m_agnTelexNbr;
  }
  /**
   * @param agnTelexNbr_ Field agnTelexNbr to be setted.
   */
  public void setAgnTelexNbr( String agnTelexNbr_ )
  {
    m_agnTelexNbr = agnTelexNbr_;
  }
  /**
   * @return Returns agnText.
   */
  public String getAgnText()
  {
    return m_agnText;
  }
  /**
   * @param agnText_ Field agnText to be setted.
   */
  public void setAgnText( String agnText_ )
  {
    m_agnText = agnText_;
  }
  /**
   * @return Returns agnVrfyCode.
   */
  public String getAgnVrfyCode()
  {
    return m_agnVrfyCode;
  }
  /**
   * @param agnVrfyCode_ Field agnVrfyCode to be setted.
   */
  public void setAgnVrfyCode( String agnVrfyCode_ )
  {
    m_agnVrfyCode = agnVrfyCode_;
  }
  /**
   * @return Returns agnZipCode.
   */
  public String getAgnZipCode()
  {
    return m_agnZipCode;
  }
  /**
   * @param agnZipCode_ Field agnZipCode to be setted.
   */
  public void setAgnZipCode( String agnZipCode_ )
  {
    m_agnZipCode = agnZipCode_;
  }
}