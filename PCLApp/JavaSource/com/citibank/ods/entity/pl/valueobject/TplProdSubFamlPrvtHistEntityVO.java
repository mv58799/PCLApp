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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdSubFamlPrvtHistEntityVO extends
    BaseTplProdSubFamlPrvtEntityVO
{
  /**
   * "Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao"
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

  /**
   * Data e Hora que o usuario aprovou o registro cadastrado
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   * Codigo do usuario (SOE ID) que aprovou o cadastro do registro
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Comment for <code>m_subFamlRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodSubFamlRefDate;

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
   * @return Returns the prodSubFamlRefDate.
   */
  public Date getProdSubFamlRefDate()
  {
    return m_prodSubFamlRefDate;
  }

  /**
   * @param prodSubFamlRefDate_ The prodSubFamlRefDate to set.
   */
  public void setProdSubFamlRefDate( Date prodSubFamlRefDate_ )
  {
    m_prodSubFamlRefDate = prodSubFamlRefDate_;
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