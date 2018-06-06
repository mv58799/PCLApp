package com.citibank.ods.modules.client.custaddress.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
/**
 * @author hamilton.matos
 *  
 */
public abstract class BaseCustAddressListFncVO extends BaseODSFncVO
{

  // Resultado da consulta
  private DataSet m_results;

  // Numero do Cliente
  private BigInteger custNbrSrc = null;

  // CFF ou CNPJ do cliente
  private BigInteger custCpfCnpjNbrSrc = null;

  // Nome do cliente
  private String custFullNameTextSrc = null;

  // Varialvel de controle.
  private String selectedCustNbr = null;

  //Varialvel de controle.
  private String selectedAddrSeqNbr = null;

  /**
   * Constante do Numero do Cliente
   */
  public static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente";

  /**
   * Constante do Nome do cliente
   */
  public static final String C_CUST_FULL_NAME_TEXT_DESCRIPTION = "Nome do cliente";

  /**
   * Constante do CFF ou CNPJ do cliente
   */
  public static final String C_CUST_CPF_CNPJ_NBR_DESCRIPTION = "CFF ou CNPJ do cliente";

  /**
   * @return Returns custCpfCnpjNbrSrc.
   */
  public BigInteger getCustCpfCnpjNbrSrc()
  {
    return custCpfCnpjNbrSrc;
  }

  /**
   * @param custCpfCnpjNbrSrc_ Field custCpfCnpjNbrSrc to be setted.
   */
  public void setCustCpfCnpjNbrSrc( BigInteger custCpfCnpjNbrSrc_ )
  {
    custCpfCnpjNbrSrc = custCpfCnpjNbrSrc_;
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
  public BigInteger getCustNbrSrc()
  {
    return custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( BigInteger custNbrSrc_ )
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
   * @return Returns selectedAddrSeqNbr.
   */
  public String getSelectedAddrSeqNbr()
  {
    return selectedAddrSeqNbr;
  }

  /**
   * @param selectedAddrSeqNbr_ Field selectedAddrSeqNbr to be setted.
   */
  public void setSelectedAddrSeqNbr( String selectedAddrSeqNbr_ )
  {
    selectedAddrSeqNbr = selectedAddrSeqNbr_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
    return selectedCustNbr;
  }

  /**
   * @param selectedCustNumber_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    selectedCustNbr = selectedCustNbr_;    
  }

}

