package com.citibank.ods.modules.client.customerprvt.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.customerPrvt.functionality.valueObject;
 * @version 1.0
 * @author l.braga,14/03/2007
 * 
 */

public abstract class BaseCustomerPrvtDetailFncVO extends BaseFncVO
{
  protected BaseTplCustomerPrvtEntity m_tplCustomerPrvtEntity;
  
  protected BaseTplCustomerPrvtCmplEntity m_tplCustomerPrvtCmplEntity;

  // Variável de controle de habilitação de botões de dados complementares
  private String cmplDataButtonControl = "";
  
  // Combo Sexo
  private DataSet m_custSexCodeDomain = null;

  // combo Estado Civil
  private DataSet m_custCivilStatCodeDomain = null;

  // Combo Categoria de marketing
  private DataSet m_custMktCatCodeDomain = null;

  // Combo codigo de duplicação
  private DataSet m_custDupCodeDomain = null;

  // Combo tipo de pessoa.
  private DataSet m_custTypeCodeDomain = null;

  private DataSet m_custCitiGrpTieIndDomain = null;

  // Combo se o cliente Corporate aplica em Corretora.
  private DataSet m_custCorpBkrApplPrflIndDomain = null;

  // Combo se o cliente Corporate aplica em Fundos
  private DataSet m_custCorpFundApplPrflIndDomain = null;

  // Combo de CPF proprio
  private DataSet m_custCpfOwnIndDomain = null;

  // Combo de funcionario
  private DataSet m_custEmplIndDomain = null;

  // Combo se o cliente possui Green Card.
  private DataSet m_custGrcardIndDomain = null;

  // Combo se e pessoa publica
  private DataSet m_custIndivPublicIndDomain = null;

  // Combo se o cliente ou empresa tem isencao de imposto de renda.
  private DataSet m_custIrrfExemptIndDomain = null;

  // Combo de isencao de CGC
  private DataSet m_custNoCgcIndDomain = null;

  // Combo isencao CPF
  private DataSet m_custNoCpfIndDomain = null;

  // Combo de grau de parentesco p/ CPF nao proprio
  private DataSet m_custParentLevelIndDomain = null;

  // Combo de autorizacao para fornecer informacoes sobre investimentosao
  // Federal Reserve Bank, quando o cliente for cidadao Norte Americano
  private DataSet m_custUsaCtznAuthIndDomain = null;
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;
  
  // Array para Grid de Exibiçãode ER
  private ArrayList erEmEntities = new ArrayList();
  

  
  // Informações referentes a dados complementares de cliente

  private String m_custText;

  private String m_clickedSearch;

  private String m_offcrText;

  public static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente";

  public static final String C_EM_NBR_DESCRIPTION = "Número EM";
  
  public static final String C_ER_NBR_DESCRIPTION = "Número ER";
  
  public static final String C_MAIL_RECV_IND_DESCRIPTION = "Indicador de Envio de Mala Direta";

  public static final String C_OFFCL_MAIL_RECV_IND_DESCRIPTION = "Indicador de Envio de Corresponência Oficial";

  public static final String C_PRVT_CUST_NBR_DESCRIPTION = "Número do Cliente Private";

  public static final String C_PRVT_KEY_NBR_DESCRIPTION = "Número Principal do Cliente Private";

  public static final String C_WEALTH_POTNL_CODE_DESCRIPTION = "Código de Potencial de Receita";

  public static final String C_CLASS_CMPLC_CODE_DESCRIPTION = "Código de Classificação Compliance";
  
  public static final String C_PRVT_CUST_TYPE_CODE_DESCRIPTION = "Código do Tipo de Cliente";
  
  public static final String C_OFFCR_NBR_DESCRIPTION = "Número de Officer";

  public static final String C_GLB_REVEN_SYS_OFFCR_NBR_DESCRIPTION = "Número de Officer Global";

  private boolean isFromSearch = false;

  private DataSet m_wealthPotnlCodeDomain;

  /*
   * Combo para indicadores
   */

  private DataSet m_mailRecvIndDomain;

  /*
   * Combo para indicadores
   */
  private DataSet m_offclMailRecvIndDomain;
  
  private DataSet m_classCmplcCodeDomain;
  
  private DataSet m_prvtCustTypeCodeDomain;
  
  private DataSet m_offcrNbrDomain;

	/**
	 * @return Returns custCitiGrpTieIndDomain.
	 */
	public DataSet getCustCitiGrpTieIndDomain() {
		return m_custCitiGrpTieIndDomain;
	}
	
	/**
	 * @param custCitiGrpTieIndDomain_ Field custCitiGrpTieIndDomain to be setted.
	 */
	public void setCustCitiGrpTieIndDomain(DataSet custCitiGrpTieIndDomain_) {
		m_custCitiGrpTieIndDomain = custCitiGrpTieIndDomain_;
	}
	
