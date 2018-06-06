package com.citibank.ods.modules.client.customerprvt.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.BaseCustomerPrvtListFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplDetailable;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @see package com.citibank.ods.modules.client.customerPrvt.form;
 * @version 1.0
 * @author l.braga,14/03/2007
 */

public class BaseCustomerPrvtListForm extends BaseForm implements
    CustomerPrvtDetailable, CustomerSearchable, OfficerSearchable, CustomerPrvtCmplDetailable
{
  // Variável de controle de habilitação de botões de dados complementares
  private String cmplDataButtonControl;
  
  // Numero do Cliente
  private String m_custNbrSrc = "";

  // Nome do cliente
  private String m_custFullNameTextSrc = "";
  
  //Nome do Co-Titular 
  private String m_custFullName2TextSrc = "";
  
  //Nome do Co-Titular 2
  private String m_custFullName3TextSrc = "";
  
  //Nome do Co-Titular 3
  private String m_custFullName4TextSrc = "";


  // CFF ou CNPJ do cliente
  private String m_custCpfCnpjNbrSrc = "";

  // Numero da conta corrente
  private String m_curAcctNbrSrc = "";
  
  // Numero da conta Investimento
  private String m_invstCurAcctNbrSrc = "";

  // numero do relacionamento
  private String m_reltnNbrSrc = "";
  
  private String m_SelectedReltnNbr = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  private String m_SelectedCustNbr = "";

  // Variáveis de Dados complementares de Cliente:
  private String m_clickedSearch;

  private String m_emNbrSrc;

  private String m_custTextSrc;

  private String m_prvtCustNbrSrc;

  private String m_prvtKeyNbrSrc;

  private String m_wealthPotnlCodeSrc;

  private String m_offcrNbrSrc;
  
  private String m_classCmplcCodeSrc;
  
  private String m_prvtCustTypeCodeSrc;

  private String m_offcrTextSrc;

  private String m_glbRevenSysOffcrNbrSrc;

  private DataSet m_wealthPotnlCodeDomain;

  private DataSet m_classCmplcCodeDomain;
  
  private DataSet m_prvtCustTypeCodeDomain;
  
  private String m_selectedEmNbr;
  
  //Status do cliente
  private String m_custPrvtStatCodeSrc = "";
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;
  
  //Lista de Customers
  private ArrayList m_custList = null;

  /**
   * @return Returns acctNbrSrc.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param acctNbrSrc_ Field acctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Returns reltnNbrSrc.
   */
  public String getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ Field reltnNbrSrc to be setted.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  /**
   * @return Returns custCpfCnpjNbrSrc.
   */
  public String getCustCpfCnpjNbrSrc()
  {
    return m_custCpfCnpjNbrSrc;
  }

  /**
   * @param custCpfCnpjNbrSrc_ Field custCpfCnpjNbrSrc to be setted.
   */
  public void setCustCpfCnpjNbrSrc( String custCpfCnpjNbrSrc_ )
  {
    m_custCpfCnpjNbrSrc = removeMask( custCpfCnpjNbrSrc_ );
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
   * @return Returns selectedCustomerPrvt.
   */

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
    return m_SelectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    setCustNbrSrc( selectedCustNbr_ );
  }

  public String getSelectedCustNbrList()
  {
    return m_SelectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
    setCustNbrSrc( selectedCustNbr_ );
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtListFncVO.C_CUST_NBR_DESCRIPTION,
                                     m_custNbrSrc,
                                     BaseTplCustomerPrvtEntity.C_CUST_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseCustomerPrvtListFncVO.C_CUST_FULL_NAME_TEXT_DESCRIPTION,
                                    m_custFullNameTextSrc,
                                    BaseTplCustomerPrvtEntity.C_CUST_FULL_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtListFncVO.C_CUST_CPF_CNPJ_NBR_DESCRIPTION,
                                     m_custCpfCnpjNbrSrc,
                                     BaseTplCustomerPrvtEntity.C_CUST_CPF_CNPJ_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtListFncVO.C_CUR_ACCT_NBR_DESCRIPTION,
                                     m_curAcctNbrSrc,
                                     BaseTplCustomerPrvtEntity.C_CUR_ACCT_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtListFncVO.C_RELTN_NBR_DESCRIPTION,
                                     m_reltnNbrSrc,
                                     BaseTplCustomerPrvtEntity.C_RELTN_NBR_SIZE,
                                     errors );

    return errors;
  }

  /**
   * @return Returns custNbrSearch.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSearch_ Field custNbrSearch to be setted.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }
  
  

  // Getters e Setters de Dados complementares de Cliente:

  
  //Alteraçao GPTI
	
  /**
    * @return Returns PrvtCustTypeCodeSrc.
    */
  public String getPrvtCustTypeCodeSrc()
  {
    return m_prvtCustTypeCodeSrc;
  }

  /**
   * @param PrvtCustTypeCodeSrc_ Field PrvtCustTypeCodeSrc to be setted.
   */
  public void setPrvtCustTypeCodeSrc( String prvtCustTypeCodeSrc_ )
  {
    m_prvtCustTypeCodeSrc = prvtCustTypeCodeSrc_;
  }

  /**
   * @return Returns prvtCustTypeCodeDomain.
   */
  public DataSet getPrvtCustTypeCodeDomain()
  {
    return m_prvtCustTypeCodeDomain;
  }

  /**
   * @param prvtCustTypeCodeDomain_ Field prvtCustTypeCodeDomain to be setted.
   */
  public void setPrvtCustTypeCodeDomain( DataSet prvtCustTypeCodeDomain_ )
  {
    m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
  }
  //Fim Alteração GPTI
  
  /**
   * @return Returns classCmplcCodeDomain.
   */
  public DataSet getClassCmplcCodeDomain()
  {
    return m_classCmplcCodeDomain;
  }

  /**
   * @param classCmplcCodeDomain_ Field classCmplcCodeDomain to be setted.
   */
  public void setClassCmplcCodeDomain( DataSet classCmplcCodeDomain_ )
  {
    m_classCmplcCodeDomain = classCmplcCodeDomain_;
  }

  /**
   * @return Returns classCmplcCodeSrc.
   */
  public String getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ Field classCmplcCodeSrc to be setted.
   */
  public void setClassCmplcCodeSrc( String classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

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
   * @return Returns custTextSrc.
   */
  public String getCustTextSrc()
  {
    return m_custTextSrc;
  }

  /**
   * @param custTextSrc_ Field custTextSrc to be setted.
   */
  public void setCustTextSrc( String custTextSrc_ )
  {
    m_custTextSrc = custTextSrc_;
  }

  /**
   * @return Returns emNbrSrc.
   */
  public String getEmNbrSrc()
  {
    return m_emNbrSrc;
  }

  /**
   * @param emNbrSrc_ Field emNbrSrc to be setted.
   */
  public void setEmNbrSrc( String emNbrSrc_ )
  {
    m_emNbrSrc = emNbrSrc_;
  }

  /**
   * @return Returns glbRevenSysOffcrNbrSrc.
   */
  public String getGlbRevenSysOffcrNbrSrc()
  {
    return m_glbRevenSysOffcrNbrSrc;
  }

  /**
   * @param glbRevenSysOffcrNbrSrc_ Field glbRevenSysOffcrNbrSrc to be setted.
   */
  public void setGlbRevenSysOffcrNbrSrc( String glbRevenSysOffcrNbrSrc_ )
  {
    m_glbRevenSysOffcrNbrSrc = glbRevenSysOffcrNbrSrc_;
  }

  /**
   * @return Returns offcrNbrSrc.
   */
  public String getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_ Field offcrNbrSrc to be setted.
   */
  public void setOffcrNbrSrc( String offcrNbrSrc_ )
  {
    m_offcrNbrSrc = offcrNbrSrc_;
  }

  /**
   * @return Returns offcrTextSrc.
   */
  public String getOffcrTextSrc()
  {
    return m_offcrTextSrc;
  }

  /**
   * @param offcrTextSrc_ Field offcrTextSrc to be setted.
   */
  public void setOffcrTextSrc( String offcrTextSrc_ )
  {
    m_offcrTextSrc = offcrTextSrc_;
  }

  /**
   * @return Returns prvtCustNbrSrc.
   */
  public String getPrvtCustNbrSrc()
  {
    return m_prvtCustNbrSrc;
  }

  /**
   * @param prvtCustNbrSrc_ Field prvtCustNbrSrc to be setted.
   */
  public void setPrvtCustNbrSrc( String prvtCustNbrSrc_ )
  {
    m_prvtCustNbrSrc = prvtCustNbrSrc_;
  }

  /**
   * @return Returns prvtKeyNbrSrc.
   */
  public String getPrvtKeyNbrSrc()
  {
    return m_prvtKeyNbrSrc;
  }

  /**
   * @param prvtKeyNbrSrc_ Field prvtKeyNbrSrc to be setted.
   */
  public void setPrvtKeyNbrSrc( String prvtKeyNbrSrc_ )
  {
    m_prvtKeyNbrSrc = prvtKeyNbrSrc_;
  }

  /**
   * @return Returns selectedEmNbr.
   */
  public String getSelectedEmNbr()
  {
    return m_selectedEmNbr;
  }

  /**
   * @param selectedEmNbr_ Field selectedEmNbr to be setted.
   */
  public void setSelectedEmNbr( String selectedEmNbr_ )
  {
    m_selectedEmNbr = selectedEmNbr_;
  }

  /**
   * @return Returns wealthPotnlCodeDomain.
   */
  public DataSet getWealthPotnlCodeDomain()
  {
    return m_wealthPotnlCodeDomain;
  }

  /**
   * @param wealthPotnlCodeDomain_ Field wealthPotnlCodeDomain to be setted.
   */
  public void setWealthPotnlCodeDomain( DataSet wealthPotnlCodeDomain_ )
  {
    m_wealthPotnlCodeDomain = wealthPotnlCodeDomain_;
  }

  /**
   * @return Returns wealthPotnlCodeSrc.
   */
  public String getWealthPotnlCodeSrc()
  {
    return m_wealthPotnlCodeSrc;
  }

  /**
   * @param wealthPotnlCodeSrc_ Field wealthPotnlCodeSrc to be setted.
   */
  public void setWealthPotnlCodeSrc( String wealthPotnlCodeSrc_ )
  {
    m_wealthPotnlCodeSrc = wealthPotnlCodeSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#getSelectedOffcrNbr()
   */
  public String getSelectedOffcrNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#setSelectedOffcrNbr(java.lang.String)
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    this.setOffcrNbrSrc( selectedOffcrNbr_ );
  }

  /**
   * @return Returns cmplDataButtonControl.
   */
  public String getCmplDataButtonControl()
  {
    return cmplDataButtonControl;
  }
  /**
   * @param cmplDataButtonControl_ Field cmplDataButtonControl to be setted.
   */
  public void setCmplDataButtonControl( String cmplDataButtonControl_ )
  {
    cmplDataButtonControl = cmplDataButtonControl_;
  }
  
  
  /**
   * @return Returns custPrvtStatCodeDomain.
   */
  public DataSet getCustPrvtStatCodeDomain()
  {
    return m_custPrvtStatCodeDomain;
  }
  /**
   * @param custPrvtStatCodeDomain_ Field custPrvtStatCodeDomain to be setted.
   */
  public void setCustPrvtStatCodeDomain( DataSet custPrvtStatCodeDomain_ )
  {
    m_custPrvtStatCodeDomain = custPrvtStatCodeDomain_;
  }
  /**
   * @return Returns custPrvtStatCodeSrc.
   */
  public String getCustPrvtStatCodeSrc()
  {
    return m_custPrvtStatCodeSrc;
  }
  /**
   * @param custPrvtStatCodeSrc_ Field custPrvtStatCodeSrc to be setted.
   */
  public void setCustPrvtStatCodeSrc( String custPrvtStatCodeSrc_ )
  {
    m_custPrvtStatCodeSrc = custPrvtStatCodeSrc_;
  }
//INICIO======================Alteraçao G&P	
  //Tag para indicar se o cliente mudou e consequentemente limpar a tela
  private boolean clrScreen;
 
  /**
   * @return clrScreen
   */
  public boolean getClrScreen() {
	return clrScreen;
  }

  /**
   * @param clrScreenParm
   */
  public void setClrScreen(boolean clrScreenParm) {
	clrScreen = clrScreenParm;
  }

//FIM=========================Alteraçao G&P
  /**
 * @return
 */
  public ArrayList getCustList() {
	return m_custList;
  }

/**
 * @param list
 */
  public void setCustList(ArrayList m_custList_) {
	m_custList = m_custList_;
  }
  
   /**
	* @return
	*/
 public String getCustFullName2TextSrc() {
   return m_custFullName2TextSrc;
 }

   /**
	* @return
	*/
 public String getCustFullName3TextSrc() {
   return m_custFullName3TextSrc;
 }
	
   /**
	* @return
	*/
 public String getCustFullName4TextSrc() {
   return m_custFullName4TextSrc;
}

   /**
	* @param string
	*/
 public void setCustFullName2TextSrc(String custFullName2TextSrc_) {
   m_custFullName2TextSrc = custFullName2TextSrc_;
 }
	
   /**
	* @param string
	*/
 public void setCustFullName3TextSrc(String custFullName3TextSrc_) {
   m_custFullName3TextSrc = custFullName3TextSrc_;
 }

   /**
	* @param string
	*/
 public void setCustFullName4TextSrc(String custFullName4TextSrc_) {
   m_custFullName4TextSrc = custFullName4TextSrc_;
 }

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getReltnNbr()
 */
  public String getReltnNbr() {	
	return m_SelectedReltnNbr;
  }

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setReltnNbr(java.lang.String)
 */
  public void setReltnNbr(String reltnNbr_) {
	m_SelectedReltnNbr = reltnNbr_;	
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

}