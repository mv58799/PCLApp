package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplRoleCustEntityVO extends BaseEntityVO
{
  /**
   * Comment for <code>m_roleCustCode</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_roleCustCode;

  /**
   * Comment for <code>m_roleCustText</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_roleCustText;

  /**
   * Comment for <code>m_lastUpdDate</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastUpdDate;

  /**
   * Comment for <code>m_lastUpdUserId</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastUpdUserId;

  /**
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;
  
  
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
   * @return Returns the roleCustCode.
   */
  public String getRoleCustCode()
  {
    return m_roleCustCode;
  }
  /**
   * @param roleCustCode_ The roleCustCode to set.
   */
  public void setRoleCustCode( String roleCustCode_ )
  {
    m_roleCustCode = roleCustCode_;
  }
  /**
   * @return Returns the roleCustText.
   */
  public String getRoleCustText()
  {
    return m_roleCustText;
  }
  /**
   * @param roleCustText_ The roleCustText to set.
   */
  public void setRoleCustText( String roleCustText_ )
  {
    m_roleCustText = roleCustText_;
  }
}