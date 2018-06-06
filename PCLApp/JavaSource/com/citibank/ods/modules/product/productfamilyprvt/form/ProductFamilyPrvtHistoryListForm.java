/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.productfamilyprvt.form;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductFamilyPrvtHistoryListForm extends
    BaseProductFamilyPrvtListForm
{
  /*
   * Data de Referencia do registro no historico.
   */
  private String m_prodFamlRefDate = "";

  /**
   * @return Returns the prodFamlRefDate.
   */
  public String getProdFamlRefDate()
  {
    return m_prodFamlRefDate;
  }

  /**
   * @param prodFamlRefDate_ The prodFamlRefDate to set.
   */
  public void setProdFamlRefDate( String prodFamlRefDate_ )
  {
    m_prodFamlRefDate = prodFamlRefDate_;
  }
}