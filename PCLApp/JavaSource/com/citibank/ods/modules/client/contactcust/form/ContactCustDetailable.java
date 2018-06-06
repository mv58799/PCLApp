package com.citibank.ods.modules.client.contactcust.form;

/**
 * 
 * @author leandro.braga
 */

public interface ContactCustDetailable
{
  /**
   * @return Returns the m_selectedCtcNbr.
   */
  public String getSelectedCtcNbr();

  /**
   * @return Returns the m_selectedCustNbr.
   */
  public String getSelectedCustNbr();

  /**
   * @param The selectedCtcNbr to set.
   */
  public void setSelectedCtcNbr( String ctcNbr_ );

  /**
   * @param The selectedCustNbr to set.
   */
  public void setSelectedCustNbr( String custNbr_ );

}