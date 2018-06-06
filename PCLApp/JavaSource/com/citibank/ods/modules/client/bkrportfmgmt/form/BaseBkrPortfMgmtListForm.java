package com.citibank.ods.modules.client.bkrportfmgmt.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

/**
 * @see package com.citibank.ods.modules.client.bkrportfmgmt.form;
 * @author Hamilton Matos
 */

public class BaseBkrPortfMgmtListForm extends BaseForm
{

  // Dados de Corretora - CNPJ
  private String m_bkrCnpjNbrSrc;

  // Dados de Corretora - Razão Social
  private String m_bkrNameTextSrc;

  // Dados de Cliente - Número do Cliente
  private String m_custNbrSrc;

  // Dados de Cliente - Nome
  private String m_custFullNameTextSrc;

  // Resultado da Consulta
  private DataSet m_results;

  // Número da conta corrente
  private String m_curAcctNbrSrc;

  // Mnemônico do cliente
  private String m_custMnmcNameSrc;

  // Código do produto
  private String m_prodCodeSrc;

  // Nome da Carteira Administrada
  private String m_portfMgmtProdNameSrc;

  // Cnpj da corretora selecionada
  private String m_selectedBkrCnpjNbr;

  // Número do cliente selecionado
  private String m_selectedCustNbr;

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
    m_custNbrSrc = custNbrSrc_;
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
    m_bkrCnpjNbrSrc = bkrCnpjNbrSrc_;
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
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns curAcctNbrSrc.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbrSrc_ Field curAcctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Returns custMnmcNameSrc.
   */
  public String getCustMnmcNameSrc()
  {
    return m_custMnmcNameSrc;
  }

  /**
   * @param custMnmcNameSrc_ Field custMnmcNameSrc to be setted.
   */
  public void setCustMnmcNameSrc( String custMnmcNameSrc_ )
  {
    m_custMnmcNameSrc = custMnmcNameSrc_;
  }

  /**
   * @return Returns prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }

  /**
   * @param prodCodeSrc_ Field prodCodeSrc to be setted.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }

  /**
   * @return Returns portfMgmtProdNameSrc.
   */
  public String getPortfMgmtProdNameSrc()
  {
    return m_portfMgmtProdNameSrc;
  }

  /**
   * @param portfMgmtProdNameSrc_ Field portfMgmtProdNameSrc to be setted.
   */
  public void setPortfMgmtProdNameSrc( String portfMgmtProdNameSrc_ )
  {
    m_portfMgmtProdNameSrc = portfMgmtProdNameSrc_;
  }

  /**
   * @return Returns selectedBkrCnpjNbr.
   */
  public String getSelectedBkrCnpjNbr()
  {
    return m_selectedBkrCnpjNbr;
  }

  /**
   * @param selectedBkrCnpjNbr_ Field selectedBkrCnpjNbr to be setted.
   */
  public void setSelectedBkrCnpjNbr( String selectedBkrCnpjNbr_ )
  {
    m_selectedBkrCnpjNbr = selectedBkrCnpjNbr_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    m_selectedCustNbr = selectedCustNbr_;
  }
}