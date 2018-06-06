/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProductFamilyPrvtHistEntityVO extends BaseTplProductFamilyPrvtEntityVO
{
  /**
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

  /**
   * Comment for <code>m_lastAuthDate</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   * Comment for <code>m_lastAuthUserID</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Comment for <code>m_famlRefDate</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodFamlRefDate;

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
   * @return Returns the prodFamlRefDate.
   */
  public Date getProdFamlRefDate()
  {
    return m_prodFamlRefDate;
  }

  /**
   * @param prodFamlRefDate_ The prodFamlRefDate to set.
   */
  public void setProdFamlRefDate( Date prodFamlRefDate_ )
  {
    m_prodFamlRefDate = prodFamlRefDate_;
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
}