/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject;

import java.util.Date;

/**
 * @author leonardo.nakada
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductFamilyPrvtHistoryListFncVO extends
    BaseProductFamilyPrvtListFncVO
{
  /**
   * Comment for <code>m_subFamlRefDate</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_prodFamlRefDate;
  
  
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
}
