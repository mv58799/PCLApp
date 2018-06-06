package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author m.nakamura
 * 
 * Agregador dos campos necessários para realizar a consulta em lista de memória
 * de risco.
 */
public class BaseMrDocPrvtListFncVO extends BaseODSFncVO
{
  // Descrição do documento MR
  private String m_mrDocText = "";

  // Número da conta corrente
  private BigInteger m_curAcctNbr = null;

  // Número do cliente
  private BigInteger m_custNbr = null;

  // Nome completo do cliente
  private String m_custFullNameText = "";

  // Indicador de Conta Investimento - Combo
  private DataSet m_mrInvstCurAcctIndDomain = null;

  // Indicador de Conta Investimento
  private String m_mrInvstCurAcctInd = "";
  
  // Código da sub_conta do Produto Selecionada no grid
  private BigInteger m_selectedProdUnderAcctCode = null;

  // Código da conta produto selecionada no grid
  private BigInteger m_selectedProdAcctCode = null;

  // Código do documento MR selecionado.
  private BigInteger m_selectedMrDocPrvt = null;

  // Resultado da pesquisa.
  private DataSet m_results = null;

  //Url da tela destino - Implementação botão buscar
  private String m_clickedSearch = "";

  //Constante da descrição do memo de risco
  public static final String C_MR_DOC_TEXT = "Descrição do Documento MR";
  
  //Constante do Nome do Cliente
  public static final String C_CUST_FULL_NAME_TEXT = "Nome do Cliente";
  
  //Constante do Nome do Co-Titular
  public static final String C_FULL_NAME_TEXT = "Nome do Co-Titular";

  //Constante do número do cliente
  public static final String C_CUST_NBR = "Número do Cliente";

  //Constante da conta corrente
  public static final String C_CUR_ACCT_NBR = "Conta Corrente";

  private boolean isFromSearch = false;

  // Número do cliente - Critérios de pesquisa
  private BigInteger m_custNbrFromList = null;
  
  // CPF/CNPJ do cliente - Critérios de pesquisa
  private BigInteger m_custCpfCnpjNbr = null;

  // Nome completo do cliente - Critérios de pesquisa
  private String m_custFullNameTextFromList = "";
  
 //Numero de Conta Investimento - Critérios de pesquisa
 private String m_invstCurAcctNbr = null;
 
 //Codigo do Co-Titular 1
 private String m_custFullName2Text = "";
 
 //Codigo do Co-Titular 2
 private String m_custFullName3Text = "";
	
 //Codigo do Co-Titular 3
 private String m_custFullName4Text = "";
 
 private BigInteger m_SelectedReltnNbr = null;

  /**
   * @return Retorna o número da conta corrente
   */
  public BigInteger getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_. Seta o número da conta corrente.
   */
  public void setCurAcctNbr( BigInteger curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * @return Retorna a descrição do documento MR.
   */
  public String getMrDocText()
  {
    return m_mrDocText;
  }

  /**
   * @param mrDocText_. Seta a descrição do documento MR.
   */
  public void setMrDocText( String mrDocText_ )
  {
    m_mrDocText = mrDocText_;
  }

  /**
   * @return Retorna o número do cliente.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_. Seta o número do cliente.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * @return Retorna o indicador da conta investimento.
   */
  public String getMrInvstCurAcctInd()
  {
    return m_mrInvstCurAcctInd;
  }

  /**
   * @param mrInvstCurAcctInd_. Seta o indicador da conta investimento.
   */
  public void setMrInvstCurAcctInd( String mrInvstCurAcctInd_ )
  {
    m_mrInvstCurAcctInd = mrInvstCurAcctInd_;
  }

  /**
   * @return Retorna as opções do indicador de investimennto.
   */
  public DataSet getMrInvstCurAcctIndDomain()
  {
    return m_mrInvstCurAcctIndDomain;
  }

  /**
   * @param mrInvstCurAcctIndDomain_.Seta no combo os indicadores de
   *          investimento.
   */
  public void setMrInvstCurAcctIndDomain( DataSet mrInvstCurAcctIndDomain_ )
  {
    m_mrInvstCurAcctIndDomain = mrInvstCurAcctIndDomain_;
  }

  /**
   * @return Retorna o nome completo do cliente
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_. Seta o nome completo do cliente.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Retorna o código do documento MR selecionado no grid.
   */
  public BigInteger getSelectedMrDocPrvt()
  {
    return m_selectedMrDocPrvt;
  }

  /**
   * @param selectedMrDocPrvt_. Seta o código do documento MR selecionado.
   */
  public void setSelectedMrDocPrvt( BigInteger selectedMrDocPrvt_ )
  {
    m_selectedMrDocPrvt = selectedMrDocPrvt_;
  }

  /**
   * @return Retorna o código da conta produto selecionada.
   */
  public BigInteger getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }

  /**
   * @param selectedProdAcctCode_. Seta o código da conta produto selecionada.
   *  
   */
  public void setSelectedProdAcctCode( BigInteger selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }

  /**
   * @return Retorna o código da sub-conta produto.
   */
  public BigInteger getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }

  /**
   * @param selectedProdUnderAcctCode_. Seta o código da sub-conta produto.
   */
  public void setSelectedProdUnderAcctCode(
                                           BigInteger selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }

  /**
   * Recupera o resultado da pesquisa
   * 
   * @return DataSet - DataSet com o resultado da pesquisa.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * Seta o resultado da pesquisa.
   * 
   * @param results_ - DataSet com o resultado da pesquisa.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Retorna a action destino do botão buscar.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_. Seta a url destino do botão buscar.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }

  public String getCustFullNameTextFromList()
  {
    return m_custFullNameTextFromList;
  }

  public void setCustFullNameTextFromList( String custFullNameTextFromList_ )
  {
    m_custFullNameTextFromList = custFullNameTextFromList_;
  }

  public BigInteger getCustNbrFromList()
  {
    return m_custNbrFromList;
  }

  public void setCustNbrFromList( BigInteger custNbrFromList_ )
  {
    m_custNbrFromList = custNbrFromList_;
  }
	/**
	 * @return
	 */
  public String getInvstCurAcctNbr() {
	return m_invstCurAcctNbr;
  }
	
	/**
	 * @param String
	 */
  public void setInvstCurAcctNbr(String invstCurAcctNbr_) {
		m_invstCurAcctNbr = invstCurAcctNbr_;
  }
	
	/**
	 * @return
	 */
  public BigInteger getCustCpfCnpjNbr() {
	return m_custCpfCnpjNbr;
  }
	
	/**
	 * @param integer
	 */
  public void setCustCpfCnpjNbr(BigInteger custCpfCnpjNbr_) {
 	m_custCpfCnpjNbr = custCpfCnpjNbr_;
  }

	/**
	 * @return
	 */
	public String getCustFullName2Text() {
		return m_custFullName2Text;
	}
	
	/**
	 * @return
	 */
	public String getCustFullName3Text() {
		return m_custFullName3Text;
	}
	
	/**
	 * @return
	 */
	public String getCustFullName4Text() {
		return m_custFullName4Text;
	}
	
	/**
	 * @param string
	 */
	public void setCustFullName2Text(String custFullName2Text_) {
		m_custFullName2Text = custFullName2Text_;
	}
	
	/**
	 * @param string
	 */
	public void setCustFullName3Text(String custFullName3Text_) {
		m_custFullName3Text = custFullName3Text_;
	}
	
	/**
	 * @param string
	 */
	public void setCustFullName4Text(String custFullName4Text_) {
		m_custFullName4Text = custFullName4Text_;
	}
	
	public BigInteger getReltnNbr() {	
		return m_SelectedReltnNbr;
	  }


	  public void setReltnNbr(BigInteger reltnNbr_) {
		m_SelectedReltnNbr = reltnNbr_;	
	  }

}