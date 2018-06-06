package com.citibank.ods.modules.client.customerprvt.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

/**
 * @author l.braga
 *  
 */

public class BaseCustomerPrvtDetailForm extends BaseForm implements
    CustomerPrvtDetailable
{
  // Variável de controle de habilitação de botões de dados complementares
  private String cmplDataButtonControl;
  
  // Data autorizacao pela alteracao.
  private String m_apprvDate = "";

  // Hora da autorizacao
  private String m_apprvTime = "";

  // Status atual do cliente
  private String m_custActlStatCode = "";

  // Ramo da atividade
  private String m_custActyAreaCode = "";

  // Cidade natal do cliente
  private String m_custBirthCityText = "";

  // Codigo do Pais da Nacionalidade do Cliente
  private String m_custBirthCntryCode = "";

  // Codigo da Nacionalidade da Empresa.
  private String m_custBirthCntryCoCode = "";

  // Data de Aniversario do Cliente , apenas para pessoa fisica.
  private String m_custBirthDate = "";

  // Estado natal do cliente
  private String m_custBirthStateCode = "";

  // Cliente na bolsa de Mercadorias e Futuros
  private String m_custBmfNbr = "";

  // Codigo de identificacao do Cliente na bolsa de Valores do Estado de Sao
  // Paulo
  private String m_custBovespaNbr = "";

  // Codigo de identificacao do Cliente na bolsa de Valores do Estado do Rio de
  // Janeiro
  private String m_custBvrjNbr = "";

  // Codigo de identificacao do cliente no CETIP (Central de Custodia e de
  // liquidacao Financeira de Titulos Privados)
  private String m_custCetipNbr = "";

  // Data quando foi verificada a renda.
  private String m_custChkDate = "";

  // Indica se cliente e ligado ao grupo Citibank (congenere - Intercompany)
  private String m_custCitiGrpTieInd = "";

  // Estado civil
  private String m_custCivilStatCode = "";

  // CNR cliente no ultimo mes
  private String m_custCnrLastMthAmt = "";

  // CNR cliente nos ultimos 6 meses.
  private String m_custCnrLastSixMthCode = "";

  // CNR cliente no ultimo ano
  private String m_custCnrLastYrAmt = "";

  // CNR cliente no ano
  private String m_custCnrYtdAmt = "";

  // Indica se o cliente Corporate aplica em Corretora.
  private String m_custCorpBkrApplPrflInd = "";

  // Indica se o cliente Corporate aplica em Fundos
  private String m_custCorpFundApplPrflInd = "";

  // Forma de constituicao da Empresa.
  private String m_custCoTypeCode = "";

  // CPF e CNPJ do Cliente ou Empresa.
  private String m_custCpfCnpjNbr = "";

  // Indicador de CPF proprio
  private String m_custCpfOwnInd = "";

  // Numero de dependentes
  private String m_custDepndNbr = "";

  // Data de ultima alteracao da quantidade de dependentes
  private String m_custDepndNbrDate = "";

  // Codigo de duplicacao de Cliente.
  private String m_custDupCode = "";

  // Indicador de funcionario
  private String m_custEmplInd = "";

  // Data do ingresso do cliente
  private String m_custEstabDate = "";

  // Operador da Inclusao do Cliente.
  private String m_custEstabOpId = "";

  // Hora do ingresso do cliente
  private String m_custEstabTime = "";

  // Data da fundacao da empresa do Cliente.
  private String m_custFndtnDate = "";

  // Nome Completo do Cliente/Empre sa.
  private String m_custFullNameText = "";

  // Indica se o cliente possui Green Card.
  private String m_custGrcardInd = "";

  // Codigo da empresa que lidera o grupo a que este cliente pertence.
  private String m_custGrpCode = "";

  // Indica se e pessoa publica
  private String m_custIndivPublicInd = "";

  // Codigo de origem do movimento de inclusao do cliente
  private String m_custInputOrigCode = "";

  // Numero internacional atribuido pela matriz Citibank para Clientes Corporate
  // (GFCID)
  private String m_custIntlNbr = "";

  // Indica se o cliente ou empresa tem isencao de imposto de renda.
  private String m_custIrrfExemptInd = "";

  // Chave de busca para o nome do Cliente / Empresa.
  private String m_custKeyNameText = "";

  // Quantidade de salarios minimos da renda
  private String m_custMgmtIncoMinSalCount = "";

  // Categoria de Marketing do Cliente.
  private String m_custMktCatCode = "";

  // Numero de Identidade da Pessoa
  private String m_custNatId = "";

  // Data de emissao da identidade
  private String m_custNatIdApplDate = "";

  // Codigo do emissor da identidade.
  private String m_custNatIdEmitCode = "";

  // Orgao emissor da identidade
  private String m_custNatIdEmitName = "";

  // Estado emissor da identidade
  private String m_custNatIdEmitStateCode = "";

  // Numero do Cliente no CMS (Customer Number)
  private String m_custNbr = "";

  // Indicador de isencao de CGC
  private String m_custNoCgcInd = "";

  // Indicador isencao CPF
  private String m_custNoCpfInd = "";

  // Codigo ocupacional
  private String m_custOccupCode = "";

  // Indicador de grau de parentesco p/ CPF nao proprio
  private String m_custParentLevelInd = "";

  // Codigo da profissao
  private String m_custProfCode = "";

  // Codigo de identificacao do Cliente no SELIC (Servico de Licitacao de
  // Titulos Publicos)
  private String m_custSelicNbr = "";

  // Codigo do Sexo do Cliente.
  private String m_custSexCode = "";

  // Nome abreviado do cliente empresa.
  private String m_custShortNameText = "";

  // Numero do green card ou social security nbr
  private String m_custSocSctyNbr = "";

  // Data do status do cliente
  private String m_custStatDate = "";

  // Codigo da empresa que lidera o subgrupo a que este clientep pertence
  private String m_custSubGrpCode = "";

  // Tipo de Pessoa (Fisica ou Juridica)
  private String m_custTypeCode = "";

  // Indicador de autorizacao para fornecer informacoes sobre investimentosao
  // Federal Reserve Bank, quando o cliente for cidadao Norte Americano
  private String m_custUsaCtznAuthInd = "";

  // Operador que registrou a autorizacao para informacao ao Federal Reserve
  // Bank dos investimentos do cliente, quando este for um US Citizen
  private String m_custUsaCtznAuthOpId = "";

  // Data da ultima alteracao
  private String m_lastUpdateDate = "";

  // Responsavel pela ultima atualizacao.
  private String m_lastUpdateOpId = "";

  // Hora da ultima alteracao
  private String m_lastUpdateTime = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

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
  
  //Status do cliente
  private String m_custPrvtStatCode = "";
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;

  // Informações referentes a dados complementares de cliente

  private String m_emNbr;
  
  private String m_erNbr;
  
  private String m_mailRecvInd;

  private DataSet m_mailRecvIndDomain;

  private String m_glbRevenSysOffcrNbr;

  private String m_offclMailRecvInd;

  private String m_clickedSearch;

  private DataSet m_offclMailRecvIndDomain;

  private String m_prvtCustNbr;

  private String m_prvtKeyNbr;

  private String m_wealthPotnlCode;

  private String m_custNbrSrc;

  private String m_custText;

  private String m_classCmplcCode;
  
  private String m_prvtCustTypeCode;
  
  private String m_offcrText;

  private String m_offcrNbrSrc;

  private DataSet m_wealthPotnlCodeDomain;

  private DataSet m_classCmplcCodeDomain;
  
  private DataSet m_prvtCustTypeCodeDomain;
  
  private String[] m_domainsErNbr;
  
  private String[] m_domainsEmNbr;
  
  private String[] m_erEmGrid = null;

  /* 
   * RDIP - Inclusão do descritivo de risco 
   * Data: 05/01/2011
   * Responsável: Eversystems
   */
  private String m_rdipDescription;
  
  /**
   * @return Returns m_apprvDate.
   */
  public String getApprvDate()
  {
    return m_apprvDate;
  }

  /**
   * @param apprvDate_ Field m_apprvDate to be setted.
   */
  public void setApprvDate( String apprvDate_ )
  {
    m_apprvDate = apprvDate_;
  }

  /**
   * @return Returns m_apprvTime.
   */
  public String getApprvTime()
  {
    return m_apprvTime;
  }

  /**
   * @param apprvTime_ Field m_apprvTime to be setted.
   */
  public void setApprvTime( String apprvTime_ )
  {
    m_apprvTime = apprvTime_;
  }

  /**
   * @return Returns m_custActlStatCode.
   */
  public String getCustActlStatCode()
  {
    return m_custActlStatCode;
  }

	/**
	 * @param custActlStatCode_ Field m_custActlStatCode to be setted.
	 */
	public void setCustActlStatCode(String custActlStatCode_) {
		m_custActlStatCode = custActlStatCode_;
	}
	
	/**
	 * @return Returns m_custActyAreaCode.
	 */
	public String getCustActyAreaCode() {
		return m_custActyAreaCode;
	}
	
	/**
	 * @param custActyAreaCode_ Field m_custActyAreaCode to be setted.
	 */
	public void setCustActyAreaCode(String custActyAreaCode_) {
		m_custActyAreaCode = custActyAreaCode_;
	}
	
	/**
	 * @return Returns m_custBirthCityText.
	 */
	public String getCustBirthCityText() {
		return m_custBirthCityText;
	}
	
	/**
	 * @param custBirthCityText_ Field m_custBirthCityText to be setted.
	 */
	public void setCustBirthCityText(String custBirthCityText_) {
		m_custBirthCityText = custBirthCityText_;
	}
	
	/**
	 * @return Returns m_custBirthCntryCode.
	 */
	public String getCustBirthCntryCode() {
		return m_custBirthCntryCode;
	}
	
	/**
	 * @param custBirthCntryCode_ Field m_custBirthCntryCode to be setted.
	 */
	public void setCustBirthCntryCode(String custBirthCntryCode_) {
		m_custBirthCntryCode = custBirthCntryCode_;
	}
	
	/**
	 * @return Returns m_custBirthCntryCoCode.
	 */
	public String getCustBirthCntryCoCode() {
		return m_custBirthCntryCoCode;
	}
	
	/**
	 * @param custBirthCntryCoCode_ Field m_custBirthCntryCoCode to be setted.
	 */
	public void setCustBirthCntryCoCode(String custBirthCntryCoCode_) {
		m_custBirthCntryCoCode = custBirthCntryCoCode_;
	}
	
	/**
	 * @return Returns m_custBirthDate.
	 */
	public String getCustBirthDate() {
		return m_custBirthDate;
	}
	
	/**
	 * @param custBirthDate_ Field m_custBirthDate to be setted.
	 */
	public void setCustBirthDate(String custBirthDate_) {
		m_custBirthDate = custBirthDate_;
	}
	
	/**
	 * @return Returns m_custBirthStateCode.
	 */
	public String getCustBirthStateCode() {
		return m_custBirthStateCode;
	}
	
	/**
	 * @param custBirthStateCode_ Field m_custBirthStateCode to be setted.
	 */
	public void setCustBirthStateCode(String custBirthStateCode_) {
		m_custBirthStateCode = custBirthStateCode_;
	}
	
	/**
	 * @return Returns m_custBmfNbr.
	 */
	public String getCustBmfNbr() {
		return m_custBmfNbr;
	}
	
	/**
	 * @param custBmfNbr_ Field m_custBmfNbr to be setted.
	 */
	public void setCustBmfNbr(String custBmfNbr_) {
		m_custBmfNbr = custBmfNbr_;
	}
	
	/**
	 * @return Returns m_custBovespaNbr.
	 */
	public String getCustBovespaNbr() {
		return m_custBovespaNbr;
	}
	
	/**
	 * @param custBovespaNbr_ Field m_custBovespaNbr to be setted.
	 */
	public void setCustBovespaNbr(String custBovespaNbr_) {
		m_custBovespaNbr = custBovespaNbr_;
	}
	
	/**
	 * @return Returns m_custBvrjNbr.
	 */
	public String getCustBvrjNbr() {
		return m_custBvrjNbr;
	}
	
	/**
	 * @param custBvrjNbr_ Field m_custBvrjNbr to be setted.
	 */
	public void setCustBvrjNbr(String custBvrjNbr_) {
		m_custBvrjNbr = custBvrjNbr_;
	}
	
	/**
	 * @return Returns m_custCetipNbr.
	 */
	public String getCustCetipNbr() {
		return m_custCetipNbr;
	}
	
	/**
	 * @param custCetipNbr_ Field m_custCetipNbr to be setted.
	 */
	public void setCustCetipNbr(String custCetipNbr_) {
		m_custCetipNbr = custCetipNbr_;
	}
	
	/**
	 * @return Returns m_custChkDate.
	 */
	public String getCustChkDate() {
		return m_custChkDate;
	}
	
	/**
	 * @param custChkDate_ Field m_custChkDate to be setted.
	 */
	public void setCustChkDate(String custChkDate_) {
		m_custChkDate = custChkDate_;
	}
	
	/**
	 * @return Returns m_custCitiGrpTieInd.
	 */
	public String getCustCitiGrpTieInd() {
		return m_custCitiGrpTieInd;
	}
	
	/**
	 * @param custCitiGrpTieInd_ Field m_custCitiGrpTieInd to be setted.
	 */
	public void setCustCitiGrpTieInd(String custCitiGrpTieInd_) {
		m_custCitiGrpTieInd = custCitiGrpTieInd_;
	}
	
	/**
	 * @return Returns m_custCivilStatCode.
	 */
	public String getCustCivilStatCode() {
		return m_custCivilStatCode;
	}
	
	/**
	 * @param custCivilStatCode_ Field m_custCivilStatCode to be setted.
	 */
	public void setCustCivilStatCode(String custCivilStatCode_) {
		m_custCivilStatCode = custCivilStatCode_;
	}
	
	/**
	 * @return Returns m_custCnrLastMthAmt.
	 */
	public String getCustCnrLastMthAmt() {
		return m_custCnrLastMthAmt;
	}
	
	/**
	 * @param custCnrLastMthAmt_ Field m_custCnrLastMthAmt to be setted.
	 */
	public void setCustCnrLastMthAmt(String custCnrLastMthAmt_) {
		m_custCnrLastMthAmt = custCnrLastMthAmt_;
	}
	
	/**
	 * @return Returns m_custCnrLastSixMthCode.
	 */
	public String getCustCnrLastSixMthCode() {
		return m_custCnrLastSixMthCode;
	}
	
	/**
	 * @param custCnrLastSixMthCode_ Field m_custCnrLastSixMthCode to be setted.
	 */
	public void setCustCnrLastSixMthCode(String custCnrLastSixMthCode_) {
		m_custCnrLastSixMthCode = custCnrLastSixMthCode_;
	}
	
	/**
	 * @return Returns m_custCnrLastYrAmt.
	 */
	public String getCustCnrLastYrAmt() {
		return m_custCnrLastYrAmt;
	}
	
	/**
	 * @param custCnrLastYrAmt_ Field m_custCnrLastYrAmt to be setted.
	 */
	public void setCustCnrLastYrAmt(String custCnrLastYrAmt_) {
		m_custCnrLastYrAmt = custCnrLastYrAmt_;
	}
	
	/**
	 * @return Returns m_custCnrYtdAmt.
	 */
	public String getCustCnrYtdAmt() {
		return m_custCnrYtdAmt;
	}
	
	/**
	 * @param custCnrYtdAmt_ Field m_custCnrYtdAmt to be setted.
	 */
	public void setCustCnrYtdAmt(String custCnrYtdAmt_) {
		m_custCnrYtdAmt = custCnrYtdAmt_;
	}
	
	/**
	 * @return Returns m_custCorpBkrApplPrflInd.
	 */
	public String getCustCorpBkrApplPrflInd() {
		return m_custCorpBkrApplPrflInd;
	}
	
	/**
	 * @param custCorpBkrApplPrflInd_ Field m_custCorpBkrApplPrflInd to be setted.
	 */
	public void setCustCorpBkrApplPrflInd(String custCorpBkrApplPrflInd_) {
		m_custCorpBkrApplPrflInd = custCorpBkrApplPrflInd_;
	}
	
	/**
	 * @return Returns m_custCorpFundApplPrflInd.
	 */
	public String getCustCorpFundApplPrflInd() {
		return m_custCorpFundApplPrflInd;
	}
	
	/**
	 * @param custCorpFundApplPrflInd_ Field m_custCorpFundApplPrflInd to be
	 *          setted.
	 */
	public void setCustCorpFundApplPrflInd(String custCorpFundApplPrflInd_) {
		m_custCorpFundApplPrflInd = custCorpFundApplPrflInd_;
	}
	
	/**
	 * @return Returns m_custCoTypeCode.
	 */
	public String getCustCoTypeCode() {
		return m_custCoTypeCode;
	}
	
	/**
	 * @param custCoTypeCode_ Field m_custCoTypeCode to be setted.
	 */
	public void setCustCoTypeCode(String custCoTypeCode_) {
		m_custCoTypeCode = custCoTypeCode_;
	}
	
	/**
	 * @return Returns m_custCpfCnpjNbr.
	 */
	public String getCustCpfCnpjNbr() {
		return m_custCpfCnpjNbr;
	}
	
	/**
	 * @param custCpfCnpjNbr_ Field m_custCpfCnpjNbr to be setted.
	 */
	public void setCustCpfCnpjNbr(String custCpfCnpjNbr_) {
		m_custCpfCnpjNbr = removeMask(custCpfCnpjNbr_);
	}
	
	/**
	 * @return Returns m_custCpfOwnInd.
	 */
	public String getCustCpfOwnInd() {
		return m_custCpfOwnInd;
	}
	
	/**
	 * @param custCpfOwnInd_ Field m_custCpfOwnInd to be setted.
	 */
	public void setCustCpfOwnInd(String custCpfOwnInd_) {
		m_custCpfOwnInd = custCpfOwnInd_;
	}
	
	/**
	 * @return Returns m_custDepndNbr.
	 */
	public String getCustDepndNbr() {
		return m_custDepndNbr;
	}
	
	/**
	 * @param custDepndNbr_ Field m_custDepndNbr to be setted.
	 */
	public void setCustDepndNbr(String custDepndNbr_) {
		m_custDepndNbr = custDepndNbr_;
	}
	
	/**
	 * @return Returns m_custDepndNbrDate.
	 */
	public String getCustDepndNbrDate() {
		return m_custDepndNbrDate;
	}
	
	/**
	 * @param custDepndNbrDate_ Field m_custDepndNbrDate to be setted.
	 */
	public void setCustDepndNbrDate(String custDepndNbrDate_) {
		m_custDepndNbrDate = custDepndNbrDate_;
	}
	
	/**
	 * @return Returns m_custDupCode.
	 */
	public String getCustDupCode() {
		return m_custDupCode;
	}
	
	/**
	 * @param custDupCode_ Field m_custDupCode to be setted.
	 */
	public void setCustDupCode(String custDupCode_) {
		m_custDupCode = custDupCode_;
	}
	
	/**
	 * @return Returns m_custEmplInd.
	 */
	public String getCustEmplInd() {
		return m_custEmplInd;
	}
	
	/**
	 * @param custEmplInd_ Field m_custEmplInd to be setted.
	 */
	public void setCustEmplInd(String custEmplInd_) {
		m_custEmplInd = custEmplInd_;
	}
	
	/**
	 * @return Returns m_custEstabDate.
	 */
	public String getCustEstabDate() {
		return m_custEstabDate;
	}
	
	/**
	 * @param custEstabDate_ Field m_custEstabDate to be setted.
	 */
	public void setCustEstabDate(String custEstabDate_) {
		m_custEstabDate = custEstabDate_;
	}
	
	/**
	 * @return Returns m_custEstabOpId.
	 */
	public String getCustEstabOpId() {
		return m_custEstabOpId;
	}
	
	/**
	 * @param custEstabOpId_ Field m_custEstabOpId to be setted.
	 */
	public void setCustEstabOpId(String custEstabOpId_) {
		m_custEstabOpId = custEstabOpId_;
	}
	
	/**
	 * @return Returns m_custEstabTime.
	 */
	public String getCustEstabTime() {
		return m_custEstabTime;
	}
	
	/**
	 * @param custEstabTime_ Field m_custEstabTime to be setted.
	 */
	public void setCustEstabTime(String custEstabTime_) {
		m_custEstabTime = custEstabTime_;
	}
	
	/**
	 * @return Returns m_custFndtnDate.
	 */
	public String getCustFndtnDate() {
		return m_custFndtnDate;
	}
	
	/**
	 * @param custFndtnDate_ Field m_custFndtnDate to be setted.
	 */
	public void setCustFndtnDate(String custFndtnDate_) {
		m_custFndtnDate = custFndtnDate_;
	}
	
	/**
	 * @return Returns m_custFullNameText.
	 */
	public String getCustFullNameText() {
		return m_custFullNameText;
	}
	
	/**
	 * @param custFullNameText_ Field m_custFullNameText to be setted.
	 */
	public void setCustFullNameText(String custFullNameText_) {
		m_custFullNameText = custFullNameText_;
	}
	
	/**
	 * @return Returns m_custGrcardInd.
	 */
	public String getCustGrcardInd() {
		return m_custGrcardInd;
	}
	
	/**
	 * @param custGrcardInd_ Field m_custGrcardInd to be setted.
	 */
	public void setCustGrcardInd(String custGrcardInd_) {
		m_custGrcardInd = custGrcardInd_;
	}
	
	/**
	 * @return Returns m_custGrpCode.
	 */
	public String getCustGrpCode() {
		return m_custGrpCode;
	}
	
	/**
	 * @param custGrpCode_ Field m_custGrpCode to be setted.
	 */
	public void setCustGrpCode(String custGrpCode_) {
		m_custGrpCode = custGrpCode_;
	}
	
	/**
	 * @return Returns m_custIndivPublicInd.
	 */
	public String getCustIndivPublicInd() {
		return m_custIndivPublicInd;
	}
	
	/**
	 * @param custIndivPublicInd_ Field m_custIndivPublicInd to be setted.
	 */
	public void setCustIndivPublicInd(String custIndivPublicInd_) {
		m_custIndivPublicInd = custIndivPublicInd_;
	}
	
	/**
	 * @return Returns m_custInputOrigCode.
	 */
	public String getCustInputOrigCode() {
		return m_custInputOrigCode;
	}
	
	/**
	 * @param custInputOrigCode_ Field m_custInputOrigCode to be setted.
	 */
	public void setCustInputOrigCode(String custInputOrigCode_) {
		m_custInputOrigCode = custInputOrigCode_;
	}
	
	/**
	 * @return Returns m_custIntlNbr.
	 */
	public String getCustIntlNbr() {
		return m_custIntlNbr;
	}
	
	/**
	 * @param custIntlNbr_ Field m_custIntlNbr to be setted.
	 */
	public void setCustIntlNbr(String custIntlNbr_) {
		m_custIntlNbr = custIntlNbr_;
	}
	
	/**
	 * @return Returns m_custIrrfExemptInd.
	 */
	public String getCustIrrfExemptInd() {
		return m_custIrrfExemptInd;
	}
	
	/**
	 * @param custIrrfExemptInd_ Field m_custIrrfExemptInd to be setted.
	 */
	public void setCustIrrfExemptInd(String custIrrfExemptInd_) {
		m_custIrrfExemptInd = custIrrfExemptInd_;
	}
	
	/**
	 * @return Returns m_custKeyNameText.
	 */
	public String getCustKeyNameText() {
		return m_custKeyNameText;
	}
	
	/**
	 * @param custKeyNameText_ Field m_custKeyNameText to be setted.
	 */
	public void setCustKeyNameText(String custKeyNameText_) {
		m_custKeyNameText = custKeyNameText_;
	}
	
	/**
	 * @return Returns m_custMgmtIncoMinSalCount.
	 */
	public String getCustMgmtIncoMinSalCount() {
		return m_custMgmtIncoMinSalCount;
	}
	
	/**
	 * @param custMgmtIncoMinSalCount_ Field m_custMgmtIncoMinSalCount to be
	 *          setted.
	 */
	public void setCustMgmtIncoMinSalCount(String custMgmtIncoMinSalCount_) {
		m_custMgmtIncoMinSalCount = custMgmtIncoMinSalCount_;
	}
	
	/**
	 * @return Returns m_custMktCatCode.
	 */
	public String getCustMktCatCode() {
		return m_custMktCatCode;
	}
	
	/**
	 * @param custMktCatCode_ Field m_custMktCatCode to be setted.
	 */
	public void setCustMktCatCode(String custMktCatCode_) {
		m_custMktCatCode = custMktCatCode_;
	}
	
	/**
	 * @return Returns m_custNatId.
	 */
	public String getCustNatId() {
		return m_custNatId;
	}
	
	/**
	 * @param custNatId_ Field m_custNatId to be setted.
	 */
	public void setCustNatId(String custNatId_) {
		m_custNatId = custNatId_;
	}
	
	/**
	 * @return Returns m_custNatIdApplDate.
	 */
	public String getCustNatIdApplDate() {
		return m_custNatIdApplDate;
	}
	
	/**
	 * @param custNatIdApplDate_ Field m_custNatIdApplDate to be setted.
	 */
	public void setCustNatIdApplDate(String custNatIdApplDate_) {
		m_custNatIdApplDate = custNatIdApplDate_;
	}
	
	/**
	 * @return Returns m_custNatIdEmitCode.
	 */
	public String getCustNatIdEmitCode() {
		return m_custNatIdEmitCode;
	}
	
	/**
	 * @param custNatIdEmitCode_ Field m_custNatIdEmitCode to be setted.
	 */
	public void setCustNatIdEmitCode(String custNatIdEmitCode_) {
		m_custNatIdEmitCode = custNatIdEmitCode_;
	}
	
	/**
	 * @return Returns m_custNatIdEmitName.
	 */
	public String getCustNatIdEmitName() {
		return m_custNatIdEmitName;
	}
	
	/**
	 * @param custNatIdEmitName_ Field m_custNatIdEmitName to be setted.
	 */
	public void setCustNatIdEmitName(String custNatIdEmitName_) {
		m_custNatIdEmitName = custNatIdEmitName_;
	}
	
	/**
	 * @return Returns m_custNatIdEmitStateCode.
	 */
	public String getCustNatIdEmitStateCode() {
		return m_custNatIdEmitStateCode;
	}
	
	/**
	 * @param custNatIdEmitStateCode_ Field m_custNatIdEmitStateCode to be setted.
	 */
	public void setCustNatIdEmitStateCode(String custNatIdEmitStateCode_) {
		m_custNatIdEmitStateCode = custNatIdEmitStateCode_;
	}
	
	/**
	 * @return Returns m_custNbr.
	 */
	public String getCustNbr() {
		return m_custNbr;
	}
	
	/**
	 * @param custNbr_ Field m_custNbr to be setted.
	 */
	public void setCustNbr(String custNbr_) {
		m_custNbr = custNbr_;
	}
	
	/**
	 * @return Returns m_custNoCgcInd.
	 */
	public String getCustNoCgcInd() {
		return m_custNoCgcInd;
	}
	
	/**
	 * @param custNoCgcInd_ Field m_custNoCgcInd to be setted.
	 */
	public void setCustNoCgcInd(String custNoCgcInd_) {
		m_custNoCgcInd = custNoCgcInd_;
	}
	
	/**
	 * @return Returns m_custNoCpfInd.
	 */
	public String getCustNoCpfInd() {
		return m_custNoCpfInd;
	}
	
	/**
	 * @param custNoCpfInd_ Field m_custNoCpfInd to be setted.
	 */
	public void setCustNoCpfInd(String custNoCpfInd_) {
		m_custNoCpfInd = custNoCpfInd_;
	}
	
	/**
	 * @return Returns m_custOccupCode.
	 */
	public String getCustOccupCode() {
		return m_custOccupCode;
	}
	
	/**
	 * @param custOccupCode_ Field m_custOccupCode to be setted.
	 */
	public void setCustOccupCode(String custOccupCode_) {
		m_custOccupCode = custOccupCode_;
	}
	
	/**
	 * @return Returns m_custParentLevelInd.
	 */
	public String getCustParentLevelInd() {
		return m_custParentLevelInd;
	}
	
	/**
	 * @param custParentLevelInd_ Field m_custParentLevelInd to be setted.
	 */
	public void setCustParentLevelInd(String custParentLevelInd_) {
		m_custParentLevelInd = custParentLevelInd_;
	}
	
	/**
	 * @return Returns m_custProfCode.
	 */
	public String getCustProfCode() {
		return m_custProfCode;
	}
	
	/**
	 * @param custProfCode_ Field m_custProfCode to be setted.
	 */
	public void setCustProfCode(String custProfCode_) {
		m_custProfCode = custProfCode_;
	}
	
	/**
	 * @return Returns m_custSelicNbr.
	 */
	public String getCustSelicNbr() {
		return m_custSelicNbr;
	}
	
	/**
	 * @param custSelicNbr_ Field m_custSelicNbr to be setted.
	 */
	public void setCustSelicNbr(String custSelicNbr_) {
		m_custSelicNbr = custSelicNbr_;
	}
	
	/**
	 * @return Returns m_custSexCode.
	 */
	public String getCustSexCode() {
		return m_custSexCode;
	}
	
	/**
	 * @param custSexCode_ Field m_custSexCode to be setted.
	 */
	public void setCustSexCode(String custSexCode_) {
		m_custSexCode = custSexCode_;
	}
	
	/**
	 * @return Returns m_custShortNameText.
	 */
	public String getCustShortNameText() {
		return m_custShortNameText;
	}
	
	/**
	 * @param custShortNameText_ Field m_custShortNameText to be setted.
	 */
	public void setCustShortNameText(String custShortNameText_) {
		m_custShortNameText = custShortNameText_;
	}
	
	/**
	 * @return Returns m_custSocSctyNbr.
	 */
	public String getCustSocSctyNbr() {
		return m_custSocSctyNbr;
	}
	
	/**
	 * @param custSocSctyNbr_ Field m_custSocSctyNbr to be setted.
	 */
	public void setCustSocSctyNbr(String custSocSctyNbr_) {
		m_custSocSctyNbr = custSocSctyNbr_;
	}
	
	/**
	 * @return Returns m_custStatDate.
	 */
	public String getCustStatDate() {
		return m_custStatDate;
	}
	
	/**
	 * @param custStatDate_ Field m_custStatDate to be setted.
	 */
	public void setCustStatDate(String custStatDate_) {
		m_custStatDate = custStatDate_;
	}
	
	/**
	 * @return Returns m_custSubGrpCode.
	 */
	public String getCustSubGrpCode() {
		return m_custSubGrpCode;
	}
	
	/**
	 * @param custSubGrpCode_ Field m_custSubGrpCode to be setted.
	 */
	public void setCustSubGrpCode(String custSubGrpCode_) {
		m_custSubGrpCode = custSubGrpCode_;
	}
	
	/**
	 * @return Returns m_custTypeCode.
	 */
	public String getCustTypeCode() {
		return m_custTypeCode;
	}
	
	/**
	 * @param custTypeCode_ Field m_custTypeCode to be setted.
	 */
	public void setCustTypeCode(String custTypeCode_) {
		m_custTypeCode = custTypeCode_;
	}
	
	/**
	 * @return Returns m_custUsaCtznAuthInd.
	 */
	public String getCustUsaCtznAuthInd() {
		return m_custUsaCtznAuthInd;
	}
	
	/**
	 * @param custUsaCtznAuthInd_ Field m_custUsaCtznAuthInd to be setted.
	 */
	public void setCustUsaCtznAuthInd(String custUsaCtznAuthInd_) {
		m_custUsaCtznAuthInd = custUsaCtznAuthInd_;
	}
	
	/**
	 * @return Returns m_custUsaCtznAuthOpId.
	 */
	public String getCustUsaCtznAuthOpId() {
		return m_custUsaCtznAuthOpId;
	}
	
	/**
	 * @param custUsaCtznAuthOpId_ Field m_custUsaCtznAuthOpId to be setted.
	 */
	public void setCustUsaCtznAuthOpId(String custUsaCtznAuthOpId_) {
		m_custUsaCtznAuthOpId = custUsaCtznAuthOpId_;
	}
	
	/**
	 * @return Returns m_lastUpdateDate.
	 */
	public String getLastUpdateDate() {
		return m_lastUpdateDate;
	}
	
	/**
	 * @param lastUpdateDate_ Field m_lastUpdateDate to be setted.
	 */
	public void setLastUpdateDate(String lastUpdateDate_) {
		m_lastUpdateDate = lastUpdateDate_;
	}
	
	/**
	 * @return Returns m_lastUpdateOpId.
	 */
	public String getLastUpdateOpId() {
		return m_lastUpdateOpId;
	}
	
	/**
	 * @param lastUpdateOpId_ Field m_lastUpdateOpId to be setted.
	 */
	public void setLastUpdateOpId(String lastUpdateOpId_) {
		m_lastUpdateOpId = lastUpdateOpId_;
	}
	
	/**
	 * @return Returns m_lastUpdateTime.
	 */
	public String getLastUpdateTime() {
		return m_lastUpdateTime;
	}
	
	/**
	 * @param lastUpdateTime_ Field m_lastUpdateTime to be setted.
	 */
	public void setLastUpdateTime(String lastUpdateTime_) {
		m_lastUpdateTime = lastUpdateTime_;
	}
	
	/**
	 * @return Returns m_recStatCode.
	 */
	public String getRecStatCode() {
		return m_recStatCode;
	}
	
	/**
	 * @param recStatCode_ Field m_recStatCode to be setted.
	 */
	public void setRecStatCode(String recStatCode_) {
		m_recStatCode = recStatCode_;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable#getSelectedCustNbr()
	 */
	public String getSelectedCustNbr() {
		/**
		 * "[Method description]"
		 * 
		 * @param
		 * @return
		 * @exception
		 * @see
		 */
		return null;
	}
	
	/*
	 * Seta o atributo da tela no CustNbr (non-Javadoc)
	 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable#setSelectedCustNbr(java.lang.String)
	 */
	public void setSelectedCustNbr(String custNbr_) {
		setCustNbr(custNbr_);
	
	}
	
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
	 * @return Returns classCmplcCode.
	 */
	public String getClassCmplcCode() {
		return m_classCmplcCode;
	}
	
	/**
	 * @param classCmplcCode_ Field classCmplcCode to be setted.
	 */
	public void setClassCmplcCode(String classCmplcCode_) {
		m_classCmplcCode = classCmplcCode_;
	}
	
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
	//Alteraçao GPTI
	
	/**
	  * @return Returns Retorna o Codigo do Tipo de Cliente Private.
	  */
	public String getPrvtCustTypeCode() {
		return m_prvtCustTypeCode;
	}
	
	/**
	 * @param PrvtCustTypeCode_.
	 * Seta o Codigo do Tipo de Cliente Private.
	 */
	public void setPrvtCustTypeCode(String prvtCustTypeCode_) {
		m_prvtCustTypeCode = prvtCustTypeCode_;
	}
	
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
	//Fim Alteração GPTI
	
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
	 * @return Returns custNbrSrc.
	 */
	public String getCustNbrSrc() {
		return m_custNbrSrc;
	}
	
	/**
	 * @param custNbrSrc_ Field custNbrSrc to be setted.
	 */
	public void setCustNbrSrc(String custNbrSrc_) {
		m_custNbrSrc = custNbrSrc_;
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
	 * @return Returns emNbr.
	 */
	public String getEmNbr() {
		return m_emNbr;
	}
	
	/**
	 * @param emNbr_ Field emNbr to be setted.
	 */
	public void setEmNbr(String emNbr_) {
		m_emNbr = emNbr_;
	}

	/**
	 * @return Returns emNbr.
	 */
	public String getErNbr() {
		return m_erNbr;
	}
	
	/**
	 * @param emNbr_ Field emNbr to be setted.
	 */
	public void setErNbr(String erNbr_) {
		m_erNbr = erNbr_;
	}
	
	/**
	 * @return Returns glbRevenSysOffcrNbr.
	 */
	public String getGlbRevenSysOffcrNbr() {
		return m_glbRevenSysOffcrNbr;
	}
	
	/**
	 * @param glbRevenSysOffcrNbr_ Field glbRevenSysOffcrNbr to be setted.
	 */
	public void setGlbRevenSysOffcrNbr(String glbRevenSysOffcrNbr_) {
		m_glbRevenSysOffcrNbr = glbRevenSysOffcrNbr_;
	}
	
	/**
	 * @return Returns mailRecvInd.
	 */
	public String getMailRecvInd() {
		return m_mailRecvInd;
	}
	
	/**
	 * @param mailRecvInd_ Field mailRecvInd to be setted.
	 */
	public void setMailRecvInd(String mailRecvInd_) {
		m_mailRecvInd = mailRecvInd_;
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
	 * @return Returns offclMailRecvInd.
	 */
	public String getOffclMailRecvInd() {
		return m_offclMailRecvInd;
	}
	
	/**
	 * @param offclMailRecvInd_ Field offclMailRecvInd to be setted.
	 */
	public void setOffclMailRecvInd(String offclMailRecvInd_) {
		m_offclMailRecvInd = offclMailRecvInd_;
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
	 * @return Returns offcrNbrSrc.
	 */
	public String getOffcrNbrSrc() {
		return m_offcrNbrSrc;
	}
	
	/**
	 * @param offcrNbrSrc_ Field offcrNbrSrc to be setted.
	 */
	public void setOffcrNbrSrc(String offcrNbrSrc_) {
		m_offcrNbrSrc = offcrNbrSrc_;
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
	 * @return Returns prvtCustNbr.
	 */
	public String getPrvtCustNbr() {
		return m_prvtCustNbr;
	}
	
	/**
	 * @param prvtCustNbr_ Field prvtCustNbr to be setted.
	 */
	public void setPrvtCustNbr(String prvtCustNbr_) {
		m_prvtCustNbr = prvtCustNbr_;
	}
	
	/**
	 * @return Returns prvtKeyNbr.
	 */
	public String getPrvtKeyNbr() {
		return m_prvtKeyNbr;
	}
	
	/**
	 * @param prvtKeyNbr_ Field prvtKeyNbr to be setted.
	 */
	public void setPrvtKeyNbr(String prvtKeyNbr_) {
		m_prvtKeyNbr = prvtKeyNbr_;
	}
	
	/**
	 * @return Returns wealthPotnlCode.
	 */
	public String getWealthPotnlCode() {
		return m_wealthPotnlCode;
	}
	
	/**
	 * @param wealthPotnlCode_ Field wealthPotnlCode to be setted.
	 */
	public void setWealthPotnlCode(String wealthPotnlCode_) {
		m_wealthPotnlCode = wealthPotnlCode_;
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
	 * @return Returns custPrvtStatCode.
	 */
	public String getCustPrvtStatCode() {
		return m_custPrvtStatCode;
	}
	/**
	 * @param custPrvtStatCode_ Field custPrvtStatCode to be setted.
	 */
	public void setCustPrvtStatCode(String custPrvtStatCode_) {
		m_custPrvtStatCode = custPrvtStatCode_;
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
	public String[] getDomainsEmNbr() {
		return m_domainsEmNbr;
	}
	
	/**
	 * @return
	 */
	public String[] getDomainsErNbr() {
		return m_domainsErNbr;
	}
	
	/**
	 * @param strings
	 */
	public void setDomainsEmNbr(String[] domainsEmNbr_) {
		m_domainsEmNbr = domainsEmNbr_;
	}
	
	/**
	 * @param strings
	 */
	public void setDomainsErNbr(String[] domainsErNbr_) {
		m_domainsErNbr = domainsErNbr_;
	}

	/**
	 * @return
	 */
	public String[] getErEmGrid() {
		return m_erEmGrid;
	}
	
	/**
	 * @param strings
	 */
	public void setErEmGrid(String[] erEmGrid_) {
		m_erEmGrid = erEmGrid_;
	}

	public String getRdipDescription() {
		return m_rdipDescription;
	}

	public void setRdipDescription(String description) {
		m_rdipDescription = description;
	}

}