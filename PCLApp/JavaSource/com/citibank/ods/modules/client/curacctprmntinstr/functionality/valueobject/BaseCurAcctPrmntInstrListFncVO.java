package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package
 *      com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 */

public class BaseCurAcctPrmntInstrListFncVO extends BaseODSFncVO
{
  
  // Data Set para popular o grid.
  private DataSet m_results = null;
    
  //Variável de controle - Buscar
  private boolean m_isFromSearch = false;

  //Tela destino do botão buscar
  private String m_clickedSearch = "";

  //Número do cliente
  private BigInteger m_custNbrSrc = null;

  //Código da instrução permanente
  private BigInteger m_prmntInstrCodeSrc = null;

  //Indicador de Conta CCI - Combo.
  private DataSet m_prmntInstrInvstCurAcctIndDomain = null;

  //Indicador de Conta CCI.
  private String m_prmntInstrInvstCurAcctIndSrc = "";

  //Número da conta
  private BigInteger m_curAcctNbrSrc = null;

  // Codigo da Conta Produto. Informacao gerada pela ODS Private para
  // identificar uma operacao ou um contrato
  private BigInteger m_selectedProdAcctCode = null;

  // Codigo da sub conta produto. Codigo gerado pela ODS para identificar
  // produtos que tenham subcontas ou subcontratos
  private BigInteger m_selectedProdUnderAcctCode = null;

  //Número do cliente selecionado no grid
  private BigInteger m_selectedCustNbr = null;

  //Código da instrução permanente selecionada no grid.
  private BigInteger m_selectedPrmntInstrCode = null;

  //Nome do cliente
  private String m_custFullNameSrc = "";
  
  //Descrição da instrução permanente
  private String m_prmntInstrTextSrc = "";

 

  /**
   * @return Returns clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }
  /**
   * @param clickedSearch_ Field clickedSearch to be setted.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
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
   * @return Returns custFullNameSrc.
   */
  public String getCustFullNameSrc()
  {
    return m_custFullNameSrc;
  }
  /**
   * @param custFullNameSrc_ Field custFullNameSrc to be setted.
   */
  public void setCustFullNameSrc( String custFullNameSrc_ )
  {
    m_custFullNameSrc = custFullNameSrc_;
  }
  /**
   * @return Returns custNbrSrc.
   */
  public BigInteger getCustNbrSrc()
  {
    return m_custNbrSrc;
  }
  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( BigInteger custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }
  /**
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
    return m_isFromSearch;
  }
  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    m_isFromSearch = isFromSearch_;
  }
  /**
   * @return Returns prmntInstrCodeSrc.
   */
  public BigInteger getPrmntInstrCodeSrc()
  {
    return m_prmntInstrCodeSrc;
  }
  /**
   * @param prmntInstrCodeSrc_ Field prmntInstrCodeSrc to be setted.
   */
  public void setPrmntInstrCodeSrc( BigInteger prmntInstrCodeSrc_ )
  {
    m_prmntInstrCodeSrc = prmntInstrCodeSrc_;
  }
  /**
   * @return Returns prmntInstrInvstCurAcctIndDomain.
   */
  public DataSet getPrmntInstrInvstCurAcctIndDomain()
  {
    return m_prmntInstrInvstCurAcctIndDomain;
  }
  /**
   * @param prmntInstrInvstCurAcctIndDomain_ Field prmntInstrInvstCurAcctIndDomain to be setted.
   */
  public void setPrmntInstrInvstCurAcctIndDomain(
                                                 DataSet prmntInstrInvstCurAcctIndDomain_ )
  {
    m_prmntInstrInvstCurAcctIndDomain = prmntInstrInvstCurAcctIndDomain_;
  }
  /**
   * @return Returns prmntInstrInvstCurAcctIndSrc.
   */
  public String getPrmntInstrInvstCurAcctIndSrc()
  {
    return m_prmntInstrInvstCurAcctIndSrc;
  }
  /**
   * @param prmntInstrInvstCurAcctIndSrc_ Field prmntInstrInvstCurAcctIndSrc to be setted.
   */
  public void setPrmntInstrInvstCurAcctIndSrc(
                                              String prmntInstrInvstCurAcctIndSrc_ )
  {
    m_prmntInstrInvstCurAcctIndSrc = prmntInstrInvstCurAcctIndSrc_;
  }
  /**
   * @return Returns selectedCustNbr.
   */
  public BigInteger getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }
  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( BigInteger selectedCustNbr_ )
  {
    m_selectedCustNbr = selectedCustNbr_;
  }
  /**
   * @return Returns selectedPrmntInstrCode.
   */
  public BigInteger getSelectedPrmntInstrCode()
  {
    return m_selectedPrmntInstrCode;
  }
  /**
   * @param selectedPrmntInstrCode_ Field selectedPrmntInstrCode to be setted.
   */
  public void setSelectedPrmntInstrCode( BigInteger selectedPrmntInstrCode_ )
  {
    m_selectedPrmntInstrCode = selectedPrmntInstrCode_;
  }
  /**
   * @return Returns selectedProdAcctCode.
   */
  public BigInteger getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }
  /**
   * @param selectedProdAcctCode_ Field selectedProdAcctCode to be setted.
   */
  public void setSelectedProdAcctCode( BigInteger selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }
  /**
   * @return Returns selectedProdUnderAcctCode.
   */
  public BigInteger getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }
  /**
   * @param selectedProdUnderAcctCode_ Field selectedProdUnderAcctCode to be setted.
   */
  public void setSelectedProdUnderAcctCode(
                                           BigInteger selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }
  
  
  /**
   * @return Returns prmntInstrTextSrc.
   */
  public String getPrmntInstrTextSrc()
  {
    return m_prmntInstrTextSrc;
  }
  /**
   * @param prmntInstrTextSrc_ Field prmntInstrTextSrc to be setted.
   */
  public void setPrmntInstrTextSrc( String prmntInstrTextSrc_ )
  {
    m_prmntInstrTextSrc = prmntInstrTextSrc_;
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
}