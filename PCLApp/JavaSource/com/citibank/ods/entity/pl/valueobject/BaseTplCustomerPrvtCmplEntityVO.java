/*
 * Created on Mar 8, 2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author leonardo.nakada
 *  
 */
public class BaseTplCustomerPrvtCmplEntityVO extends BaseEntityVO
{
  /**
   * Comment for <code>m_custNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_custNbr;

  /**
   * Comment for <code>m_emNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_emNbr;
  
  /**
   * Comment for <code>m_erNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_erNbr;

  /**
	 * Comment for <code>m_wealthPotnlCode</code>
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */

  private BigInteger m_wealthPotnlCode;

  /**
   * Comment for <code>m_prvtCustTypeCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prvtCustTypeCode;
  
  /**
   * Comment for <code>m_classCmplcCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  
  private BigInteger m_classCmplcCode;

  /**
   * Comment for <code>m_offcrNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_offcrNbr;

  /**
   * Comment for <code>m_mailRecvInd</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_mailRecvInd;

  /**
   * Comment for <code>m_offclMailRecvInd</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_offclMailRecvInd;

  /**
   * Comment for <code>m_offclCustNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_glbRevenSysOffcrNbr;

  /**
   * Comment for <code>m_prvtCustNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prvtCustNbr;

  /**
   * Comment for <code>m_pbKeyNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prvtKeyNbr;

  /**
   * Comment for <code>m_lastAuthDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   * Comment for <code>m_lastAuthUserId</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Comment for <code>m_lastUpdDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastUpdDate;

  /**
   * Comment for <code>m_lastUpdUserId</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastUpdUserId;

  /**
   * Status do cliente
   */
  private String m_custPrvtStatCode;

  /**
   * @return Retorna o Codigo do Tipo de Cliente.
   */
  public BigInteger getPrvtCustTypeCode() 
  {
	return m_prvtCustTypeCode;
  }

  /**
   * @param BigInteger prvtCustTypeCode_.
   * Seta o Codigo do Tipo de Cliente.
   */
  public void setPrvtCustTypeCode(BigInteger prvtCustTypeCode_) 
  {
	m_prvtCustTypeCode = prvtCustTypeCode_;
  }
 
  /**
   * @return Returns the classCmplcCode.
   */
  public BigInteger getClassCmplcCode()
  {
    return m_classCmplcCode;
  }

  /**
   * @param classCmplcCode_ The classCmplcCode to set.
   */
  public void setClassCmplcCode( BigInteger classCmplcCode_ )
  {
    m_classCmplcCode = classCmplcCode_;
  }

  /**
   * @return Returns the custNbr.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ The custNbr to set.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

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
   * @return Returns emNbr.
   */
  public String getErNbr()
  {
	return m_erNbr;
  }

  /**
   * @param emNbr_ Field emNbr to be setted.
   */
  public void setErNbr( String erNbr_ )
  {
	m_erNbr = erNbr_;
  }  

  /**
   * @return Returns the glbRevenSysOffcrNbr.
   */
  public BigInteger getGlbRevenSysOffcrNbr()
  {
    return m_glbRevenSysOffcrNbr;
  }

  /**
   * @param glbRevenSysOffcrNbr_ The glbRevenSysOffcrNbr to set.
   */
  public void setGlbRevenSysOffcrNbr( BigInteger glbRevenSysOffcrNbr_ )
  {
    m_glbRevenSysOffcrNbr = glbRevenSysOffcrNbr_;
  }

  /**
   * @return Returns the lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ The lastAuthDate to set.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns the lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ The lastAuthUserId to set.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns the mailRecvInd.
   */
  public String getMailRecvInd()
  {
    return m_mailRecvInd;
  }

  /**
   * @param mailRecvInd_ The mailRecvInd to set.
   */
  public void setMailRecvInd( String mailRecvInd_ )
  {
    m_mailRecvInd = mailRecvInd_;
  }

  /**
   * @return Returns the offclMailRecvInd.
   */
  public String getOffclMailRecvInd()
  {
    return m_offclMailRecvInd;
  }

  /**
   * @param offclMailRecvInd_ The offclMailRecvInd to set.
   */
  public void setOffclMailRecvInd( String offclMailRecvInd_ )
  {
    m_offclMailRecvInd = offclMailRecvInd_;
  }

  /**
   * @return Returns the offcrNbr.
   */
  public BigInteger getOffcrNbr()
  {
    return m_offcrNbr;
  }

  /**
   * @param offcrNbr_ The offcrNbr to set.
   */
  public void setOffcrNbr( BigInteger offcrNbr_ )
  {
    m_offcrNbr = offcrNbr_;
  }

  /**
   * @return Returns the prvtCustNbr.
   */
  public BigInteger getPrvtCustNbr()
  {
    return m_prvtCustNbr;
  }

  /**
   * @param prvtCustNbr_ The prvtCustNbr to set.
   */
  public void setPrvtCustNbr( BigInteger prvtCustNbr_ )
  {
    m_prvtCustNbr = prvtCustNbr_;
  }

  /**
   * @return Returns the prvtKeyNbr.
   */
  public BigInteger getPrvtKeyNbr()
  {
    return m_prvtKeyNbr;
  }

  /**
   * @param prvtKeyNbr_ The prvtKeyNbr to set.
   */
  public void setPrvtKeyNbr( BigInteger prvtKeyNbr_ )
  {
    m_prvtKeyNbr = prvtKeyNbr_;
  }

  /**
   * @return Returns the wealthPotnlCode.
   */
  public BigInteger getWealthPotnlCode()
  {
    return m_wealthPotnlCode;
  }

  /**
   * @param wealthPotnlCode_ The wealthPotnlCode to set.
   */
  public void setWealthPotnlCode( BigInteger wealthPotnlCode_ )
  {
    m_wealthPotnlCode = wealthPotnlCode_;
  }
  
  
  /**
   * @return Returns custPrvtStatCode.
   */
  public String getCustPrvtStatCode()
  {
    return m_custPrvtStatCode;
  }
  /**
   * @param custPrvtStatCode_ Field custPrvtStatCode to be setted.
   */
  public void setCustPrvtStatCode( String custPrvtStatCode_ )
  {
    m_custPrvtStatCode = custPrvtStatCode_;
  }
}