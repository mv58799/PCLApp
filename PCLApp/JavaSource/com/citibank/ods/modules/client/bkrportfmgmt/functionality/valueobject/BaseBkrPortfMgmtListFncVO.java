package com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author Hamilton Matos
 */

public abstract class BaseBkrPortfMgmtListFncVO extends BaseODSFncVO
{

  // Dados de Cliente - Número do Cliente
  private String custNbrSrc;

  // Dados de Cliente - Nome
  private String custFullNameTextSrc;

  // Dados de Corretora - CNPJ
  private String bkrCnpjNbrSrc;

  // Dados de Corretora - Razão Social
  private String bkrNameTextSrc;

  // Resultado da Consulta
  private DataSet m_results;

  // Conta corrente
  private BigInteger m_curAcctNbrSrc;

  // Mnemônico
  private String m_custMnmcNameSrc;

  // Nome da carteira
  private String m_portfMgmtProdNameSrc;

  // Código do produto
  private String m_prodCode;

  /**
   * @return Returns bkrCnpjNbrSrc.
   */
  public String getBkrCnpjNbrSrc()
  {
    return bkrCnpjNbrSrc;
  }

  /**
   * @param bkrCnpjNbrSrc_ Field bkrCnpjNbrSrc to be setted.
   */
  public void setBkrCnpjNbrSrc( String bkrCnpjNbrSrc_ )
  {
    bkrCnpjNbrSrc = bkrCnpjNbrSrc_;
  }

  /**
   * @return Returns bkrNameTextSrc.
   */
  public String getBkrNameTextSrc()
  {
    return bkrNameTextSrc;
  }

  /**
   * @param bkrNameTextSrc_ Field bkrNameTextSrc to be setted.
   */
  public void setBkrNameTextSrc( String bkrNameTextSrc_ )
  {
    bkrNameTextSrc = bkrNameTextSrc_;
  }

  /**
   * @return Returns custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ Field custFullNameTextSrc to be setted.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    custFullNameTextSrc = custFullNameTextSrc_;
  }

  /**
   * @return Returns custNbrSrc.
   */
  public String getCustNbrSrc()
  {
    return custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    custNbrSrc = custNbrSrc_;
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
   * @return Returns prodCode.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * @param prodCode_ Field prodCode to be setted.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }
}