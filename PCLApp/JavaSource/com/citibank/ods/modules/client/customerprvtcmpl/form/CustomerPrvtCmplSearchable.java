/*
 * Created on Apr 25, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.customerprvtcmpl.form;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public interface CustomerPrvtCmplSearchable
{
  public void setSelectedEmNbr( String selectedEmNbr_ );

  public String getSelectedEmNbr();
  
  public void setSelectedCustNbr( String selectedECustNbr_ );
  
  public String getSelectedCustNbr(); 
}