	/**
	 * @return Returns custCivilStatCodeDomain.
	 */
	public DataSet getCustCivilStatCodeDomain() {
		return m_custCivilStatCodeDomain;
	}
	
	/**
	 * @param custCivilStatCodeDomain_ Field custCivilStatCodeDomain to be setted.
	 */
	public void setCustCivilStatCodeDomain(DataSet custCivilStatCodeDomain_) {
		m_custCivilStatCodeDomain = custCivilStatCodeDomain_;
	}
	
	/**
	 * @return Returns custCorpBkrApplPrflIndDomain.
	 */
	public DataSet getCustCorpBkrApplPrflIndDomain() {
		return m_custCorpBkrApplPrflIndDomain;
	}
	
	/**
	 * @param custCorpBkrApplPrflIndDomain_ Field custCorpBkrApplPrflIndDomain to
	 *          be setted.
	 */
	public void setCustCorpBkrApplPrflIndDomain(DataSet custCorpBkrApplPrflIndDomain_) {
		m_custCorpBkrApplPrflIndDomain = custCorpBkrApplPrflIndDomain_;
	}
	
	/**
	 * @return Returns custCorpFundApplPrflIndDomain.
	 */
	public DataSet getCustCorpFundApplPrflIndDomain() {
		return m_custCorpFundApplPrflIndDomain;
	}
	
	/**
	 * @param custCorpFundApplPrflIndDomain_ Field custCorpFundApplPrflIndDomain
	 *          to be setted.
	 */
	public void setCustCorpFundApplPrflIndDomain(DataSet custCorpFundApplPrflIndDomain_) {
		m_custCorpFundApplPrflIndDomain = custCorpFundApplPrflIndDomain_;
	}
	
	/**
	 * @return Returns custCpfOwnIndDomain.
	 */
	public DataSet getCustCpfOwnIndDomain() {
		return m_custCpfOwnIndDomain;
	}
	
	/**
	 * @param custCpfOwnIndDomain_ Field custCpfOwnIndDomain to be setted.
	 */
	public void setCustCpfOwnIndDomain(DataSet custCpfOwnIndDomain_) {
		m_custCpfOwnIndDomain = custCpfOwnIndDomain_;
	}
	
	/**
	 * @return Returns custDupCodeDomain.
	 */
	public DataSet getCustDupCodeDomain() {
		return m_custDupCodeDomain;
	}
	
	/**
	 * @param custDupCodeDomain_ Field custDupCodeDomain to be setted.
	 */
	public void setCustDupCodeDomain(DataSet custDupCodeDomain_) {
		m_custDupCodeDomain = custDupCodeDomain_;
	}
	
	/**
	 * @return Returns custEmplIndDomain.
	 */
	public DataSet getCustEmplIndDomain() {
		return m_custEmplIndDomain;
	}
	
	/**
	 * @param custEmplIndDomain_ Field custEmplIndDomain to be setted.
	 */
	public void setCustEmplIndDomain(DataSet custEmplIndDomain_) {
		m_custEmplIndDomain = custEmplIndDomain_;
	}
	
	/**
	 * @return Returns custGrcardIndDomain.
	 */
	public DataSet getCustGrcardIndDomain() {
		return m_custGrcardIndDomain;
	}
	
	/**
	 * @param custGrcardIndDomain_ Field custGrcardIndDomain to be setted.
	 */
	public void setCustGrcardIndDomain(DataSet custGrcardIndDomain_) {
		m_custGrcardIndDomain = custGrcardIndDomain_;
	}
	
	/**
	 * @return Returns custIndivPublicIndDomain.
	 */
	public DataSet getCustIndivPublicIndDomain() {
		return m_custIndivPublicIndDomain;
	}
	
	/**
	 * @param custIndivPublicIndDomain_ Field custIndivPublicIndDomain to be
	 *          setted.
	 */
	public void setCustIndivPublicIndDomain(DataSet custIndivPublicIndDomain_) {
		m_custIndivPublicIndDomain = custIndivPublicIndDomain_;
	}
	
	/**
	 * @return Returns custIrrfExemptIndDomain.
	 */
	public DataSet getCustIrrfExemptIndDomain() {
		return m_custIrrfExemptIndDomain;
	}
	
	/**
	 * @param custIrrfExemptIndDomain_ Field custIrrfExemptIndDomain to be setted.
	 */
	public void setCustIrrfExemptIndDomain(DataSet custIrrfExemptIndDomain_) {
		m_custIrrfExemptIndDomain = custIrrfExemptIndDomain_;
	}
	
	/**
	 * @return Returns custMktCatCodeDomain.
	 */
	public DataSet getCustMktCatCodeDomain() {
		return m_custMktCatCodeDomain;
	}
	
