package com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementListFncVO extends BaseBkrPortfMgmtListFncVO
{

  // Número do cliente
  private String m_custNbrSrc;

  // Nome completo do cliente
  private String m_custFullNameTextSrc;

  // Código do produto
  private String m_prodCodeSrc;

  // Mnemônico do cliente
  private String m_custMnmcNameTextSrc;

  // Nome da Carteira Administrada
  private String m_portfMgmtProdNameSrc;

  // Número da conta corrente
  private BigInteger m_curAcctNbrSrc;

  // Resultado da Consulta
  private DataSet m_results;

  // Último usuário alteração
  private String m_lastUpdUserIdSrc;

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
   * @return Returns custMnmcNameTextSrc.
   */
  public String getCustMnmcNameTextSrc()
  {
    return m_custMnmcNameTextSrc;
  }

  /**
   * @param custMnmcNameTextSrc_ Field custMnmcNameTextSrc to be setted.
   */
  public void setCustMnmcNameTextSrc( String custMnmcNameTextSrc_ )
  {
    m_custMnmcNameTextSrc = custMnmcNameTextSrc_;
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
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Returns curAcctNbrSrc.
   */
  public BigInteger getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbrSrc_ Field curAcctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( BigInteger curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
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
}