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
 * @author gerson.a.rodrigues,Apr 16, 2007
 *  
 */

public class BaseTplIpDocCallbackEntityVO extends BaseEntityVO
{

  private BigInteger m_ipCallbackCode;
  
  private String m_lastUpdUserId;

  private Date m_lastUpdDate;

  private String m_recStatCode;

  private String m_lastAuthUserId;

  private Date m_lastAuthDate;

  private BigInteger m_custNbr;

  private BigInteger m_ctcNbr;
  
  // Variavel de controle
  private BigInteger m_phoneDDDCodeSrc;

  // Variavel de controle
  private BigInteger m_phoneExtnNbrSrc;

  // Variavel de controle
  private BigInteger m_phoneNbrSrc;

  private BigInteger m_ipDocCode;

  /**
   * @return Returns ctcNbr.
   */
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
   * @return Returns ipCallbackCode.
   */
  public BigInteger getIpCallbackCode()
  {
    return m_ipCallbackCode;
  }

  /**
   * @param ipCallbackCode_ Field ipCallbackCode to be setted.
   */
  public void setIpCallbackCode( BigInteger ipCallbackCode_ )
  {
    m_ipCallbackCode = ipCallbackCode_;
  }

  /**
   * @return Returns ipDocCode.
   */
  public BigInteger getIpDocCode()
  {
    return m_ipDocCode;
  }

  /**
   * @param ipDocCode_ Field ipDocCode to be setted.
   */
  public void setIpDocCode( BigInteger ipDocCode_ )
  {
    m_ipDocCode = ipDocCode_;
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
public BigInteger getPhoneDDDCodeSrc() {
	return m_phoneDDDCodeSrc;
}

/**
 * @return
 */
public BigInteger getPhoneExtnNbrSrc() {
	return m_phoneExtnNbrSrc;
}

/**
 * @return
 */
public BigInteger getPhoneNbrSrc() {
	return m_phoneNbrSrc;
}

/**
 * @param integer
 */
public void setPhoneDDDCodeSrc(BigInteger integer) {
	m_phoneDDDCodeSrc = integer;
}

/**
 * @param integer
 */
public void setPhoneExtnNbrSrc(BigInteger integer) {
	m_phoneExtnNbrSrc = integer;
}

/**
 * @param integer
 */
public void setPhoneNbrSrc(BigInteger integer) {
	m_phoneNbrSrc = integer;
}

}