	/**
	 * @param custMktCatCodeDomain_ Field custMktCatCodeDomain to be setted.
	 */
	public void setCustMktCatCodeDomain(DataSet custMktCatCodeDomain_) {
		m_custMktCatCodeDomain = custMktCatCodeDomain_;
	}
	
	/**
	 * @return Returns custNoCgcIndDomain.
	 */
	public DataSet getCustNoCgcIndDomain() {
		return m_custNoCgcIndDomain;
	}
	
	/**
	 * @param custNoCgcIndDomain_ Field custNoCgcIndDomain to be setted.
	 */
	public void setCustNoCgcIndDomain(DataSet custNoCgcIndDomain_) {
		m_custNoCgcIndDomain = custNoCgcIndDomain_;
	}
	
	/**
	 * @return Returns custNoCpfIndDomain.
	 */
	public DataSet getCustNoCpfIndDomain() {
		return m_custNoCpfIndDomain;
	}
	
	/**
	 * @param custNoCpfIndDomain_ Field custNoCpfIndDomain to be setted.
	 */
	public void setCustNoCpfIndDomain(DataSet custNoCpfIndDomain_) {
		m_custNoCpfIndDomain = custNoCpfIndDomain_;
	}
	
	/**
	 * @return Returns custParentLevelIndDomain.
	 */
	public DataSet getCustParentLevelIndDomain() {
		return m_custParentLevelIndDomain;
	}
	
	/**
	 * @param custParentLevelIndDomain_ Field custParentLevelIndDomain to be
	 *          setted.
	 */
	public void setCustParentLevelIndDomain(DataSet custParentLevelIndDomain_) {
		m_custParentLevelIndDomain = custParentLevelIndDomain_;
	}
	
	/**
	 * @return Returns custSexCodeDomain.
	 */
	public DataSet getCustSexCodeDomain() {
		return m_custSexCodeDomain;
	}
	
	/**
	 * @param custSexCodeDomain_ Field custSexCodeDomain to be setted.
	 */
	public void setCustSexCodeDomain(DataSet custSexCodeDomain_) {
		m_custSexCodeDomain = custSexCodeDomain_;
	}
	
	/**
	 * @return Returns custTypeCodeDomain.
	 */
	public DataSet getCustTypeCodeDomain() {
		return m_custTypeCodeDomain;
	}
	
	/**
	 * @param custTypeCodeDomain_ Field custTypeCodeDomain to be setted.
	 */
	public void setCustTypeCodeDomain(DataSet custTypeCodeDomain_) {
		m_custTypeCodeDomain = custTypeCodeDomain_;
	}
	
	/**
	 * @return Returns custUsaCtznAuthIndDomain.
	 */
	public DataSet getCustUsaCtznAuthIndDomain() {
		return m_custUsaCtznAuthIndDomain;
	}
	
	/**
	 * @param custUsaCtznAuthIndDomain_ Field custUsaCtznAuthIndDomain to be
	 *          setted.
	 */
	public void setCustUsaCtznAuthIndDomain(DataSet custUsaCtznAuthIndDomain_) {
		m_custUsaCtznAuthIndDomain = custUsaCtznAuthIndDomain_;
	}
	
	/**
	 * @return Returns tplCustomerPrvtEntity.
	 */
	public BaseTplCustomerPrvtEntity getTplCustomerPrvtEntity() {
		return m_tplCustomerPrvtEntity;
	}
	
	/**
	 * @param tplCustomerPrvtEntity_ Field tplCustomerPrvtEntity to be setted.
	 */
	public void setTplCustomerPrvtEntity(BaseTplCustomerPrvtEntity tplCustomerPrvtEntity_) {
		m_tplCustomerPrvtEntity = tplCustomerPrvtEntity_;
	}
	
	/**
	 * @return Returns isFromSearch.
	 */
	public boolean isFromSearch() {
		return isFromSearch;
	}
	
	/**
	 * @param isFromSearch_ Field isFromSearch to be setted.
	 */
	public void setFromSearch(boolean isFromSearch_) {
		isFromSearch = isFromSearch_;
	}
	
	/**
	 * @return Returns clickedSearch.
	 */
	public String getClickedSearch() {
		return m_clickedSearch;
	}
	
	/**
	 * @param clickedSearch_ Field clickedSearch to be setted.
	 */
	public void setClickedSearch(String clickedSearch_) {
		m_clickedSearch = clickedSearch_;
	}
	
	/**
	 * @return Returns custText.
	 */
	public String getCustText() {
		return m_custText;
	}
	
	/**
	 * @param custText_ Field custText to be setted.
	 */
	public void setCustText(String custText_) {
		m_custText = custText_;
	}
	
	/**
	 * @return Returns offcrText.
	 */
	public String getOffcrText() {
		return m_offcrText;
	}
	
