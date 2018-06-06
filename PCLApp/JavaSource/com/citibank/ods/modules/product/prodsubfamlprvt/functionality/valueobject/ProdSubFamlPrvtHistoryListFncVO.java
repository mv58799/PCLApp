/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject;

import java.util.Date;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProdSubFamlPrvtHistoryListFncVO extends
    BaseProdSubFamlPrvtListFncVO
{
  /**
   * Comment for <code>m_subFamlRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodSubFamlRefDate;

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
}