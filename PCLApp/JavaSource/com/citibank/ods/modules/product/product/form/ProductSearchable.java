/*
 * Created on Apr 4, 2007
 *
 */
package com.citibank.ods.modules.product.product.form;

/**
 * @author leonardo.nakada
 * 
 */
public interface ProductSearchable
{
  public void setSelectedProdCode( String selectedProdCode_ );

  public String getSelectedProdCode();
  
  public void setProdCodeSrc( String selectedProdCode_ );

  public String getProdCodeSrc();

  public void setSelectedSysCode( String selectedSysCode_ );

  public String getSelectedSysCode();
  
  public void setSysCodeSrc( String selectedSysCode_ );

  public String getSysCodeSrc();

  public void setSelectedSysSegCode( String selectedSysSegCode_ );

  public String getSelectedSysSegCode();
  
  public void setSysSegCodeSrc( String selectedSysSegCode_ );

  public String getSysSegCodeSrc();
}