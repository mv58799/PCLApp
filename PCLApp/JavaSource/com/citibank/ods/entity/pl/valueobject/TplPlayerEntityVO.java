/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerEntityVO extends BaseTplPlayerEntityVO
{
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
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao"
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
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
   * @return Returns the lastAuthUserID.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserID_ The lastAuthUserID to set.
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
}