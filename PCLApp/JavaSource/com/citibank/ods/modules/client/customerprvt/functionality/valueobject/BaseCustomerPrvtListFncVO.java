package com.citibank.ods.modules.client.customerprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.customerPrvt.functionality.valueObject;
 * @version 1.0
 * @author l.braga,13/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseCustomerPrvtListFncVO extends BaseFncVO
{
  // Numero do Cliente
  private BigInteger m_custNbr = null;

  // Nome do cliente
  private String m_custFullNameText = "";
  
  //Nome do Co-Titular 
  private String m_custFullName2Text = "";
  
  //Nome do Co-Titular 2
  private String m_custFullName3Text = "";
  
  //Nome do Co-Titular 3
  private String m_custFullName4Text = "";

  // CFF ou CNPJ do cliente
  private BigInteger m_custCpfCnpjNbr = null;

  //Numero da Conta Investimento
  private String m_invstCurAcctNbrSrc = null;
  
  // Numero da conta corrente
  private BigInteger m_curAcctNbrSrc = null;

  // Numero do relacionamento
  private BigInteger m_reltnNbrSrc = null;

  // Indicador de navegação de busca
  private boolean m_isFromSearch = false;

  // DataSet como os resultados do banco.
  private DataSet m_results;

  // Informações referentes a dados complementares de cliente

  private String m_clickedSearch;

  private DataSet m_wealthPotnlCodeDomain;

  private DataSet m_classCmplcCodeDomain;
  
  private DataSet m_prvtCustTypeCodeDomain;
  
  private DataSet m_offcrNbrDomain;

  private String m_custTextSrc;

  private String m_offcrTextSrc;

  private BigInteger m_classCmplcCodeSrc;
  
  private BigInteger m_prvtCustTypeCodeSrc;
  
  private BigInteger m_offcrNbrSrc;

  private BigInteger m_prvtKeyNbrSrc;

  private BigInteger m_prvtCustNbrSrc;

  private BigInteger m_wealthPotnlCodeSrc;

  private BigInteger m_custNbrSrc;

  private String m_emNbrSrc;

  private BigInteger m_glbRevenSysOffcrNbrSrc;

  private BigInteger m_selectedCustNbr;
  
  //Status do cliente
  private String m_custPrvtStatCodeSrc = "";
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;

  public static final String C_EM_NBR_SRC_DESCRIPTION = "Número EM";

  public static final String C_CUST_TEXT_SRC_DESCRIPTION = "Nome do Cliente";

  public static final String C_GLB_REVEN_SYS_OFFCR_NBR_SRC_DESCRIPTION = "Número de Officer Global";

  public static final String C_PRVT_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente Private";

  public static final String C_PRVT_KEY_NBR_SRC_DESCRIPTION = "Número Principal do Cliente Private";

  public static final String C_WEALTH_POTNL_CODE_SRC_DESCRIPTION = "Potencial de Receita";

  public static final String C_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente";

  public static final String C_OFFCR_NBR_SRC_DESCRIPTION = "Número do Officer";

  public static final String C_CLASS_CMPLC_CODE_SRC_DESCRIPTION = "Classificação Compliance";
  
  public static final String C_PRVT_CUST_TYPE_CODE_SRC_DESCRIPTION = "Codigo do Tipo de Cliente";

  public static final String C_OFFCR_TEXT_SRC_DESCRIPTION = "Nome do Officer";

  private boolean isFromSearch = false;
  
  private ArrayList m_listCustomer = null;

  /**
   * @return Returns custCpfCnpjNbr.
   */
  public BigInteger getCustCpfCnpjNbr()
  {
    return m_custCpfCnpjNbr;
  }

  /**
   * @param custCpfCnpjNbr_ Field custCpfCnpjNbr to be setted.
   */
  public void setCustCpfCnpjNbr( BigInteger custCpfCnpjNbr_ )
  {
    m_custCpfCnpjNbr = custCpfCnpjNbr_;
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Returns custNbr.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
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
   * @return Returns acctNbrSrc.
   */
  public BigInteger getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param acctNbrSrc_ Field acctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( BigInteger curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Returns reltnNbrSrc.
   */
  public BigInteger getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ Field reltnNbrSrc to be setted.
   */
  public void setReltnNbrSrc( BigInteger reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  public static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente";

  public static final String C_CUST_FULL_NAME_TEXT_DESCRIPTION = "Nome do Cliente";

  public static final String C_CUST_CPF_CNPJ_NBR_DESCRIPTION = "CPF/CNPJ do cliente";

  public static final String C_CUR_ACCT_NBR_DESCRIPTION = "Numero da Conta Corrente Cliente";

  public static final String C_RELTN_NBR_DESCRIPTION = "Numero de Realcionamento";

  // Informações referentes a dados complementares de cliente

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
  public BigInteger getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ Field classCmplcCodeSrc to be setted.
   */
  public void setClassCmplcCodeSrc( BigInteger classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

  /**
   * @return Returns Retorna o Dominio do Tipo de Cliente Private.
   */
  public DataSet getPrvtCustTypeDomain()
  {
	return m_prvtCustTypeCodeDomain;
  }

  /**
   * @param DataSet m_prvtCustTypeCodeDomain_.
   * Seta o Dominio do Tipo de Cliente Private.
   */
  public void setPrvtCustTypeDomain( DataSet prvtCustTypeDomain_ )
  {
	m_prvtCustTypeCodeDomain = prvtCustTypeDomain_;
  }

  /**
   * @return Returns Retorna a Fonte do Dominio do Tipo de Cliente Private.
   */
  public BigInteger getPrvtCustTypeCodeSrc()
  {
	return m_prvtCustTypeCodeSrc;
  }

  /**
   * @param BigInteger prvtCustTypeCodeSrc_.
   * Seta a Fonte do Dominio do Tipo de Cliente Private.
   */
  public void setPrvtCustTypeCodeSrc( BigInteger prvtCustTypeCodeSrc_ )
  {
	m_prvtCustTypeCodeSrc = prvtCustTypeCodeSrc_;
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
  public BigInteger getGlbRevenSysOffcrNbrSrc()
  {
    return m_glbRevenSysOffcrNbrSrc;
  }

  /**
   * @param glbRevenSysOffcrNbrSrc_ Field glbRevenSysOffcrNbrSrc to be setted.
   */
  public void setGlbRevenSysOffcrNbrSrc( BigInteger glbRevenSysOffcrNbrSrc_ )
  {
    m_glbRevenSysOffcrNbrSrc = glbRevenSysOffcrNbrSrc_;
  }

  /**
   * @return Returns offcrNbrDomain.
   */
  public DataSet getOffcrNbrDomain()
  {
    return m_offcrNbrDomain;
  }

  /**
   * @param offcrNbrDomain_ Field offcrNbrDomain to be setted.
   */
  public void setOffcrNbrDomain( DataSet offcrNbrDomain_ )
  {
    m_offcrNbrDomain = offcrNbrDomain_;
  }

  /**
   * @return Returns offcrNbrSrc.
   */
  public BigInteger getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_ Field offcrNbrSrc to be setted.
   */
  public void setOffcrNbrSrc( BigInteger offcrNbrSrc_ )
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
  public BigInteger getPrvtCustNbrSrc()
  {
    return m_prvtCustNbrSrc;
  }

  /**
   * @param prvtCustNbrSrc_ Field prvtCustNbrSrc to be setted.
   */
  public void setPrvtCustNbrSrc( BigInteger prvtCustNbrSrc_ )
  {
    m_prvtCustNbrSrc = prvtCustNbrSrc_;
  }

  /**
   * @return Returns prvtKeyNbrSrc.
   */
  public BigInteger getPrvtKeyNbrSrc()
  {
    return m_prvtKeyNbrSrc;
  }

  /**
   * @param prvtKeyNbrSrc_ Field prvtKeyNbrSrc to be setted.
   */
  public void setPrvtKeyNbrSrc( BigInteger prvtKeyNbrSrc_ )
  {
    m_prvtKeyNbrSrc = prvtKeyNbrSrc_;
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
  public BigInteger getWealthPotnlCodeSrc()
  {
    return m_wealthPotnlCodeSrc;
  }

  /**
   * @param wealthPotnlCodeSrc_ Field wealthPotnlCodeSrc to be setted.
   */
  public void setWealthPotnlCodeSrc( BigInteger wealthPotnlCodeSrc_ )
  {
    m_wealthPotnlCodeSrc = wealthPotnlCodeSrc_;
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
   * @return Returns Retorna a Fonte do Dominio do Tipo de Cliente Private.
   */
  public String getCustPrvtStatCodeSrc()
  {
    return m_custPrvtStatCodeSrc;
  }
  /**
   * @param String custPrvtStatCodeSrc_.
   * 
   */
  public void setCustPrvtStatCodeSrc( String custPrvtStatCodeSrc_ )
  {
    m_custPrvtStatCodeSrc = custPrvtStatCodeSrc_;
  }
  
  /**
	 * @return
	 */
  public ArrayList getListCustomer() {
	  return m_listCustomer;
  }

  /**
	 * @param list
  */
  public void setListCustomer(ArrayList m_listCustomer_) {
	  m_listCustomer = m_listCustomer_;
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

	/**
	 * @return
	 */
	public String getInvstCurAcctNbrSrc() {
		return m_invstCurAcctNbrSrc;
	}

	/**
	 * @param integer
	 */
	public void setInvstCurAcctNbrSrc(String invstCurAcctNbrSrc_) {
		m_invstCurAcctNbrSrc = invstCurAcctNbrSrc_;
	}

}