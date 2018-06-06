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
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplDocTransferEntityVO extends BaseEntityVO
{

  private BigInteger m_docTransferCode;

  private String m_ownDestAcctInd;

  private BigInteger m_txnTypeCode;

  private String m_lastUpdUserId;

  private Date m_lastUpdDate;

  private BigInteger m_agnBankCode;

  private BigInteger m_custNbr;

  private BigInteger m_brchCode;

  private BigInteger m_ipDocCode;

  private BigInteger m_curAcctNbr;
  
  private String m_beneMainDestAcctInd;
  
  private String m_beneCpfCnpjNbr;
  
  private String m_beneNameText;
  
  private String m_beneAcctTypeCode;

  /**
   * @return Returns agnBankCode.
   */
  public BigInteger getAgnBankCode()
  {
    return m_agnBankCode;
  }

  /**
   * @param agnBankCode_ Field agnBankCode to be setted.
   */
  public void setAgnBankCode( BigInteger agnBankCode_ )
  {
    m_agnBankCode = agnBankCode_;
  }

  /**
   * @return Returns brchCode.
   */
  public BigInteger getBrchCode()
  {
    return m_brchCode;
  }

  /**
   * @param brchCode_ Field brchCode to be setted.
   */
  public void setBrchCode( BigInteger brchCode_ )
  {
    m_brchCode = brchCode_;
  }

  /**
   * @return Returns curAcctNbr.
   */
  public BigInteger getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_ Field curAcctNbr to be setted.
   */
  public void setCurAcctNbr( BigInteger curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
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
   * @return Returns docTransferCode.
   */
  public BigInteger getDocTransferCode()
  {
    return m_docTransferCode;
  }

  /**
   * @param docTransferCode_ Field docTransferCode to be setted.
   */
  public void setDocTransferCode( BigInteger docTransferCode_ )
  {
    m_docTransferCode = docTransferCode_;
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
   * @return Returns ownDestAcctInd.
   */
  public String getOwnDestAcctInd()
  {
    return m_ownDestAcctInd;
  }

  /**
   * @param ownDestAcctInd_ Field ownDestAcctInd to be setted.
   */
  public void setOwnDestAcctInd( String ownDestAcctInd_ )
  {
    m_ownDestAcctInd = ownDestAcctInd_;
  }

  /**
   * @return Returns txnTypeCode.
   */
  public BigInteger getTxnTypeCode()
  {
    return m_txnTypeCode;
  }

  /**
   * @param txnTypeCode_ Field txnTypeCode to be setted.
   */
  public void setTxnTypeCode( BigInteger txnTypeCode_ )
  {
    m_txnTypeCode = txnTypeCode_;
  }

/**
 * @return
 */
  public String getBeneMainDestAcctInd() {
	return m_beneMainDestAcctInd;
  }

/**
 * @param string
 */
  public void setBeneMainDestAcctInd(String m_beneMainDestAcctInd_) {
	m_beneMainDestAcctInd = m_beneMainDestAcctInd_;
  }
  
  /**
   * @return
   */
  public String getBeneCpfCnpjNbr() {
	  return m_beneCpfCnpjNbr;
  }

  /**
   * @return
   */
  public String getBeneNameText() {
	  return m_beneNameText;
  }

  /**
   * @param string
   */
  public void setBeneCpfCnpjNbr(String m_beneCpfCnpjNbr_) {
	  m_beneCpfCnpjNbr = m_beneCpfCnpjNbr_;
  }

  /**
   * @param string
   */
  public void setBeneNameText(String m_beneNameText_) {
	  m_beneNameText = m_beneNameText_;
  }


/**
 * @return
 */
  public String getBeneAcctTypeCode() {
	return m_beneAcctTypeCode;
  }

/**
 * @param string
 */
  public void setBeneAcctTypeCode(String m_beneAcctTypeCode_) {
	m_beneAcctTypeCode = m_beneAcctTypeCode_;
  }

}