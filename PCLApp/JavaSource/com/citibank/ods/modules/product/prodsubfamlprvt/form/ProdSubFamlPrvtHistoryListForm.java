/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.form;

/**
 * @author leonardo.nakada
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProdSubFamlPrvtHistoryListForm extends
    BaseProdSubFamlPrvtListForm
{
  /*
   * Data de Referencia do registro no historico.
   */
  private String m_prodSubFamlRefDate = "";
  
  
  /**
   * @return Returns the prodSubFamlRefDate.
   */
  public String getProdSubFamlRefDate()
  {
    return m_prodSubFamlRefDate;
  }
  /**
   * @param prodSubFamlRefDate_ The prodSubFamlRefDate to set.
   */
  public void setProdSubFamlRefDate( String prodSubFamlRefDate_ )
  {
    m_prodSubFamlRefDate = prodSubFamlRefDate_;
  }
}
