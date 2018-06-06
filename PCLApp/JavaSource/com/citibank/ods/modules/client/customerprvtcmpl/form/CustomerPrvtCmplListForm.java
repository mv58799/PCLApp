/*
 * Created on Mar 2, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.customerprvtcmpl.form;

/**
 * @author bruno.zanetti
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class CustomerPrvtCmplListForm extends BaseCustomerPrvtCmplListForm
{
  
  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#getSelectedOffcrNbr()
   */
  
  public String getSelectedOffcrNbr()
  {
    return null;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#setSelectedOffcrNbr(java.lang.String)
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    this.setOffcrNbrSrc(selectedOffcrNbr_);
  }
  
  /**
   * @return Returns selectedCustPrvtNbr.
   */
  public String getSelectedCustPrvtNbr()
  {
    return null;
  }
  /**
   * @param selectedCustPrvtNbr_ Field selectedCustPrvtNbr to be setted.
   */
  public void setSelectedCustPrvtNbr( String selectedCustPrvtNbr_ )
  {
    this.setCustNbrSrc(selectedCustPrvtNbr_);
  }
}