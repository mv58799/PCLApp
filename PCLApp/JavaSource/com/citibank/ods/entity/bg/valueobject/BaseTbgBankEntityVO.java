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
 * <U>Update` by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTbgBankEntityVO extends BaseEntityVO
{
  private String m_bankCode;

  private String m_bankNameText;

  private String m_bankShortNameText;

  private String m_bankPartptCompnInd;

  private String m_bankPartptCrInd;

  private String m_bankPartptDebtInd;

  private BigInteger m_bankDebtLimitAmt;

  private BigInteger m_bankIspbCode;

  private BigInteger m_bankIncCode;

  private String m_bankTrfInd;

  /**
   * @return Returns bankCode.
   */
  public String getBankCode()
  {
    return m_bankCode;
  }
  /**
   * @param bankCode_ Field bankCode to be setted.
   */
  public void setBankCode( String bankCode_ )
  {
    m_bankCode = bankCode_;
  }
  /**
   * @return Returns bankDebtLimitAmt.
   */
  public BigInteger getBankDebtLimitAmt()
  {
    return m_bankDebtLimitAmt;
  }
  /**
   * @param bankDebtLimitAmt_ Field bankDebtLimitAmt to be setted.
   */
  public void setBankDebtLimitAmt( BigInteger bankDebtLimitAmt_ )
  {
    m_bankDebtLimitAmt = bankDebtLimitAmt_;
  }
  /**
   * @return Returns bankIncCode.
   */
  public BigInteger getBankIncCode()
  {
    return m_bankIncCode;
  }
  /**
   * @param bankIncCode_ Field bankIncCode to be setted.
   */
  public void setBankIncCode( BigInteger bankIncCode_ )
  {
    m_bankIncCode = bankIncCode_;
  }
  /**
   * @return Returns bankIspbCode.
   */
  public BigInteger getBankIspbCode()
  {
    return m_bankIspbCode;
  }
  /**
   * @param bankIspbCode_ Field bankIspbCode to be setted.
   */
  public void setBankIspbCode( BigInteger bankIspbCode_ )
  {
    m_bankIspbCode = bankIspbCode_;
  }
  /**
   * @return Returns bankNameText.
   */
  public String getBankNameText()
  {
    return m_bankNameText;
  }
  /**
   * @param bankNameText_ Field bankNameText to be setted.
   */
  public void setBankNameText( String bankNameText_ )
  {
    m_bankNameText = bankNameText_;
  }
  /**
   * @return Returns bankPartptCompnInd.
   */
  public String getBankPartptCompnInd()
  {
    return m_bankPartptCompnInd;
  }
  /**
   * @param bankPartptCompnInd_ Field bankPartptCompnInd to be setted.
   */
  public void setBankPartptCompnInd( String bankPartptCompnInd_ )
  {
    m_bankPartptCompnInd = bankPartptCompnInd_;
  }
  /**
   * @return Returns bankPartptCrInd.
   */
  public String getBankPartptCrInd()
  {
    return m_bankPartptCrInd;
  }
  /**
   * @param bankPartptCrInd_ Field bankPartptCrInd to be setted.
   */
  public void setBankPartptCrInd( String bankPartptCrInd_ )
  {
    m_bankPartptCrInd = bankPartptCrInd_;
  }
  /**
   * @return Returns bankPartptDebtInd.
   */
  public String getBankPartptDebtInd()
  {
    return m_bankPartptDebtInd;
  }
  /**
   * @param bankPartptDebtInd_ Field bankPartptDebtInd to be setted.
   */
  public void setBankPartptDebtInd( String bankPartptDebtInd_ )
  {
    m_bankPartptDebtInd = bankPartptDebtInd_;
  }
  /**
   * @return Returns bankShortNameText.
   */
  public String getBankShortNameText()
  {
    return m_bankShortNameText;
  }
  /**
   * @param bankShortNameText_ Field bankShortNameText to be setted.
   */
  public void setBankShortNameText( String bankShortNameText_ )
  {
    m_bankShortNameText = bankShortNameText_;
  }
  /**
   * @return Returns bankTrfInd.
   */
  public String getBankTrfInd()
  {
    return m_bankTrfInd;
  }
  /**
   * @param bankTrfInd_ Field bankTrfInd to be setted.
   */
  public void setBankTrfInd( String bankTrfInd_ )
  {
    m_bankTrfInd = bankTrfInd_;
  }
}