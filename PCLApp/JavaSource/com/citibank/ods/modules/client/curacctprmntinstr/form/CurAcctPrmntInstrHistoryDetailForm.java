package com.citibank.ods.modules.client.curacctprmntinstr.form;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrHistoryDetailForm extends
    BaseCurAcctPrmntInstrDetailForm
{

  // Data e Hora que o Usuario aprovou o Registro Cadastrado
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  // Data de referência
  private String m_curAcctPrmntInstrRefDate = "";

  //Número do cliente
  private String m_selectedCustNbrHist = "";

  //Número da instrução permananente selecionada
  private String m_selectedPrmntInstrCodeHist = "";

  // Data de referência selecionada no grid
  private String m_selectedCurAcctPrmntInstrRefDate = "";

  /**
   * @return Returns m_curAcctPrmntInstrRefDate.
   */
  public String getCurAcctPrmntInstrRefDate()
  {
    return m_curAcctPrmntInstrRefDate;
  }

  /**
   * @param curAcctPrmntInstrRefDate_ Field m_curAcctPrmntInstrRefDate to be
   *          setted.
   */
  public void setCurAcctPrmntInstrRefDate( String curAcctPrmntInstrRefDate_ )
  {
    m_curAcctPrmntInstrRefDate = curAcctPrmntInstrRefDate_;
  }

  /**
   * @return Returns m_lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field m_lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns m_lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field m_lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns m_recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field m_recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns selectedCustNbrHist.
   */
  public String getSelectedCustNbrHist()
  {
    return m_selectedCustNbrHist;
  }

  /**
   * @param selectedCustNbrHist_ Field selectedCustNbrHist to be setted.
   */
  public void setSelectedCustNbrHist( String selectedCustNbrHist_ )
  {
    setCustNbrSrc( selectedCustNbrHist_ );
  }

  /**
   * @return Returns selectedPrmntInstrCodeHist.
   */
  public String getSelectedPrmntInstrCodeHist()
  {
    return m_selectedPrmntInstrCodeHist;
  }

  /**
   * @param selectedPrmntInstrCodeHist_ Field selectedPrmntInstrCodeHist to be
   *          setted.
   */
  public void setSelectedPrmntInstrCodeHist( String selectedPrmntInstrCodeHist_ )
  {
    setPrmntInstrCodeSrc( selectedPrmntInstrCodeHist_ );
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
    setCurAcctPrmntInstrRefDate( selectedCurAcctPrmntInstrRefDate_ );
  }
}