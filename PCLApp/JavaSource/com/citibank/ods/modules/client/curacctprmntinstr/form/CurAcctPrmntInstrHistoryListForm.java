package com.citibank.ods.modules.client.curacctprmntinstr.form;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.form;
 * @version 1.0
 * @author michele.monteiro,20/06/2007
 */

public class CurAcctPrmntInstrHistoryListForm extends
    BaseCurAcctPrmntInstrListForm
{
  // Data de referência do registro no histórico
  private String m_curAcctPrmntInstrRefDate = "";

  //Número do cliente
  private String m_custNbrHistSrc = "";

  //Código da instrução permanente
  private String m_prmntInstrCodeHistSrc = "";

  // Data de referência do registro no histórico selecioanada no grid
  private String m_selectedCurAcctPrmntInstrRefDate = "";

  /**
   * @return Returns curAcctPrmntInstrRefDate.
   */
  public String getCurAcctPrmntInstrRefDate()
  {
    return m_curAcctPrmntInstrRefDate;
  }

  /**
   * @param curAcctPrmntInstrRefDate_ Field curAcctPrmntInstrRefDate to be
   *          setted.
   */
  public void setCurAcctPrmntInstrRefDate( String curAcctPrmntInstrRefDate_ )
  {
    m_curAcctPrmntInstrRefDate = curAcctPrmntInstrRefDate_;
  }

  public String getCustNbrHistSrc()
  {
    return m_custNbrHistSrc;
  }

  public void setCustNbrHistSrc( String custNbrHistSrc_ )
  {
    m_custNbrHistSrc = custNbrHistSrc_;
  }

  public String getPrmntInstrCodeHistSrc()
  {
    return m_prmntInstrCodeHistSrc;
  }

  public void setPrmntInstrCodeHistSrc( String prmntInstrCodeHistSrc_ )
  {
    m_prmntInstrCodeHistSrc = prmntInstrCodeHistSrc_;
  }

  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    m_custNbrHistSrc = selectedCustNbr_;
  }

  public void setSelectedPrmntInstrCode( String selectedPrmntInstrCode_ )
  {
    m_prmntInstrCodeHistSrc = selectedPrmntInstrCode_;
  }

  /**
   * @return Returns selectedCurAcctPrmntInstrRefDate.
   */
  public String getSelectedCurAcctPrmntInstrRefDate()
  {
    return m_selectedCurAcctPrmntInstrRefDate;
  }

  /**
   * @param selectedCurAcctPrmntInstrRefDate_ Field
   *          selectedCurAcctPrmntInstrRefDate to be setted.
   */
  public void setSelectedCurAcctPrmntInstrRefDate(
                                                  String selectedCurAcctPrmntInstrRefDate_ )
  {
    m_selectedCurAcctPrmntInstrRefDate = selectedCurAcctPrmntInstrRefDate_;
  }
}