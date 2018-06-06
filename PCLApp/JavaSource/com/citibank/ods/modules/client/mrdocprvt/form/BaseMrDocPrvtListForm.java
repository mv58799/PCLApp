package com.citibank.ods.modules.client.mrdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.BaseMrDocPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Form Base da Classe MrDocPrvtListView.jsp
 * 
 * @author michele.monteiro,20/04/2007
 *  
 */

public class BaseMrDocPrvtListForm extends BaseForm implements
    CustomerSearchable, MrDocPrvtDetailable
{
	
  private String m_custCurAcctNbrSrc;	
  
  private String m_docTransferCode = "";
  
  private String m_ipDocCode = "";

  // Descrição do documento MR
  private String m_mrDocTextSrc = "";

  //Número da conta corrente
  private String m_curAcctNbrSrc = "";

  //Número do cliente
  private String m_custNbrSrc = "";

  //Codigo do Co-Titular 1
  private String m_custFullName2TextSrc = null;
  
  //Codigo do Co-Titular 2
  private String m_custFullName3TextSrc = null;
  
  //Codigo do Co-Titular 3
  private String m_custFullName4TextSrc = null;
  
  //Nome completo do cliente
  private String m_custFullNameTextSrc = "";

  //Indicador de Conta Investimento - Combo
  private DataSet m_mrInvstCurAcctIndDomain = null;

  //Indicador de Conta Investimento
  private String m_mrInvstCurAcctIndSrc = "";
  
  //Codigo da Conta Investimento
  private String m_invstCurAcctNbrSrc = "";

  //Código da sub_conta do Produto Selecionada no grid
  private String m_selectedProdUnderAcctCode = "";

  //Código da conta produto selecionada no grid
  private String m_selectedProdAcctCode = "";

  //Código do documento MR selecionado.
  private String m_selectedMrDocPrvt = "";

  //Url da tela destino - Implementação botão buscar
  private String m_clickedSearch = "";

  //Resultado da pesquisa.
  private DataSet m_results = null;

  //Número da conta produto
  private String m_prodAcctCodeSrc = "";

  //Número da sub conta produto
  private String m_prodUnderAcctCodeSrc = "";
  
  // CPF/CNPJ do cliente - Critérios de pesquisa
  private String m_custCpfCnpjNbrSrc = null;
  
  private String m_SelectedReltnNbr = "";


  /**
   * @return Retorna o número da conta corrente
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbr_. Seta o número da conta corrente.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Retorna a descrição do documento MR.
   */
  public String getMrDocTextSrc()
  {
    return m_mrDocTextSrc;
  }

  /**
   * @param mrDocTextSrc_. Seta a descrição do documento MR.
   */
  public void setMrDocTextSrc( String mrDocTextSrc_ )
  {
    m_mrDocTextSrc = mrDocTextSrc_;
  }

  /**
   * @return Retorna o número do cliente.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_. Seta o número do cliente.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }

  /**
   * @return Retorna o indicador da conta investimento.
   */
  public String getMrInvstCurAcctIndSrc()
  {
    return m_mrInvstCurAcctIndSrc;
  }

  /**
   * @param mrInvstCurAcctInd_. Seta o indicador da conta investimento.
   */
  public void setMrInvstCurAcctIndSrc( String mrInvstCurAcctInd_ )
  {
    m_mrInvstCurAcctIndSrc = mrInvstCurAcctInd_;
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
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_. Seta o nome completo do cliente.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
  }
  
  /**
   * @return Retorna o nome do Co-Titular 1.
   */
  public String getCustFullName2TextSrc()
  {
	return m_custFullName2TextSrc;
  }

  /**
   * @param custFullNameTextSrc_. Seta oo nome do Co-Titular 1.
   */
  public void setCustFullName2TextSrc( String custFullName2TextSrc_ )
  {
	m_custFullName2TextSrc = custFullName2TextSrc_;
  }
  
  /**
   * @return Retorna o nome do Co-Titular 2.
   */
  public String getCustFullName3TextSrc()
  {
	return m_custFullName3TextSrc;
  }

  /**
   * @param custFullNameTextSrc_. Seta o nome do Co-Titular 2.
   */
  public void setCustFullName3TextSrc( String custFullName3TextSrc_ )
  {
	m_custFullName3TextSrc = custFullName3TextSrc_;
  }
  
  /**
   * @return Retorna o nome do Co-Titular 3.
   */
  public String getCustFullName4TextSrc()
  {
	return m_custFullName4TextSrc;
  }

  /**
   * @param custFullNameTextSrc_. Seta o nome do Co-Titular 3.
   */
  public void setCustFullName4TextSrc( String custFullName4TextSrc_ )
  {
	m_custFullName4TextSrc = custFullName4TextSrc_;
  }

  /**
   * @return Retorna o código do documento MR selecionado no grid.
   */
  public String getSelectedMrDocPrvt()
  {
    return m_selectedMrDocPrvt;
  }

  /**
   * @param selectedMrDocPrvt_. Seta o código do documento MR selecionado.
   */
  public void setSelectedMrDocPrvt( String selectedMrDocPrvt_ )
  {
    m_selectedMrDocPrvt = selectedMrDocPrvt_;
  }

  /**
   * @return Retorna o código da conta produto selecionada.
   */
  public String getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }

  /**
   * @param selectedProdAcctCode_. Seta o código da conta produto selecionada.
   *  
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }

  /**
   * @return Retorna o código da sub-conta produto.
   */
  public String getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }

  /**
   * @param selectedProdUnderAcctCode_. Seta o código da sub-conta produto.
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }

  /**
   * Método da interface CustomerSearchable.
   */
  public String getSelectedCustNbrList()
  {
    return null;
  }

  /**
   * Método da interface CustomerSearchable. Seta o número do cliente
   * selecionado.
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
    setCustNbrSrc( selectedCustNbr_ );
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
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return null;
  }

  /**
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {

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
   * @return Retorna o número da conta produto.
   */
  public String getProdAcctCodeSrc()
  {
    return m_prodAcctCodeSrc;
  }

  /**
   * @param prodAcctCodeSrc_.Seta o número da conta produto.
   */
  public void setProdAcctCodeSrc( String prodAcctCodeSrc_ )
  {
    m_prodAcctCodeSrc = prodAcctCodeSrc_;
  }
  
  /**
   * @return
   */
  public String getCustCpfCnpjNbrSrc() {
	  return m_custCpfCnpjNbrSrc;
  }

  /**
   * @param string
   */
  public void setCustCpfCnpjNbrSrc(String custCpfCnpjNbrSrc_) {
	  m_custCpfCnpjNbrSrc = custCpfCnpjNbrSrc_;
  }
  
  /**
   * @return
   */
  public String getInvstCurAcctNbrSrc() {
	  return m_invstCurAcctNbrSrc;
  }

  /**
   * @param string
   */
  public void setInvstCurAcctNbrSrc(String invstCurAcctNbrSrc_) {
	  m_invstCurAcctNbrSrc = invstCurAcctNbrSrc_;
  }


  /**
   * @return Retorna o número da sub conta produto.
   */
  public String getProdUnderAcctCodeSrc()
  {
    return m_prodUnderAcctCodeSrc;
  }

  /**
   * @param prodUnderAcctCodeSrc_. Seta o número da sub conta produto.
   */
  public void setProdUnderAcctCodeSrc( String prodUnderAcctCodeSrc_ )
  {
    m_prodUnderAcctCodeSrc = prodUnderAcctCodeSrc_;
  }

  /**
   * Método da interface CurAccountSearchable.
   */
  public String getSelectedCurAcctNbrSrc()
  {

    return null;
  }

  /**
   * Método da interface CurAccountSearchable.
   */
  public String getSelectedProdAcctCodeSrc()
  {

    return null;
  }

  /**
   * Método da interface CurAccountSearchable.
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {

    return null;
  }

  /**
   * Método da interface CurAccountSearchable.Seta o número da conta corrente
   * selecionada.
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
    setCurAcctNbrSrc( curAcctNbr_ );
  }

  /**
   * Método da interface CurAccountSearchable.Seta o número da conta produto
   * selecionada.
   */
  public void setSelectedProdAcctCodeSrc( String prodAcctCode_ )
  {
    setProdAcctCodeSrc( prodAcctCode_ );
  }

  /**
   * Método da interface CurAccountSearchable.Seta o número da sub conta produto
   * selecionada.
   */
  public void setSelectedProdUnderAcctCodeSrc( String prodUnderAcctCode_ )
  {
    setProdUnderAcctCodeSrc( prodUnderAcctCode_ );
  }



  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength( BaseMrDocPrvtListFncVO.C_MR_DOC_TEXT,
                                    m_mrDocTextSrc,
                                    BaseTplMrDocPrvtEntity.C_MR_DOC_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseMrDocPrvtListFncVO.C_CUR_ACCT_NBR,
                                     m_curAcctNbrSrc,
                                     BaseTplMrDocPrvtEntity.C_CUR_ACCT_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger( BaseMrDocPrvtListFncVO.C_CUST_NBR,
                                     m_custNbrSrc,
                                     BaseTplMrDocPrvtEntity.C_CUST_NBR_SIZE,
                                     errors );

    return errors;
  }

  public String getReltnNbr() {	
 	return m_SelectedReltnNbr;
  }


  public void setReltnNbr(String reltnNbr_) {
	m_SelectedReltnNbr = reltnNbr_;	
  }
  
  /**
   * @return
   */
  public String getCustCurAcctNbrSrc() {
    return m_custCurAcctNbrSrc;
  }

  /**
   * @param string
   */
  public void setCustCurAcctNbrSrc(String m_custCurAcctNbrSrc_) {
     m_custCurAcctNbrSrc = m_custCurAcctNbrSrc_;
  }


/**
 * @return
 */
  public String getIpDocCode() {
	return m_ipDocCode;
  }

/**
 * @param string
 */
  public void setIpDocCode(String m_ipDocCode_) {
	m_ipDocCode = m_ipDocCode_;
  }

/**
 * @return
 */
  public String getDocTransferCode() {
	return m_docTransferCode;
  }

/**
 * @param string
 */
  public void setDocTransferCode(String m_docTransferCode_) {
	m_docTransferCode = m_docTransferCode_;
  }

}