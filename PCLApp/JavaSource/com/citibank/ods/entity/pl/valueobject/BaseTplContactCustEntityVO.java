package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * BaseTplContactCust
 * @author Hamilton Matos
 * @date 26/03/2007
 */

public class BaseTplContactCustEntityVO extends BaseEntityVO
{
  
  private BigInteger m_ctcNbr;

  private BigInteger m_custNbr;

  private String m_fullNameText;

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private Date m_lastUpdDate;

  private String m_lastUpdUserId;

  private BigInteger m_phoneDddCode;

  private BigInteger m_phoneExtnNbr;

  private BigInteger m_phoneNbr;

  private String m_recStatCode;
  
  private String m_fullNameText_2;
  
  private String m_fullNameText_3;
  
  public BigInteger getCtcNbr()
  {
    return m_ctcNbr;
  }

  /**
   * @param ctcNbr_ Field ctcNbr to be setted.
   */
  public void setCtcNbr( BigInteger ctcNbr_ )
  {
    m_ctcNbr = ctcNbr_;
  }

  /**
   * @return Returns custNbr.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * @return Returns fullNameText.
   */
  public String getFullNameText()
  {
    return m_fullNameText;
  }

  /**
   * @param fullNameText_ Field fullNameText to be setted.
   */
  public void setFullNameText( String fullNameText_ )
  {
    m_fullNameText = fullNameText_;
  }

  /**
   * @return Returns lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
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
   * @return Returns phoneDddCode.
   */
  public BigInteger getPhoneDddCode()
  {
    return m_phoneDddCode;
  }

  /**
   * @param phoneDddCode_ Field phoneDddCode to be setted.
   */
  public void setPhoneDddCode( BigInteger phoneDddCode_ )
  {
    m_phoneDddCode = phoneDddCode_;
  }

  /**
   * @return Returns phoneExtnNbr.
   */
  public BigInteger getPhoneExtnNbr()
  {
    return m_phoneExtnNbr;
  }

  /**
   * @param phoneExtnNbr_ Field phoneExtnNbr to be setted.
   */
  public void setPhoneExtnNbr( BigInteger phoneExtnNbr_ )
  {
    m_phoneExtnNbr = phoneExtnNbr_;
  }

  /**
   * @return Returns phoneNbr.
   */
  public BigInteger getPhoneNbr()
  {
    return m_phoneNbr;
  }

  /**
   * @param phoneNbr_ Field phoneNbr to be setted.
   */
  public void setPhoneNbr( BigInteger phoneNbr_ )
  {
    m_phoneNbr = phoneNbr_;
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
 * @return
 */
  public String getFullNameText_2() {
	return m_fullNameText_2;
  }

/**
 * @return
 */
  public String getFullNameText_3() {
	return m_fullNameText_3;
  }

/**
 * @param string
 */
  public void setFullNameText_2(String m_fullNameText_2_) {
	m_fullNameText_2 = m_fullNameText_2_;
  }

/**
 * @param string
 */
  public void setFullNameText_3(String m_fullNameText_3_) {
	m_fullNameText_3 = m_fullNameText_3_;
  }

}