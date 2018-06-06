package com.citibank.ods.modules.client.bkrportfmgmt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable;

/**
 * @see package com.citibank.ods.modules.client.bkrportfmgmt.form;
 * @author Hamilton Matos
 */

public class BaseBkrPortfMgmtDetailForm extends BaseForm implements
    CustomerPrvtDetailable
{

  //CNPJ da Corretora.
  private String m_bkrCnpjNbr = "";

  // Código do Cliente na Corretora
  private String m_bkrCustNbr = "";

  // Data e hora da última atualização efetuada pelo usuário
  private String m_lastUpdDate = "";

  // Código do usuário (SOE ID) que efetuou a última atualização no registro
  private String m_lastUpdUserId = "";

  // Número do Cliente no CMS
  private String m_custNbrSrc = "";

  // Nome do Cliente
  private String m_custFullNameTextSrc = "";

  // Dados do grid de corretoras
  private String[][] m_brokerGrid;

  // Dados do grid de corretoras associadas a carteira administrada
  private String[][] m_bkrPortfMgmtGrid;

  // Ítens selecionados no grid de corretoras
  private String[] m_selectedItemsInBrokerGrid;

  // Ítens selecionados no grid de corretoras associadas a carteira administrada
  private String[] m_selectedItemsInBkrPortfMgmtGrid;

  // Ítens marcados para inserção no grid de corretoras associadas a carteira
  // administrada
  private String[] m_insertSelectedItemsInBkrPortfMgmtGrid;

  // Ítens marcados para deleção no grid de corretoras associadas a carteira
  // administrada
  private String[] m_deleteSelectedItemsInBkrPortfMgmtGrid;

  // Ítens marcados para alteração no grid de corretoras associadas a carteira
  // administrada
  private String[] m_updateSelectedItemsInBkrPortfMgmtGrid;

  // Coluna "CNPJ" no grid de corretoras associadas a carteira administrada
  private String[] m_bkrCnpjNbrInBkrPortfMgmtGrid;

  // Coluna "Razão Social da Corretora" no grid de corretoras associadas a
  // carteira administrada
  private String[] m_bkrNameTextInBkrPortfMgmtGrid;

  // Coluna "C/C Bovespa" no grid de corretoras associadas a carteira
  // administrada
  private String[] m_bovespaCurAcctNbrInBkrPortfMgmtGrid;

  // Coluna "CCI Bovespa" no grid de corretoras associadas a carteira
  // administrada
  private String[] m_bovespaInvstAcctNbrInBkrPortfMgmtGrid;

  // Coluna "C/C BMF" no grid de corretoras associadas a carteira administrada
  private String[] m_bmfCurAcctNbrInBkrPortfMgmtGrid;

  // Coluna "CCI BMF" no grid de corretoras associadas a carteira administrada
  private String[] m_bmfInvstAcctNbrInBkrPortfMgmtGrid;

  // CNPJ da Corretora
  private String m_bkrCnpjNbrSrc;

  // Nome Completo da corretora
  private String m_bkrNameTextSrc;

  // Resultado da Consulta de Carteiras Administradas
  private DataSet m_portfolioResults;

  // Código da Conta Produto selecionada
  private String m_selectedProdAcctCode;

  // Código da sub conta produto selecionada
  private String m_selectedProdUnderAcctCode;

  // Conta corrente Bovespa
  private String m_bovespaCurAcctNbr;

  // Conta investimento Bovespa
  private String m_bovespaInvstAcctNbr;

  // Conta corrente BMF
  private String m_bmfCurAcctNbr;

  // Conta investimento BMF
  private String m_bmfInvstAcctNbr;

  // Flag de verificação de registro em movimento para controle de botões
  private String m_confirmAssociationEnabled = "";

  // Flag para controle de botões
  private String m_insertBtnPressed = "";

  // Constantes utilizadas no grid de corretoras
  // CNPJ
  public static final int COL_POS_BKR_CNPJ_NBR = 0;

  // Razão Social da Corretora
  public static final int COL_POS_BKR_NAME_TEXT = 1;

  // Endereço da Corretora
  public static final int COL_POS_BKR_ADDR_TEXT = 2;

  // Constantes utilizadas no grid de associações cliente x corretoras.
  // C/C Bovespa
  public static final int COL_POS_BOVESPA_CUR_ACCT_NBR = 2;

  // CCI Bovespa
  public static final int COL_POS_BOVESPA_INVST_ACCT_NBR = 3;

  // C/C BMF
  public static final int COL_POS_BMF_CUR_ACCT_NBR = 4;

  // CCI BMF
  public static final int COL_POS_BMF_INVST_ACCT_NBR = 5;

  /**
   * @return Returns bkrCnpjNbr.
   */
  public String getBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /**
   * @param bkrCnpjNbr_ Field bkrCnpjNbr to be setted.
   */
  public void setBkrCnpjNbr( String bkrCnpjNbr_ )
  {
    m_bkrCnpjNbr = bkrCnpjNbr_;
  }

  /**
   * @return Returns bkrCustNbr.
   */
  public String getBkrCustNbr()
  {
    return m_bkrCustNbr;
  }

  /**
   * @param bkrCustNbr_ Field bkrCustNbr to be setted.
   */
  public void setBkrCustNbr( String bkrCustNbr_ )
  {
    m_bkrCustNbr = bkrCustNbr_;
  }

  /**
   * @return Returns custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ Field custFullNameTextSrc to be setted.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
  }

  /**
   * @return Returns custNbrSrc.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    //
  }

  /**
   * @return Returns lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns bkrPortfMgmtGrid.
   */
  public String[][] getBkrPortfMgmtGrid()
  {
    return m_bkrPortfMgmtGrid;
  }

  /**
   * @param bkrPortfMgmtGrid_ Field bkrPortfMgmtGrid to be setted.
   */
  public void setBkrPortfMgmtGrid( String[][] bkrPortfMgmtGrid_ )
  {
    m_bkrPortfMgmtGrid = bkrPortfMgmtGrid_;
  }

  /**
   * @return Returns brokerGrid.
   */
  public String[][] getBrokerGrid()
  {
    return m_brokerGrid;
  }

  /**
   * @param brokerGrid_ Field brokerGrid to be setted.
   */
  public void setBrokerGrid( String[][] brokerGrid_ )
  {
    m_brokerGrid = brokerGrid_;
  }

  /**
   * @return Returns selectedItemsInBkrPortfMgmtGrid.
   */
  public String[] getSelectedItemsInBkrPortfMgmtGrid()
  {
    return m_selectedItemsInBkrPortfMgmtGrid;
  }

  /**
   * @param selectedItemsInBkrPortfMgmtGrid_ Field
   *          selectedItemsInBkrPortfMgmtGrid to be setted.
   */
  public void setSelectedItemsInBkrPortfMgmtGrid(
                                                 String[] selectedItemsInBkrPortfMgmtGrid_ )
  {
    m_selectedItemsInBkrPortfMgmtGrid = selectedItemsInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns selectedItemsInBrokerGrid.
   */
  public String[] getSelectedItemsInBrokerGrid()
  {
    return m_selectedItemsInBrokerGrid;
  }

  /**
   * @param selectedItemsInBrokerGrid_ Field selectedItemsInBrokerGrid to be
   *          setted.
   */
  public void setSelectedItemsInBrokerGrid( String[] selectedItemsInBrokerGrid_ )
  {
    m_selectedItemsInBrokerGrid = selectedItemsInBrokerGrid_;
  }

  /**
   * @return Returns bkrCnpjNbrSrc.
   */
  public String getBkrCnpjNbrSrc()
  {
    return m_bkrCnpjNbrSrc;
  }

  /**
   * @param bkrCnpjNbrSrc_ Field bkrCnpjNbrSrc to be setted.
   */
  public void setBkrCnpjNbrSrc( String bkrCnpjNbrSrc_ )
  {
    m_bkrCnpjNbrSrc = removeMask( bkrCnpjNbrSrc_ );
  }

  /**
   * @return Returns bkrNameTextSrc.
   */
  public String getBkrNameTextSrc()
  {
    return m_bkrNameTextSrc;
  }

  /**
   * @param bkrNameTextSrc_ Field bkrNameTextSrc to be setted.
   */
  public void setBkrNameTextSrc( String bkrNameTextSrc_ )
  {
    m_bkrNameTextSrc = bkrNameTextSrc_;
  }

  /**
   * @return Returns portfolioResults.
   */
  public DataSet getPortfolioResults()
  {
    return m_portfolioResults;
  }

  /**
   * @param portfolioResults_ Field portfolioResults to be setted.
   */
  public void setPortfolioResults( DataSet portfolioResults_ )
  {
    m_portfolioResults = portfolioResults_;
  }

  /**
   * @return Returns prodAcctCode.
   */
  public String getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }

  /**
   * @param prodAcctCode_ Field prodAcctCode to be setted.
   */
  public void setSelectedProdAcctCode( String prodAcctCode_ )
  {
    m_selectedProdAcctCode = prodAcctCode_;
  }

  /**
   * @return Returns prodUnderAcctCode.
   */
  public String getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_ Field prodUnderAcctCode to be setted.
   */
  public void setSelectedProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = prodUnderAcctCode_;
  }

  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {
    if ( m_selectedItemsInBrokerGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInBrokerGrid.length; i++ )
      {
        m_selectedItemsInBrokerGrid[ i ] = "N";
      }
    }

    if ( m_selectedItemsInBkrPortfMgmtGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInBkrPortfMgmtGrid.length; i++ )
      {
        m_selectedItemsInBkrPortfMgmtGrid[ i ] = "N";
      }
    }

    if ( m_updateSelectedItemsInBkrPortfMgmtGrid != null )
    {
      for ( int i = 0; i < m_updateSelectedItemsInBkrPortfMgmtGrid.length; i++ )
      {
        m_updateSelectedItemsInBkrPortfMgmtGrid[ i ] = "N";
      }
    }
    if ( m_deleteSelectedItemsInBkrPortfMgmtGrid != null )
    {
      for ( int i = 0; i < m_deleteSelectedItemsInBkrPortfMgmtGrid.length; i++ )
      {
        m_deleteSelectedItemsInBkrPortfMgmtGrid[ i ] = "N";
      }
    }
    if ( m_insertSelectedItemsInBkrPortfMgmtGrid != null )
    {
      for ( int i = 0; i < m_insertSelectedItemsInBkrPortfMgmtGrid.length; i++ )
      {
        m_insertSelectedItemsInBkrPortfMgmtGrid[ i ] = "N";
      }
    }

  }

  /**
   * @return Returns bmfCurAcctNbr.
   */
  public String getBmfCurAcctNbr()
  {
    return m_bmfCurAcctNbr;
  }

  /**
   * @param bmfCurAcctNbr_ Field bmfCurAcctNbr to be setted.
   */
  public void setBmfCurAcctNbr( String bmfCurAcctNbr_ )
  {
    m_bmfCurAcctNbr = bmfCurAcctNbr_;
  }

  /**
   * @return Returns bmfInvstAcctNbr.
   */
  public String getBmfInvstAcctNbr()
  {
    return m_bmfInvstAcctNbr;
  }

  /**
   * @param bmfInvstAcctNbr_ Field bmfInvstAcctNbr to be setted.
   */
  public void setBmfInvstAcctNbr( String bmfInvstAcctNbr_ )
  {
    m_bmfInvstAcctNbr = bmfInvstAcctNbr_;
  }

  /**
   * @return Returns bovespaCurAcctNbr.
   */
  public String getBovespaCurAcctNbr()
  {
    return m_bovespaCurAcctNbr;
  }

  /**
   * @param bovespaCurAcctNbr_ Field bovespaCurAcctNbr to be setted.
   */
  public void setBovespaCurAcctNbr( String bovespaCurAcctNbr_ )
  {
    m_bovespaCurAcctNbr = bovespaCurAcctNbr_;
  }

  /**
   * @return Returns bovespaInvstAcctNbr.
   */
  public String getBovespaInvstAcctNbr()
  {
    return m_bovespaInvstAcctNbr;
  }

  /**
   * @param bovespaInvstAcctNbr_ Field bovespaInvstAcctNbr to be setted.
   */
  public void setBovespaInvstAcctNbr( String bovespaInvstAcctNbr_ )
  {
    m_bovespaInvstAcctNbr = bovespaInvstAcctNbr_;
  }

  /**
   * @return Returns deleteSelectedItemsInBkrPortfMgmtGrid.
   */
  public String[] getDeleteSelectedItemsInBkrPortfMgmtGrid()
  {
    return m_deleteSelectedItemsInBkrPortfMgmtGrid;
  }

  /**
   * @param deleteSelectedItemsInBkrPortfMgmtGrid_ Field
   *          deleteSelectedItemsInBkrPortfMgmtGrid to be setted.
   */
  public void setDeleteSelectedItemsInBkrPortfMgmtGrid(
                                                       String[] deleteSelectedItemsInBkrPortfMgmtGrid_ )
  {
    m_deleteSelectedItemsInBkrPortfMgmtGrid = deleteSelectedItemsInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns insertSelectedItemsInBkrPortfMgmtGrid.
   */
  public String[] getInsertSelectedItemsInBkrPortfMgmtGrid()
  {
    return m_insertSelectedItemsInBkrPortfMgmtGrid;
  }

  /**
   * @param insertSelectedItemsInBkrPortfMgmtGrid_ Field
   *          insertSelectedItemsInBkrPortfMgmtGrid to be setted.
   */
  public void setInsertSelectedItemsInBkrPortfMgmtGrid(
                                                       String[] insertSelectedItemsInBkrPortfMgmtGrid_ )
  {
    m_insertSelectedItemsInBkrPortfMgmtGrid = insertSelectedItemsInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns updateSelectedItemsInBkrPortfMgmtGrid.
   */
  public String[] getUpdateSelectedItemsInBkrPortfMgmtGrid()
  {
    return m_updateSelectedItemsInBkrPortfMgmtGrid;
  }

  /**
   * @param updateSelectedItemsInBkrPortfMgmtGrid_ Field
   *          updateSelectedItemsInBkrPortfMgmtGrid to be setted.
   */
  public void setUpdateSelectedItemsInBkrPortfMgmtGrid(
                                                       String[] updateSelectedItemsInBkrPortfMgmtGrid_ )
  {
    m_updateSelectedItemsInBkrPortfMgmtGrid = updateSelectedItemsInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bmfCurAcctNbrInBkrPortfMgmtGrid.
   */
  public String[] getBmfCurAcctNbrInBkrPortfMgmtGrid()
  {
    return m_bmfCurAcctNbrInBkrPortfMgmtGrid;
  }

  /**
   * @param bmfCurAcctNbrInBkrPortfMgmtGrid_ Field
   *          bmfCurAcctNbrInBkrPortfMgmtGrid to be setted.
   */
  public void setBmfCurAcctNbrInBkrPortfMgmtGrid(
                                                 String[] bmfCurAcctNbrInBkrPortfMgmtGrid_ )
  {
    m_bmfCurAcctNbrInBkrPortfMgmtGrid = bmfCurAcctNbrInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bmfInvstAcctNbrInBkrPortfMgmtGrid.
   */
  public String[] getBmfInvstAcctNbrInBkrPortfMgmtGrid()
  {
    return m_bmfInvstAcctNbrInBkrPortfMgmtGrid;
  }

  /**
   * @param bmfInvstAcctNbrInBkrPortfMgmtGrid_ Field
   *          bmfInvstAcctNbrInBkrPortfMgmtGrid to be setted.
   */
  public void setBmfInvstAcctNbrInBkrPortfMgmtGrid(
                                                   String[] bmfInvstAcctNbrInBkrPortfMgmtGrid_ )
  {
    m_bmfInvstAcctNbrInBkrPortfMgmtGrid = bmfInvstAcctNbrInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bovespaCurAcctNbrInBkrPortfMgmtGrid.
   */
  public String[] getBovespaCurAcctNbrInBkrPortfMgmtGrid()
  {
    return m_bovespaCurAcctNbrInBkrPortfMgmtGrid;
  }

  /**
   * @param bovespaCurAcctNbrInBkrPortfMgmtGrid_ Field
   *          bovespaCurAcctNbrInBkrPortfMgmtGrid to be setted.
   */
  public void setBovespaCurAcctNbrInBkrPortfMgmtGrid(
                                                     String[] bovespaCurAcctNbrInBkrPortfMgmtGrid_ )
  {
    m_bovespaCurAcctNbrInBkrPortfMgmtGrid = bovespaCurAcctNbrInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bovespaInvstAcctNbrInBkrPortfMgmtGrid.
   */
  public String[] getBovespaInvstAcctNbrInBkrPortfMgmtGrid()
  {
    return m_bovespaInvstAcctNbrInBkrPortfMgmtGrid;
  }

  /**
   * @param bovespaInvstAcctNbrInBkrPortfMgmtGrid_ Field
   *          bovespaInvstAcctNbrInBkrPortfMgmtGrid to be setted.
   */
  public void setBovespaInvstAcctNbrInBkrPortfMgmtGrid(
                                                       String[] bovespaInvstAcctNbrInBkrPortfMgmtGrid_ )
  {
    m_bovespaInvstAcctNbrInBkrPortfMgmtGrid = bovespaInvstAcctNbrInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bkrCnpjNbrInBkrPortfMgmtGrid.
   */
  public String[] getBkrCnpjNbrInBkrPortfMgmtGrid()
  {
    return m_bkrCnpjNbrInBkrPortfMgmtGrid;
  }

  /**
   * @param bkrCnpjNbrInBkrPortfMgmtGrid_ Field bkrCnpjNbrInBkrPortfMgmtGrid to
   *          be setted.
   */
  public void setBkrCnpjNbrInBkrPortfMgmtGrid(
                                              String[] bkrCnpjNbrInBkrPortfMgmtGrid_ )
  {
    m_bkrCnpjNbrInBkrPortfMgmtGrid = bkrCnpjNbrInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns bkrNameTextInBkrPortfMgmtGrid.
   */
  public String[] getBkrNameTextInBkrPortfMgmtGrid()
  {
    return m_bkrNameTextInBkrPortfMgmtGrid;
  }

  /**
   * @param bkrNameTextInBkrPortfMgmtGrid_ Field bkrNameTextInBkrPortfMgmtGrid
   *          to be setted.
   */
  public void setBkrNameTextInBkrPortfMgmtGrid(
                                               String[] bkrNameTextInBkrPortfMgmtGrid_ )
  {
    m_bkrNameTextInBkrPortfMgmtGrid = bkrNameTextInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns confirmAssociationEnabled.
   */
  public String getConfirmAssociationEnabled()
  {
    return m_confirmAssociationEnabled;
  }

  /**
   * @param confirmAssociationEnabled_ Field confirmAssociationEnabled to be
   *          setted.
   */
  public void setConfirmAssociationEnabled( String confirmAssociationEnabled_ )
  {
    m_confirmAssociationEnabled = confirmAssociationEnabled_;
  }

  /**
   * @return Returns insertBtnPressed.
   */
  public String getInsertBtnPressed()
  {
    return m_insertBtnPressed;
  }

  /**
   * @param insertBtnPressed_ Field insertBtnPressed to be setted.
   */
  public void setInsertBtnPressed( String insertBtnPressed_ )
  {
    m_insertBtnPressed = insertBtnPressed_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }
}