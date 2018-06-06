/*
 * Created on 14/11/2008
 */
package com.citibank.ods.modules.client.ipdocprvt.form;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class IpDocTransFinancDetailForm extends BaseIpDocTransFinancDetailForm
{

  private String m_selectedCtcNbr;

  private String m_selectedFullNameText;

  private String m_selectedDocTransferCode;

  /**
   * @return Returns selectedCtcNbr.
   */
  public String getSelectedCtcNbr()
  {
	return m_selectedCtcNbr;
  }

  /**
   * @param selectedCtcNbr_ Field selectedCtcNbr to be setted.
   */
  public void setSelectedCtcNbr( String selectedCtcNbr_ )
  {
	m_selectedCtcNbr = selectedCtcNbr_;
  }

  /**
   * @return Returns selectedDocTransferCode.
   */
  public String getSelectedDocTransferCode()
  {
	return m_selectedDocTransferCode;
  }

  /**
   * @param selectedDocTransferCode_ Field selectedDocTransferCode to be setted.
   */
  public void setSelectedDocTransferCode( String selectedDocTransferCode_ )
  {
	m_selectedDocTransferCode = selectedDocTransferCode_;
  }

  /**
   * @return Returns selectedFullNameText.
   */
  public String getSelectedFullNameText()
  {
	return m_selectedFullNameText;
  }

  /**
   * @param selectedFullNameText_ Field selectedFullNameText to be setted.
   */
  public void setSelectedFullNameText( String selectedFullNameText_ )
  {
	m_selectedFullNameText = selectedFullNameText_;
  }
}