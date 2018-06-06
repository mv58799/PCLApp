package com.citibank.ods.modules.client.customerprvt.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.customerprvt.form; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 5, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public interface CustomerSearchable
{
  
  public String getReltnNbr();
  
  public void setReltnNbr( String reltnNbr_);
  
  public String getSelectedCustNbrList();
  
  public void setSelectedCustNbrList( String selectedCustNbr_);
  
  public String getCustNbrSrc();
  
  public void setCustNbrSrc( String custNbrSrc_);

}
