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
public class TplCustomerPrvtCmplHistEntityVO extends BaseTplCustomerPrvtCmplEntityVO
{
  /**
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

  /**
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_custRefDate;

  /**
   * @return Returns the custRefDate.
   */
  public Date getCustRefDate()
  {
    return m_custRefDate;
  }

  /**
   * @param custRefDate_ The custRefDate to set.
   */
  public void setCustRefDate( Date custRefDate_ )
  {
    m_custRefDate = custRefDate_;
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