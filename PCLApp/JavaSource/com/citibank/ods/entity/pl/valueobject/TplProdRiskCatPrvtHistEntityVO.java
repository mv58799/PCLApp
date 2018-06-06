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
public class TplProdRiskCatPrvtHistEntityVO extends
    BaseTplProdRiskCatPrvtEntityVO
{
  /**
   * Data e Hora que o usuario aprovou o registro cadastrado
   * 
   * Comment for <code>m_lastAuthDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

  /**
   *  Codigo do usuario (SOE ID) que aprovou o cadastro do registro
   * 
   * Comment for <code>m_lastAuthUserID</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserID;

  /**
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao"
   * 
   * Comment for <code>m_recStatCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_recStatCode;

  /**
   * Data de Referencia do registro no historico.
   * 
   * Comment for <code>m_prodRiskRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodRiskCatRefDate;

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
   * @return Returns the lastAuthUserID.
   */
  public String getLastAuthUserID()
  {
    return m_lastAuthUserID;
  }

  /**
   * @param lastAuthUserID_ The lastAuthUserID to set.
   */
  public void setLastAuthUserID( String lastAuthUserID_ )
  {
    m_lastAuthUserID = lastAuthUserID_;
  }

  /**
   * @return Returns the prodRiskCatRefDate.
   */
  public Date getProdRiskCatRefDate()
  {
    return m_prodRiskCatRefDate;
  }

  /**
   * @param prodRiskCatRefDate_ The prodRiskCatRefDate to set.
   */
  public void setProdRiskCatRefDate( Date prodRiskCatRefDate_ )
  {
    m_prodRiskCatRefDate = prodRiskCatRefDate_;
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