	/**
	 * @param offcrText_ Field offcrText to be setted.
	 */
	public void setOffcrText(String offcrText_) {
		m_offcrText = offcrText_;
	}
	
	/**
	 * @return Returns mailRecvIndDomain.
	 */
	public DataSet getMailRecvIndDomain() {
		return m_mailRecvIndDomain;
	}
	
	/**
	 * @param mailRecvIndDomain_ Field mailRecvIndDomain to be setted.
	 */
	public void setMailRecvIndDomain(DataSet mailRecvIndDomain_) {
		m_mailRecvIndDomain = mailRecvIndDomain_;
	}
	
	/**
	 * @return Returns offclMailRecvIndDomain.
	 */
	public DataSet getOffclMailRecvIndDomain() {
		return m_offclMailRecvIndDomain;
	}
	
	/**
	 * @param offclMailRecvIndDomain_ Field offclMailRecvIndDomain to be setted.
	 */
	public void setOffclMailRecvIndDomain(DataSet offclMailRecvIndDomain_) {
		m_offclMailRecvIndDomain = offclMailRecvIndDomain_;
	}
	
	/**
	 * @return Returns wealthPotnlCodeDomain.
	 */
	public DataSet getWealthPotnlCodeDomain() {
		return m_wealthPotnlCodeDomain;
	}
	
	/**
	 * @param wealthPotnlCodeDomain_ Field wealthPotnlCodeDomain to be setted.
	 */
	public void setWealthPotnlCodeDomain(DataSet wealthPotnlCodeDomain_) {
		m_wealthPotnlCodeDomain = wealthPotnlCodeDomain_;
	}
	//Alteração GPTI
	
	/**
	 * @return Returns Retorna o Dominio do Tipo de Cliente Private.
	 */
	public DataSet getPrvtCustTypeCodeDomain() {
		return m_prvtCustTypeCodeDomain;
	}
	/**
	 * @param prvtCustTypeCodeDomain_.
	 * Seta o Dominio do Tipo de Cliente Private.
	 */
	public void setPrvtCustTypeCodeDomain(DataSet prvtCustTypeCodeDomain_) {
		m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
	}
	
	//Fim Alteração
	/**
	 * @return Returns classCmplcCodeDomain.
	 */
	public DataSet getClassCmplcCodeDomain() {
		return m_classCmplcCodeDomain;
	}
	/**
	 * @param classCmplcCodeDomain_ Field classCmplcCodeDomain to be setted.
	 */
	public void setClassCmplcCodeDomain(DataSet classCmplcCodeDomain_) {
		m_classCmplcCodeDomain = classCmplcCodeDomain_;
	}
	/**
	 * @return Returns offcrNbrDomain.
	 */
	public DataSet getOffcrNbrDomain() {
		return m_offcrNbrDomain;
	}
	/**
	 * @param offcrNbrDomain_ Field offcrNbrDomain to be setted.
	 */
	public void setOffcrNbrDomain(DataSet offcrNbrDomain_) {
		m_offcrNbrDomain = offcrNbrDomain_;
	}
	
	/**
	 * @return Returns tplCustomerPrvtCmplEntity.
	 */
	public BaseTplCustomerPrvtCmplEntity getTplCustomerPrvtCmplEntity() {
		return m_tplCustomerPrvtCmplEntity;
	}
	/**
	 * @param tplCustomerPrvtCmplEntity_ Field tplCustomerPrvtCmplEntity to be setted.
	 */
	public void setTplCustomerPrvtCmplEntity(BaseTplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_) {
		m_tplCustomerPrvtCmplEntity = tplCustomerPrvtCmplEntity_;
	}
	/**
	 * @return Returns cmplDataButtonControl.
	 */
	public String getCmplDataButtonControl() {
		return cmplDataButtonControl;
	}
	/**
	 * @param cmplDataButtonControl_ Field cmplDataButtonControl to be setted.
	 */
	public void setCmplDataButtonControl(String cmplDataButtonControl_) {
		cmplDataButtonControl = cmplDataButtonControl_;
	}
	
	/**
	 * @return Returns custPrvtStatCodeDomain.
	 */
	public DataSet getCustPrvtStatCodeDomain() {
		return m_custPrvtStatCodeDomain;
	}
	/**
	 * @param custPrvtStatCodeDomain_ Field custPrvtStatCodeDomain to be setted.
	 */
	public void setCustPrvtStatCodeDomain(DataSet custPrvtStatCodeDomain_) {
		m_custPrvtStatCodeDomain = custPrvtStatCodeDomain_;
	}
	/**
	 * @return
	 */
	public ArrayList getErEmEntities() {
		return erEmEntities;
	}
	
	/**
	 * @param list
	 */
	public void setErEmEntities(ArrayList erEmEntities_) {
		erEmEntities = erEmEntities_;
	}

 }