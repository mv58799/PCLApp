/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author fernando.salgado
 *  
 */
public class TplProdQlfyPrvtHistEntityVO extends BaseTplProdQlfyPrvtEntityVO
{
  /**
   * Data de referencia do registro no historico
   * 
   * Comment for <code>m_prodQlfyRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodQlfyRefDate;

  /**
   * Codigo do usuario (SOE ID) que aprovou a ultima alteração do registro
   * 
   * Comment for <code>m_lastAuthUserId</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastAuthUserId;

  /**
   * Data e Hora que o usuario aprovou a alteração do registro
   * 
   * Comment for <code>m_lastAuthDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastAuthDate;

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
   * @return Returns prodQlfyRefDate.
   */
  public Date getProdQlfyRefDate()
  {
    return m_prodQlfyRefDate;
  }

  /**
   * @param prodQlfyRefDate_ Field prodQlfyRefDate to be setted.
   */
  public void setProdQlfyRefDate( Date prodQlfyRefDate_ )
  {
    m_prodQlfyRefDate = prodQlfyRefDate_;
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
}