package com.citibank.ods.modules.client.custaddress.form;
//
/**
 * @author l.braga,23/03/2007
 *  
 */

public interface CustAddressDetailable
{

  /**
   * @return Returns the m_selectedLoadProcNbr.
   */
  public String getSelectedCustNbr();

  /**
   * @param offcrNbr The m_selectedLoadProcNbr to set.
   */
  public void setSelectedCustNbr( String CustNbr_ );

  /**
   * @return Returns the m_selectedLoadProcNbr.
   */
  public String getSelectedAddrSeqNbr();

  /**
   * @param offcrNbr The m_selectedLoadProcNbr to set.
   */
  public void setSelectedAddrSeqNbr( String addrSeqNbr_ );
}