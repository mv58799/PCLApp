/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class TplErHistEntityVO extends BaseTplErEntityVO
{

  /**
   * Comment for <code>m_erEmRefDate</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_erRefDate;

  /**
   * Comment for <code>m_lastAuthDate</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   * Comment for <code>m_lastAuthUserId</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

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
   * @return Returns the recStatCode.
   */
  public String getRecStatCode()
  {
	return m_recStatCode;
  }

  /**
   * @param recStatCode_ The recStatCode to set.
   */
  public void setRecStatCode( String recStatCode_ )
  {
	m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns the erEmRefDate.
   */
  public Date getErRefDate()
  {
	return m_erRefDate;
  }

  /**
   * @param erEmRefDate_ The erEmRefDate to set.
   */
  public void setErRefDate( Date erRefDate_ )
  {
	m_erRefDate = erRefDate_;
  }
}