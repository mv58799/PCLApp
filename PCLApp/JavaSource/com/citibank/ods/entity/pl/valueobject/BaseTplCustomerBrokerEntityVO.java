package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * BaseTplCustomerBroker
 * @author Hamilton Matos
 */

public class BaseTplCustomerBrokerEntityVO extends BaseEntityVO
{
  private String m_bkrNameText;

  private String m_bkrAddrText;

  private String m_bkrCnpjNbr;

  private BigInteger m_bkrCustNbr;

  private BigInteger m_custNbr;

  private String m_custFullNameText;

  private Date m_lastAuthDate;

  private String m_lastAuthUserId;

  private Date m_lastUpdDate;

  private String m_lastUpdUserId;

  private String m_recStatCode;

  /**
   * @return Returns bkrCnpjNbr.
   */
  public String getBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /**
   * @param bkrCnpjNbr_ Field bkrCnpjNbr to be setted.
   */
  public void setBkrCnpjNbr( String bkrCnpjNbr_ )
  {
    m_bkrCnpjNbr = bkrCnpjNbr_;
  }

  /**
   * @return Returns bkrCustNbr.
   */
  public BigInteger getBkrCustNbr()
  {
    return m_bkrCustNbr;
  }

  /**
   * @param bkrCustNbr_ Field bkrCustNbr to be setted.
   */
  public void setBkrCustNbr( BigInteger bkrCustNbr_ )
  {
    m_bkrCustNbr = bkrCustNbr_;
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
   * @return Returns bkrAddrText.
   */
  public String getBkrAddrText()
  {
    return m_bkrAddrText;
  }

  /**
   * @param bkrAddrText_ Field bkrAddrText to be setted.
   */
  public void setBkrAddrText( String bkrAddrText_ )
  {
    m_bkrAddrText = bkrAddrText_;
  }

  /**
   * @return Returns bkrNameText.
   */
  public String getBkrNameText()
  {
    return m_bkrNameText;
  }

  /**
   * @param bkrNameText_ Field bkrNameText to be setted.
   */
  public void setBkrNameText( String bkrNameText_ )
  {
    m_bkrNameText = bkrNameText_;
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }
}