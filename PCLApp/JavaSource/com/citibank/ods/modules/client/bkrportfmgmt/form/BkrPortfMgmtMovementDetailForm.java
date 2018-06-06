package com.citibank.ods.modules.client.bkrportfmgmt.form;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementDetailForm extends BaseBkrPortfMgmtDetailForm
{

  // Vari�vel utilizada no controle do bot�o de aprova��o
  private String m_makerUser = "";

  // Vari�vel utilizada no controle do bot�o de aprova��o
  private String m_approveButtonState = "";

  // C�digo da opera��o realizada no registro: inclus�o, altera��o, exclus�o
  private String m_opernCode = "";

  // Coluna de c�digo de opera��o
  private String[] m_opernCodeInBkrPortfMgmtGrid;

  // Resultado da Consulta de Carteiras Administradas
  private DataSet m_portfolioResults;

  // Vari�vel de controle para a��o de altera��o
  private String m_isUpdate = "";

  // Vari�vel de controle para a��o de aprova��o
  private String m_isApprove = "";

  /**
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }

  /**
   * @return Returns opernCodeInBkrPortfMgmtGrid.
   */
  public String[] getOpernCodeInBkrPortfMgmtGrid()
  {
    return m_opernCodeInBkrPortfMgmtGrid;
  }

  /**
   * @param opernCodeInBkrPortfMgmtGrid_ Field opernCodeInBkrPortfMgmtGrid to be
   *          setted.
   */
  public void setOpernCodeInBkrPortfMgmtGrid(
                                             String[] opernCodeInBkrPortfMgmtGrid_ )
  {
    m_opernCodeInBkrPortfMgmtGrid = opernCodeInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns approveButtonState.
   */
  public String getApproveButtonState()
  {
    return m_approveButtonState;
  }

  /**
   * @param approveButtonState_ Field approveButtonState to be setted.
   */
  public void setApproveButtonState( String approveButtonState_ )
  {
    m_approveButtonState = approveButtonState_;
  }

  public DataSet getPortfolioResults()
  {
    return m_portfolioResults;
  }

  public void setPortfolioResults( DataSet portfolioResults_ )
  {
    m_portfolioResults = portfolioResults_;
  }

  public String getIsApprove()
  {
    return m_isApprove;
  }

  public void setIsApprove( String isApprove_ )
  {
    m_isApprove = isApprove_;
  }

  public String getIsUpdate()
  {
    return m_isUpdate;
  }

  public void setIsUpdate( String isUpdate_ )
  {
    m_isUpdate = isUpdate_;
  }

  public String getMakerUser()
  {
    return m_makerUser;
  }

  public void setMakerUser( String makerUser_ )
  {
    m_makerUser = makerUser_;
  }
}