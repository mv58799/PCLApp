package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @version 1.0
 * @author Hamilton Matos,Jul 26, 2007
 */

public class TplCustomerBrokerHistEntityVO extends
    BaseTplCustomerBrokerEntityVO
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
   * Data de Referencia do registro no historico.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_bkrCustRefDate;
}