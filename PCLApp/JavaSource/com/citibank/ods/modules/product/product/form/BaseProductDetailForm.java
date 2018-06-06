package com.citibank.ods.modules.product.product.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class BaseProductDetailForm extends BaseForm implements
    ProductDetailable
{

  private DataSet m_systemSegmentCodeDomain;

  private DataSet m_prodRiskCatCodeDomain;

  private DataSet m_prodQlfyCodeDomain;

  private DataSet m_prodSubFamlCodeDomain;

  private DataSet m_prodFamlCodeDomain;

  private DataSet m_prvtProdAggrCodeDomain;

  private DataSet m_citiGrpTieReltnPlcyIndDomain;

  private DataSet m_citiGrpTieRstrnPlcyIndDomain;

  private DataSet m_loadProductSubFamilyDomain;

  private DataSet m_loadProductQlfyDomain;

  private DataSet m_loadProductRiskDomain;

  private DataSet m_loadProductAggrDomain;

  private DataSet m_loadSegmentDomain;

  private DataSet m_loadAdministratorDomain;

  private DataSet m_loadCustodDomain;

  private DataSet m_loadAuditDomain;

  private DataSet m_loadCtlDomain;

  private DataSet m_loadManagerDomain;

  private DataSet m_loadProdStatCodeDomain;

  private DataSet m_loadProdCrTypeClassCodeDomain;

  private DataSet m_loadProdLegalClassCodeDomain;
  
  private DataSet m_assetTypeCodeDomain;
  
  private DataSet assetClassOnesrcDomain;

  // Indicador de Produto com Contrato Sob Politica 23A (Politica que trata de
  // limites de transacoes intercompany, com as regras para operacoes cobertas,
  // nao cobertas e proibidas: regulamenta as relacoes entre as empresas
  private String m_citiGrpTieReltnPlcyInd = "";

  // Indicador de Produto com Contrato Sob Politica 23B (Politica que trata de
  // limites de transacoes intercompany, com as restricoes para as operacoes
  // entre as empresas afiliadas)
  private String m_citiGrpTieRstrnPlcyInd = "";

  // Data e Hora da Ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do Usuario que efetuou a ultima atualizacao registro.
  private String m_lastUpdUserId = "";

  // CNPJ do Administrador do Produto. (Informacao cadastral do do Produto como
  // passivo do Banco)
  private String m_prodAdminCnpjNbr = "";

  // Codigo Anbid do Produto
  private String m_prodAnbidCode = "";

  // Data de Aprovacao do Produto
  private String m_prodApprvDate = "";

  // CNPJ do Auditor do Produto. (Informacao cadastral do do Produto como
  // passivo do Banco)
  private String m_prodAuditCnpjNbr = "";

  // Codigo do Produto na BMF
  private String m_prodBmfCode = "";

  // Codigo do Produto na Bovespa - Conta CBLC
  private String m_prodBovespaCode = "";

  // Codigo da Moeda
  private String m_prodCcyCode = "";

  // Codigo Cetip do Produto
  private String m_prodCetipCode = "";

  // Codigo do Produto.
  private String m_prodCode = "";

  // Data de Criacao do Produto
  private String m_prodCreateDate = "";

  // Classificacao do tipo de credito (line ou commitment)
  private String m_prodCrTypeClassCode = "";

  // CNPJ do Custodiante do Produto (Informacao cadastral do do Produto como
  // passivo do Banco)
  private String m_prodCstdyCnpjNbr = "";

  // CNPJ do Controlador do Produto (Informacao cadastral do do Produto como
  // passivo do Banco)
  private String m_prodCtlCnpjNbr = "";

  // Codigo da Familia de Produtos (redundante seguindo atributo existente no
  // BG)
  private String m_prodFamlCode = "";

  // Codigo ISO do Produto
  private String m_prodIsoCode = "";

  // Gestor do Produto (Informacao cadastral do do Produto como passivo do
  // Banco)
  private String m_prodMgmtCnpjNbr = "";

  // Nome do Produto
  private String m_prodName = "";

  // Data de Inicio de Operacao
  private String m_prodOpernStaDate = "";

  // Codigo do Sistema processador do Produto.
  private String m_prodProcSysCode = "";

  // Codigo da segmentacao do sistema processador do Produto.
  private String m_prodProcSysSegCode = "";

  // Codigo de Qualificacao do Prod uto Private
  private String m_prodQlfyCode = "";

  // Codigo da Categoria de Risco Private.
  private String m_prodRiskCatCode = "";
  
  // Classificacao de ativo no onesource
  private String assetClassOnesrc = "";

  // Codigo Selic do Produto
  private String m_prodSelicCode = "";

  // Status do Produto
  private String m_prodStatCode = "";

  // Codigo da Sub-Familia de Produtos.
  private String m_prodSubFamlCode = "";

  // Codigo da Classificacao Legal (Basileia)
  private String m_prodLegalClassCode = "";

  // Descricao do Produto
  private String m_prodText = "";

  // Codigo do Agrupador de Produtos Private.
  private String m_prvtProdAggrCode = "";

  // Codigo do sistema origem do cadastro do Produto.
  private String m_sysCode = "";

  // Codigo da segmentacao do sistema origem do cadastro do Produto.
  private String m_sysSegCode = "";

  // Data e Hora que o Usuario aprovou o Registro Cadastrado
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  // Codigo do Sistema.
  private String m_sysCodeSrc = "";

  //Número da conta produto
  private String m_prodAcctCode = "";

  //Flag - alteração de sub-família
  private String m_firstLoaded = "";
  
  //Flag que controla a busca de sub-família
  private String m_findType= "";
  
  //Codigo do Tipo de Ativo
  private String m_assetTypeCode="";	
  
  //Lista de Emissores do Produto
  private List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity;
  
  private DataSet emissorProdRiskCatCodeDomain;
  
  // Codigo da Categoria de Risco Private do Emissor.
  private String emissorProdRiskCatCode = "";
  
  //Codigo do Emissor
  private String emissorProdEmissorCode = "";
  
  //Dominio de Emissores
  private DataSet prodEmissorsCodeDomain;
  
  //Emissor selecionado para delete
  private String emissorSeqNbr;
  
  //Indicacao se envia a IA
  private String prodSentIaInd;
  
  private DataSet assocClassProdCodeDomain;
  
  private String assocClassProdCode;
  
  //Variaves para controle de Tela
  private String noVisibleBox = "infoInvest_infoRenda_";
  private String visibleBox = "infoBase_";
  
  //Variaveis FRD - Fase 3
  //Data de Aporte
  private String prodEvnConTrbDate;
  //Perfil Fundo
  private String prodFundPrflTyp;
  //CNPJ
  private String prodCnpjNbr;
  //ISIN
  private String prodIsinCode;
  //Tipo de encerramento
  private String prodCloseTypCode;
  //Tipo de abertura
  private String prodOpenTypCode;
  //Nome Original do Produto
  private String prodOrigName;
  //Data do Evento Abertura
  private String prodOpenEvnDate;
  //Taxa de Administração
  private String prodAdminRate;
  //Taxa de Performance
  private String prodPerfmRate;
  //Taxa de ingresso
  private String prodPrtfinvApplRate;
  //Taxa de saída
  private String prodExitRate;
  //Tipo cota
  private String prodQuotTypeCode;
  //Horário Fechamento
  private String prodCloseTime;
  //Cotização Aplicação
  private String prodDepQuotDateType;
  //Cotização Resgate
  private String prodWthdrCrDateType;
  //Liquidação de Aplicação
  private String prodApplLiqDateType;
  //Liquidação de Resgate
  private String prodWthdrLiqDateType;
  //Aplicação inicial mínima
  private String prodMinStaApplAmt;
  //Movimentação Mínima 
  private String prodMovMinAmt;
  //Resgate mínimo
  private String prodMinWthdrAmt;
  //Valor Permanência
  private String prodHoldMinAmt;
  //Carência
  private String prodGraceInd;
  //Data Enc. Balanço
  private String prodBalCloseDate;
  //Distribuição CVM
  private String prodCvmDistCode;
  //Condomínio
  private String prodQuotCndmCode;
  //Restrito
  private String prodRstrnCode;
  //Forma de Divulgação(Gazeta)
  private String prodGazetaDclrFormCode;
  //Classificação ANBIMA
  private String anbidFundClassCode;
  private DataSet anbidFundClassCodeDomain;
  //Divulgar como ANBIMA
  private String prodAnbidDclrCode;
  //Tributação CVM
  private String prodCvmTaxCode;
  
  private String fundDistFormTypeCode;
	private String termText;
	private String strategyStartDate;
	private String strategyCloseDate;
	private String applicationStatCode;
	private String wthdrStatCode;
	private String perfmRateText;
	private String closeDate;
	
	
  
  public List<TplRiskFamilyProdPlayerEntity> getListTplRiskFamilyProdPlayerEntity() {
		return listTplRiskFamilyProdPlayerEntity;
	}

	public void setListTplRiskFamilyProdPlayerEntity(
			List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity) {
		this.listTplRiskFamilyProdPlayerEntity = listTplRiskFamilyProdPlayerEntity;
	}

	public String getFundDistFormTypeCode() {
		return fundDistFormTypeCode;
	}

	public void setFundDistFormTypeCode(String fundDistFormTypeCode) {
		this.fundDistFormTypeCode = fundDistFormTypeCode;
	}

	public String getTermText() {
		return termText;
	}

	public void setTermText(String termText) {
		this.termText = termText;
	}

	public String getStrategyStartDate() {
		return strategyStartDate;
	}

	public void setStrategyStartDate(String strategyStartDate) {
		this.strategyStartDate = strategyStartDate;
	}

	public String getStrategyCloseDate() {
		return strategyCloseDate;
	}

	public void setStrategyCloseDate(String strategyCloseDate) {
		this.strategyCloseDate = strategyCloseDate;
	}

	public String getApplicationStatCode() {
		return applicationStatCode;
	}

	public void setApplicationStatCode(String applicationStatCode) {
		this.applicationStatCode = applicationStatCode;
	}

	public String getWthdrStatCode() {
		return wthdrStatCode;
	}

	public void setWthdrStatCode(String wthdrStatCode) {
		this.wthdrStatCode = wthdrStatCode;
	}

	public String getPerfmRateText() {
		return perfmRateText;
	}

	public void setPerfmRateText(String perfmRateText) {
		this.perfmRateText = perfmRateText;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

public BaseProductDetailForm(){
	  listTplRiskFamilyProdPlayerEntity = new ArrayList<TplRiskFamilyProdPlayerEntity>();
  }

  /**
   * @return Returns m_citiGrpTieReltnPlcyInd.
   */
  public String getCitiGrpTieReltnPlcyInd()
  {
    return m_citiGrpTieReltnPlcyInd;
  }

  /**
   * @param citiGrpTieReltnPlcyInd_ Field m_citiGrpTieReltnPlcyInd to be setted.
   */
  public void setCitiGrpTieReltnPlcyInd( String citiGrpTieReltnPlcyInd_ )
  {
    m_citiGrpTieReltnPlcyInd = citiGrpTieReltnPlcyInd_;
  }

  /**
   * @return Returns m_citiGrpTieRstrnPlcyInd.
   */
  public String getCitiGrpTieRstrnPlcyInd()
  {
    return m_citiGrpTieRstrnPlcyInd;
  }

  /**
   * @param citiGrpTieRstrnPlcyInd_ Field m_citiGrpTieRstrnPlcyInd to be setted.
   */
  public void setCitiGrpTieRstrnPlcyInd( String citiGrpTieRstrnPlcyInd_ )
  {
    m_citiGrpTieRstrnPlcyInd = citiGrpTieRstrnPlcyInd_;
  }

  /**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_prodAdminCnpjNbr.
   */
  public String getProdAdminCnpjNbr()
  {
    return m_prodAdminCnpjNbr;
  }

  /**
   * @param prodAdminCnpjNbr_ Field m_prodAdminCnpjNbr to be setted.
   */
  public void setProdAdminCnpjNbr( String prodAdminCnpjNbr_ )
  {
    m_prodAdminCnpjNbr = prodAdminCnpjNbr_;
  }

  /**
   * @return Returns m_prodAnbidCode.
   */
  public String getProdAnbidCode()
  {
    return m_prodAnbidCode;
  }

  /**
   * @param prodAnbidCode_ Field m_prodAnbidCode to be setted.
   */
  public void setProdAnbidCode( String prodAnbidCode_ )
  {
    m_prodAnbidCode = prodAnbidCode_;
  }

  /**
   * @return Returns m_prodApprvDate.
   */
  public String getProdApprvDate()
  {
    return m_prodApprvDate;
  }

  /**
   * @param prodApprvDate_ Field m_prodApprvDate to be setted.
   */
  public void setProdApprvDate( String prodApprvDate_ )
  {
    m_prodApprvDate = prodApprvDate_;
  }

  /**
   * @return Returns m_prodAuditCnpjNbr.
   */
  public String getProdAuditCnpjNbr()
  {
    return m_prodAuditCnpjNbr;
  }

  /**
   * @param prodAuditCnpjNbr_ Field m_prodAuditCnpjNbr to be setted.
   */
  public void setProdAuditCnpjNbr( String prodAuditCnpjNbr_ )
  {
    m_prodAuditCnpjNbr = prodAuditCnpjNbr_;
  }

  /**
   * @return Returns m_prodBmfCode.
   */
  public String getProdBmfCode()
  {
    return m_prodBmfCode;
  }

  /**
   * @param prodBmfCode_ Field m_prodBmfCode to be setted.
   */
  public void setProdBmfCode( String prodBmfCode_ )
  {
    m_prodBmfCode = prodBmfCode_;
  }

  /**
   * @return Returns m_prodBovespaCode.
   */
  public String getProdBovespaCode()
  {
    return m_prodBovespaCode;
  }

  /**
   * @param prodBovespaCode_ Field m_prodBovespaCode to be setted.
   */
  public void setProdBovespaCode( String prodBovespaCode_ )
  {
    m_prodBovespaCode = prodBovespaCode_;
  }

  /**
   * @return Returns m_prodCcyCode.
   */
  public String getProdCcyCode()
  {
    return m_prodCcyCode;
  }

  /**
   * @param prodCcyCode_ Field m_prodCcyCode to be setted.
   */
  public void setProdCcyCode( String prodCcyCode_ )
  {
    m_prodCcyCode = prodCcyCode_;
  }

  /**
   * @return Returns m_prodCetipCode.
   */
  public String getProdCetipCode()
  {
    return m_prodCetipCode;
  }

  /**
   * @param prodCetipCode_ Field m_prodCetipCode to be setted.
   */
  public void setProdCetipCode( String prodCetipCode_ )
  {
    m_prodCetipCode = prodCetipCode_;
  }

  /**
   * @return Returns m_prodCode.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * @param prodCode_ Field m_prodCode to be setted.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }

  /**
   * @return Returns m_prodCreateDate.
   */
  public String getProdCreateDate()
  {
    return m_prodCreateDate;
  }

  /**
   * @param prodCreateDate_ Field m_prodCreateDate to be setted.
   */
  public void setProdCreateDate( String prodCreateDate_ )
  {
    m_prodCreateDate = prodCreateDate_;
  }

  /**
   * @return Returns m_prodCrTypeClassCode.
   */
  public String getProdCrTypeClassCode()
  {
    return m_prodCrTypeClassCode;
  }

  /**
   * @param prodCrTypeClassCode_ Field m_prodCrTypeClassCode to be setted.
   */
  public void setProdCrTypeClassCode( String prodCrTypeClassCode_ )
  {
    m_prodCrTypeClassCode = prodCrTypeClassCode_;
  }

  /**
   * @return Returns m_prodCstdyCnpjNbr.
   */
  public String getProdCstdyCnpjNbr()
  {
    return m_prodCstdyCnpjNbr;
  }

  /**
   * @param prodCstdyCnpjNbr_ Field m_prodCstdyCnpjNbr to be setted.
   */
  public void setProdCstdyCnpjNbr( String prodCstdyCnpjNbr_ )
  {
    m_prodCstdyCnpjNbr = prodCstdyCnpjNbr_;
  }

  /**
   * @return Returns m_prodCtlCnpjNbr.
   */
  public String getProdCtlCnpjNbr()
  {
    return m_prodCtlCnpjNbr;
  }

  /**
   * @param prodCtlCnpjNbr_ Field m_prodCtlCnpjNbr to be setted.
   */
  public void setProdCtlCnpjNbr( String prodCtlCnpjNbr_ )
  {
    m_prodCtlCnpjNbr = prodCtlCnpjNbr_;
  }

  /**
   * @return Returns m_prodFamlCode.
   */
  public String getProdFamlCode()
  {
    return m_prodFamlCode;
  }

  /**
   * @param prodFamlCode_ Field m_prodFamlCode to be setted.
   */
  public void setProdFamlCode( String prodFamlCode_ )
  {
    m_prodFamlCode = prodFamlCode_;
  }

  /**
   * @return Returns m_prodIsoCode.
   */
  public String getProdIsoCode()
  {
    return m_prodIsoCode;
  }

  /**
   * @param prodIsoCode_ Field m_prodIsoCode to be setted.
   */
  public void setProdIsoCode( String prodIsoCode_ )
  {
    m_prodIsoCode = prodIsoCode_;
  }

  /**
   * @return Returns m_prodMgmtCnpjNbr.
   */
  public String getProdMgmtCnpjNbr()
  {
    return m_prodMgmtCnpjNbr;
  }

  /**
   * @param prodMgmtCnpjNbr_ Field m_prodMgmtCnpjNbr to be setted.
   */
  public void setProdMgmtCnpjNbr( String prodMgmtCnpjNbr_ )
  {
    m_prodMgmtCnpjNbr = prodMgmtCnpjNbr_;
  }

  /**
   * @return Returns m_prodName.
   */
  public String getProdName()
  {
    return m_prodName;
  }

  /**
   * @param prodName_ Field m_prodName to be setted.
   */
  public void setProdName( String prodName_ )
  {
    m_prodName = prodName_;
  }

  /**
   * @return Returns m_prodOpernStaDate.
   */
  public String getProdOpernStaDate()
  {
    return m_prodOpernStaDate;
  }

  /**
   * @param prodOpernStaDate_ Field m_prodOpernStaDate to be setted.
   */
  public void setProdOpernStaDate( String prodOpernStaDate_ )
  {
    m_prodOpernStaDate = prodOpernStaDate_;
  }

  /**
   * @return Returns m_prodProcSysCode.
   */
  public String getProdProcSysCode()
  {
    return m_prodProcSysCode;
  }

  /**
   * @param prodProcSysCode_ Field m_prodProcSysCode to be setted.
   */
  public void setProdProcSysCode( String prodProcSysCode_ )
  {
    m_prodProcSysCode = prodProcSysCode_;
  }

  /**
   * @return Returns m_prodProcSysSegCode.
   */
  public String getProdProcSysSegCode()
  {
    return m_prodProcSysSegCode;
  }

  /**
   * @param prodProcSysSegCode_ Field m_prodProcSysSegCode to be setted.
   */
  public void setProdProcSysSegCode( String prodProcSysSegCode_ )
  {
    m_prodProcSysSegCode = prodProcSysSegCode_;
  }

  /**
   * @return Returns m_prodQlfyCode.
   */
  public String getProdQlfyCode()
  {
    return m_prodQlfyCode;
  }

  /**
   * @param prodQlfyCode_ Field m_prodQlfyCode to be setted.
   */
  public void setProdQlfyCode( String prodQlfyCode_ )
  {
    m_prodQlfyCode = prodQlfyCode_;
  }

  /**
   * @return Returns m_prodRiskCatCode.
   */
  public String getProdRiskCatCode()
  {
    return m_prodRiskCatCode;
  }

  /**
   * @param prodRiskCatCode_ Field m_prodRiskCatCode to be setted.
   */
  public void setProdRiskCatCode( String prodRiskCatCode_ )
  {
    m_prodRiskCatCode = prodRiskCatCode_;
  }

  /**
   * @return Returns m_prodSelicCode.
   */
  public String getProdSelicCode()
  {
    return m_prodSelicCode;
  }

  /**
   * @param prodSelicCode_ Field m_prodSelicCode to be setted.
   */
  public void setProdSelicCode( String prodSelicCode_ )
  {
    m_prodSelicCode = prodSelicCode_;
  }

  /**
   * @return Returns m_prodStatCode.
   */
  public String getProdStatCode()
  {
    return m_prodStatCode;
  }

  /**
   * @param prodStatCode_ Field m_prodStatCode to be setted.
   */
  public void setProdStatCode( String prodStatCode_ )
  {
    m_prodStatCode = prodStatCode_;
  }

  /**
   * @return Returns m_prodSubFamlCode.
   */
  public String getProdSubFamlCode()
  {
    return m_prodSubFamlCode;
  }

  /**
   * @param prodSubFamlCode_ Field m_prodSubFamlCode to be setted.
   */
  public void setProdSubFamlCode( String prodSubFamlCode_ )
  {
    m_prodSubFamlCode = prodSubFamlCode_;
  }

  public String getProdLegalClassCode()
  {
    return m_prodLegalClassCode;
  }

  public void setProdLegalClassCode( String prodLegalClassCode_ )
  {
    m_prodLegalClassCode = prodLegalClassCode_;
  }

  /**
   * @return Returns m_prodText.
   */
  public String getProdText()
  {
    return m_prodText;
  }

  /**
   * @param prodText_ Field m_prodText to be setted.
   */
  public void setProdText( String prodText_ )
  {
    m_prodText = prodText_;
  }

  /**
   * @return Returns m_prvtProdAggrCode.
   */
  public String getPrvtProdAggrCode()
  {
    return m_prvtProdAggrCode;
  }

  /**
   * @param prvtProdAggrCode_ Field m_prvtProdAggrCode to be setted.
   */
  public void setPrvtProdAggrCode( String prvtProdAggrCode_ )
  {
    m_prvtProdAggrCode = prvtProdAggrCode_;
  }

  /**
   * @return Returns m_sysCode.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * @param sysCode_ Field m_sysCode to be setted.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * @return Returns m_sysSegCode.
   */
  public String getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * @param sysSegCode_ Field m_sysSegCode to be setted.
   */
  public void setSysSegCode( String sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }

  /**
   * @return Returns lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns prodQlfyCodeDomain.
   */
  public DataSet getProdQlfyCodeDomain()
  {
    return m_prodQlfyCodeDomain;
  }

  /**
   * @param prodQlfyCodeDomain_ Field prodQlfyCodeDomain to be setted.
   */
  public void setProdQlfyCodeDomain( DataSet prodQlfyCodeDomain_ )
  {
    m_prodQlfyCodeDomain = prodQlfyCodeDomain_;
  }

  /**
   * @return Returns prodRiskCodeDomain.
   */
  public DataSet getProdRiskCatCodeDomain()
  {
    return m_prodRiskCatCodeDomain;
  }

  /**
   * @param prodRiskCodeDomain_ Field prodRiskCodeDomain to be setted.
   */
  public void setProdRiskCatCodeDomain( DataSet prodRiskCatCodeDomain_ )
  {
    m_prodRiskCatCodeDomain = prodRiskCatCodeDomain_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#getSelectedProdCode()
   */
  public String getSelectedProdCode()
  {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#getSelectedSysCode()
   */
  public String getSelectedSysCode()
  {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#getSelectedSysSegCode()
   */
  public String getSelectedSysSegCode()
  {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#setSelectedProdCode(java.lang.String)
   */
  public void setSelectedProdCode( String selectedProdCode_ )
  {
    m_prodCode = selectedProdCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#setSelectedSysCode(java.lang.String)
   */
  public void setSelectedSysCode( String selectedSysCode_ )
  {
    m_sysCode = selectedSysCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductDetailable#setSelectedSysSegCode(java.lang.String)
   */
  public void setSelectedSysSegCode( String selectedSysSegCode_ )
  {
    m_sysSegCode = selectedSysSegCode_;
  }

  /**
   * @return Returns sysCodeSrc.
   */
  public String getSysCodeSrc()
  {
    return m_sysCodeSrc;
  }

  /**
   * @param sysCodeSrc_ Field sysCodeSrc to be setted.
   */
  public void setSysCodeSrc( String sysCodeSrc_ )
  {
    m_sysCodeSrc = sysCodeSrc_;
  }

  /**
   * @return Returns prodSubFamlCodeDomain.
   */
  public DataSet getProdSubFamlCodeDomain()
  {
    return m_prodSubFamlCodeDomain;
  }

  /**
   * @param prodSubFamlCodeDomain_ Field prodSubFamlCodeDomain to be setted.
   */
  public void setProdSubFamlCodeDomain( DataSet prodSubFamlCodeDomain_ )
  {
    m_prodSubFamlCodeDomain = prodSubFamlCodeDomain_;
  }

  /**
   * @return Returns prvtProdAggrCodeDomain.
   */
  public DataSet getPrvtProdAggrCodeDomain()
  {
    return m_prvtProdAggrCodeDomain;
  }

  /**
   * @param prvtProdAggrCodeDomain_ Field prvtProdAggrCodeDomain to be setted.
   */
  public void setPrvtProdAggrCodeDomain( DataSet prvtProdAggrCodeDomain_ )
  {
    m_prvtProdAggrCodeDomain = prvtProdAggrCodeDomain_;
  }

  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_SYS_CODE_DESCRIPTION,
                                    m_sysCode,
                                    BaseTplProductEntity.C_SYS_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_PROC_SYS_CODE_DESCRIPTION,
                                    m_prodProcSysCode,
                                    BaseTplProductEntity.C_PROD_PROC_SYS_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_PROC_SYS_SEG_CODE_DESCRIPTION,
                                    m_prodProcSysSegCode,
                                    BaseTplProductEntity.C_PROD_PROC_SYS_SEG_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CODE_DESCRIPTION,
                                    m_prodCode,
                                    BaseTplProductEntity.C_PROD_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_NAME_DESCRIPTION,
                                    m_prodName,
                                    BaseTplProductEntity.C_PROD_NAME_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_TEXT_DESCRIPTION,
                                    m_prodText,
                                    BaseTplProductEntity.C_PROD_TEXT_SIZE,
                                    errors );
    ODSValidator.validateDate(
                               BaseProductDetailFncVO.C_PROD_CREATE_DATE_DESCRIPTION,
                               m_prodCreateDate, errors );
    ODSValidator.validateDate(
                               BaseProductDetailFncVO.C_PROD_OPERN_STA_DATE_DESCRIPTION,
                               m_prodOpernStaDate, errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CR_TYPE_CLASS_CODE_DESCRIPTION,
                                    m_prodCrTypeClassCode,
                                    BaseTplProductEntity.C_PROD_CR_TYPE_CLASS_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CCY_CODE_DESCRIPTION,
                                    m_prodCcyCode,
                                    BaseTplProductEntity.C_PROD_CCY_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_SELIC_CODE_DESCRIPTION,
                                    m_prodSelicCode,
                                    BaseTplProductEntity.C_PROD_SELIC_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_BOVESPA_CODE_DESCRIPTION,
                                    m_prodBovespaCode,
                                    BaseTplProductEntity.C_PROD_BOVESPA_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_BMF_CODE_DESCRIPTION,
                                    m_prodBmfCode,
                                    BaseTplProductEntity.C_PROD_BMF_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_ISO_CODE_DESCRIPTION,
                                    m_prodIsoCode,
                                    BaseTplProductEntity.C_PROD_ISO_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_ANBID_CODE_DESCRIPTION,
                                    m_prodAnbidCode,
                                    BaseTplProductEntity.C_PROD_ANBID_CODE_SIZE,
                                    errors );
    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CETIP_CODE_DESCRIPTION,
                                    m_prodCetipCode,
                                    BaseTplProductEntity.C_PROD_CETIP_CODE_SIZE,
                                    errors );

    return errors;
  }

  /**
   * @return Returns systemSegmentCodeDomain.
   */
  public DataSet getSystemSegmentCodeDomain()
  {
    return m_systemSegmentCodeDomain;
  }

  /**
   * @param systemSegmentCodeDomain_ Field systemSegmentCodeDomain to be setted.
   */
  public void setSystemSegmentCodeDomain( DataSet sytemSegmentCodeDomain_ )
  {
    m_systemSegmentCodeDomain = sytemSegmentCodeDomain_;
  }

  /**
   * @return Returns citiGrpTieReltnPlcyIndDomain.
   */
  public DataSet getCitiGrpTieReltnPlcyIndDomain()
  {
    return m_citiGrpTieReltnPlcyIndDomain;
  }

  /**
   * @param citiGrpTieReltnPlcyIndDomain_ Field citiGrpTieReltnPlcyIndDomain to
   *          be setted.
   */
  public void setCitiGrpTieReltnPlcyIndDomain(
                                              DataSet citiGrpTieReltnPlcyIndDomain_ )
  {
    m_citiGrpTieReltnPlcyIndDomain = citiGrpTieReltnPlcyIndDomain_;
  }

  /**
   * @return Returns citiGrpTieRstrnPlcyIndDomain.
   */
  public DataSet getCitiGrpTieRstrnPlcyIndDomain()
  {
    return m_citiGrpTieRstrnPlcyIndDomain;
  }

  /**
   * @param citiGrpTieRstrnPlcyIndDomain_ Field citiGrpTieRstrnPlcyIndDomain to
   *          be setted.
   */
  public void setCitiGrpTieRstrnPlcyIndDomain(
                                              DataSet citiGrpTieRstrnPlcyIndDomain_ )
  {
    m_citiGrpTieRstrnPlcyIndDomain = citiGrpTieRstrnPlcyIndDomain_;
  }

  /**
   * @return Returns loadProductAggrDomain.
   */
  public DataSet getLoadProductAggrDomain()
  {
    return m_loadProductAggrDomain;
  }

  /**
   * @param loadProductAggrDomain_ Field loadProductAggrDomain to be setted.
   */
  public void setLoadProductAggrDomain( DataSet loadProductAggrDomain_ )
  {
    m_loadProductAggrDomain = loadProductAggrDomain_;
  }

  /**
   * @return Returns loadProductQlfyDomain.
   */
  public DataSet getLoadProductQlfyDomain()
  {
    return m_loadProductQlfyDomain;
  }

  /**
   * @param loadProductQlfyDomain_ Field loadProductQlfyDomain to be setted.
   */
  public void setLoadProductQlfyDomain( DataSet loadProductQlfyDomain_ )
  {
    m_loadProductQlfyDomain = loadProductQlfyDomain_;
  }

  /**
   * @return Returns loadProductRiskDomain.
   */
  public DataSet getLoadProductRiskDomain()
  {
    return m_loadProductRiskDomain;
  }

  /**
   * @param loadProductRiskDomain_ Field loadProductRiskDomain to be setted.
   */
  public void setLoadProductRiskDomain( DataSet loadProductRiskDomain_ )
  {
    m_loadProductRiskDomain = loadProductRiskDomain_;
  }

  /**
   * @return Returns loadProductSubFamilyDomain.
   */
  public DataSet getLoadProductSubFamilyDomain()
  {
    return m_loadProductSubFamilyDomain;
  }

  /**
   * @param loadProductSubFamilyDomain_ Field loadProductSubFamilyDomain to be
   *          setted.
   */
  public void setLoadProductSubFamilyDomain( DataSet loadProductSubFamilyDomain_ )
  {
    m_loadProductSubFamilyDomain = loadProductSubFamilyDomain_;
  }

  /**
   * @return Returns loadSegmentDomain.
   */
  public DataSet getLoadSegmentDomain()
  {
    return m_loadSegmentDomain;
  }

  /**
   * @param loadSegmentDomain_ Field loadSegmentDomain to be setted.
   */
  public void setLoadSegmentDomain( DataSet loadSegmentDomain_ )
  {
    m_loadSegmentDomain = loadSegmentDomain_;
  }

  /**
   * @return Returns loadAdministratorDomain.
   */
  public DataSet getLoadAdministratorDomain()
  {
    return m_loadAdministratorDomain;
  }

  /**
   * @param loadAdministratorDomain_ Field loadAdministratorDomain to be setted.
   */
  public void setLoadAdministratorDomain( DataSet loadAdministratorDomain_ )
  {
    m_loadAdministratorDomain = loadAdministratorDomain_;
  }

  /**
   * @return Returns loadAuditDomain.
   */
  public DataSet getLoadAuditDomain()
  {
    return m_loadAuditDomain;
  }

  /**
   * @param loadAuditDomain_ Field loadAuditDomain to be setted.
   */
  public void setLoadAuditDomain( DataSet loadAuditDomain_ )
  {
    m_loadAuditDomain = loadAuditDomain_;
  }

  /**
   * @return Returns loadCtlDomain.
   */
  public DataSet getLoadCtlDomain()
  {
    return m_loadCtlDomain;
  }

  /**
   * @param loadCtlDomain_ Field loadCtlDomain to be setted.
   */
  public void setLoadCtlDomain( DataSet loadCtlDomain_ )
  {
    m_loadCtlDomain = loadCtlDomain_;
  }

  /**
   * @return Returns loadCustodDomain.
   */
  public DataSet getLoadCustodDomain()
  {
    return m_loadCustodDomain;
  }

  /**
   * @param loadCustodDomain_ Field loadCustodDomain to be setted.
   */
  public void setLoadCustodDomain( DataSet loadCustodDomain_ )
  {
    m_loadCustodDomain = loadCustodDomain_;
  }

  /**
   * @return Returns loadManagerDomain.
   */
  public DataSet getLoadManagerDomain()
  {
    return m_loadManagerDomain;
  }

  /**
   * @param loadManagerDomain_ Field loadManagerDomain to be setted.
   */
  public void setLoadManagerDomain( DataSet loadManagerDomain_ )
  {
    m_loadManagerDomain = loadManagerDomain_;
  }

  /**
   * @return Returns loadprodCrTypeClassCodeDomain.
   */
  public DataSet getLoadprodCrTypeClassCodeDomain()
  {
    return m_loadProdCrTypeClassCodeDomain;
  }

  /**
   * @param loadprodCrTypeClassCodeDomain_ Field loadprodCrTypeClassCodeDomain
   *          to be setted.
   */
  public void setLoadprodCrTypeClassCodeDomain(
                                               DataSet loadprodCrTypeClassCodeDomain_ )
  {
    m_loadProdCrTypeClassCodeDomain = loadprodCrTypeClassCodeDomain_;
  }

  /**
   * @return Returns loadProdLegalClassCodeDomain.
   */
  public DataSet getLoadProdLegalClassCodeDomain()
  {
    return m_loadProdLegalClassCodeDomain;
  }

  /**
   * @param loadProdLegalClassCodeDomain__ Field loadProdLegalClassCodeDomain to
   *          be setted.
   */
  public void setLoadProdLegalClassCodeDomain(
                                              DataSet loadProdLegalClassCodeDomain_ )
  {
    m_loadProdLegalClassCodeDomain = loadProdLegalClassCodeDomain_;
  }

  /**
   * @return Returns loadProdStatCodeDomain.
   */
  public DataSet getLoadProdStatCodeDomain()
  {
    return m_loadProdStatCodeDomain;
  }

  /**
   * @param loadProdStatCodeDomain_ Field loadProdStatCodeDomain to be setted.
   */
  public void setLoadProdStatCodeDomain( DataSet loadProdStatCodeDomain_ )
  {
    m_loadProdStatCodeDomain = loadProdStatCodeDomain_;
  }

  /**
   * @return Returns prodFamlCodeDomain.
   */
  public DataSet getProdFamlCodeDomain()
  {
    return m_prodFamlCodeDomain;
  }

  /**
   * @param prodFamlCodeDomain_ Field prodFamlCodeDomain to be setted.
   */
  public void setProdFamlCodeDomain( DataSet prodFamlCodeDomain_ )
  {
    m_prodFamlCodeDomain = prodFamlCodeDomain_;
  }

  /**
   * @return Returns prodAcctCode.
   */
  public String getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_ Field prodAcctCode to be setted.
   */
  public void setProdAcctCode( String prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  public String getFirstLoaded()
  {
    return m_firstLoaded;
  }

  public void setFirstLoaded( String firstLoaded_ )
  {
    m_firstLoaded = firstLoaded_;
  }

  /**
  * @return
  */
  public String getFindType() {
	return m_findType;
  }

  /**
  * @param string
  */
  public void setFindType(String m_findType_) 
  {
	m_findType = m_findType_;
  }
 
  /**
   * @param set
   */
  public void setAssetTypeCodeDomain(DataSet assetTypeCodeDomain)
  {
	  m_assetTypeCodeDomain = assetTypeCodeDomain;
  }

  /**
   * @return
   */
  public DataSet getAssetTypeCodeDomain()
  {
	return m_assetTypeCodeDomain;
  }

  /**
   * @return
   */
  public String getAssetTypeCode() 
  {
	return m_assetTypeCode;
  }
	
  /**
   * @param string
   */
  public void setAssetTypeCode(String assetTypeCode)
  {
    m_assetTypeCode = assetTypeCode;
  }

  /**
   * Lista de Emissor com o risco
   * 
   * @return
   */
  public List<TplRiskFamilyProdPlayerEntity> getListProductPlayerRiskVO() {
	return listTplRiskFamilyProdPlayerEntity;
  }

  public void setListProductPlayerRiskVO(List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity) {
	this.listTplRiskFamilyProdPlayerEntity = listTplRiskFamilyProdPlayerEntity;
  }

  public DataSet getEmissorProdRiskCatCodeDomain() {
	return emissorProdRiskCatCodeDomain;
  }

  public void setEmissorProdRiskCatCodeDomain(DataSet emissorProdRiskCatCodeDomain) {
	this.emissorProdRiskCatCodeDomain = emissorProdRiskCatCodeDomain;
  }

  /**
   * Codigo da categoria selecionada no combo da categoria de risco do Emissor
   * @return
   */  
  public String getEmissorProdRiskCatCode() {
	return emissorProdRiskCatCode;
  }

  public void setEmissorProdRiskCatCode(String emissorProdRiskCatCode) {
	this.emissorProdRiskCatCode = emissorProdRiskCatCode;
  }

  /**
   * Codigo do emissor selecionado no combo de emissor 
   * @return
   */
  public String getEmissorProdEmissorCode() {
	return emissorProdEmissorCode;
  }

  public void setEmissorProdEmissorCode(String emissorProdEmissorCode) {
	this.emissorProdEmissorCode = emissorProdEmissorCode;
  }

  public DataSet getProdEmissorsCodeDomain() {
	return prodEmissorsCodeDomain;
  }

  public void setProdEmissorsCodeDomain(DataSet prodEmissorsCodeDomain) {
	this.prodEmissorsCodeDomain = prodEmissorsCodeDomain;
  }

  public String getEmissorSeqNbr() {
	return emissorSeqNbr;
  }

  public void setEmissorSeqNbr(String emissorSeqNbr) {
	this.emissorSeqNbr = emissorSeqNbr;
  }

  public String getProdSentIaInd() {
	return prodSentIaInd;
  }

  public void setProdSentIaInd(String prodSentIaInd) {
	this.prodSentIaInd = prodSentIaInd;
  }

public DataSet getAssocClassProdCodeDomain() {
	return assocClassProdCodeDomain;
}

public void setAssocClassProdCodeDomain(DataSet assocClassProdCodeDomain) {
	this.assocClassProdCodeDomain = assocClassProdCodeDomain;
}

public String getAssocClassProdCode() {
	return assocClassProdCode;
}

public void setAssocClassProdCode(String assocClassProdCode) {
	this.assocClassProdCode = assocClassProdCode;
}

public String getNoVisibleBox() {
	return noVisibleBox;
}

public void setNoVisibleBox(String noVisibleBox) {
	this.noVisibleBox = noVisibleBox;
}

public String getVisibleBox() {
	return visibleBox;
}

public void setVisibleBox(String visibleBox) {
	this.visibleBox = visibleBox;
}

public String getAnbidFundClassCode() {
	return anbidFundClassCode;
}

public void setAnbidFundClassCode(String anbidFundClassCode) {
	this.anbidFundClassCode = anbidFundClassCode;
}

public String getProdAdminRate() {
	return prodAdminRate;
}

public void setProdAdminRate(String prodAdminRate) {
	this.prodAdminRate = prodAdminRate;
}

public String getProdAnbidDclrCode() {
	return prodAnbidDclrCode;
}

public void setProdAnbidDclrCode(String prodAnbidDclrCode) {
	this.prodAnbidDclrCode = prodAnbidDclrCode;
}

public String getProdBalCloseDate() {
	return prodBalCloseDate;
}

public void setProdBalCloseDate(String prodBalCloseDate) {
	this.prodBalCloseDate = prodBalCloseDate;
}

public String getProdCloseTime() {
	return prodCloseTime;
}

public void setProdCloseTime(String prodCloseTime) {
	this.prodCloseTime = prodCloseTime;
}

public String getProdCloseTypCode() {
	return prodCloseTypCode;
}

public void setProdCloseTypCode(String prodCloseTypCode) {
	this.prodCloseTypCode = prodCloseTypCode;
}

public String getProdCnpjNbr() {
	return prodCnpjNbr;
}

public void setProdCnpjNbr(String prodCnpjNbr) {
	this.prodCnpjNbr = prodCnpjNbr;
}

public String getProdCvmDistCode() {
	return prodCvmDistCode;
}

public void setProdCvmDistCode(String prodCvmDistCode) {
	this.prodCvmDistCode = prodCvmDistCode;
}

public String getProdCvmTaxCode() {
	return prodCvmTaxCode;
}

public void setProdCvmTaxCode(String prodCvmTaxCode) {
	this.prodCvmTaxCode = prodCvmTaxCode;
}

public String getProdDepQuotDateType() {
	return prodDepQuotDateType;
}

public void setProdDepQuotDateType(String prodDepQuotDateType) {
	this.prodDepQuotDateType = prodDepQuotDateType;
}

public String getProdEvnConTrbDate() {
	return prodEvnConTrbDate;
}

public void setProdEvnConTrbDate(String prodEvnConTrbDate) {
	this.prodEvnConTrbDate = prodEvnConTrbDate;
}

public String getProdExitRate() {
	return prodExitRate;
}

public void setProdExitRate(String prodExitRate) {
	this.prodExitRate = prodExitRate;
}

public String getProdFundPrflTyp() {
	return prodFundPrflTyp;
}

public void setProdFundPrflTyp(String prodFundPrflTyp) {
	this.prodFundPrflTyp = prodFundPrflTyp;
}

public String getProdGazetaDclrFormCode() {
	return prodGazetaDclrFormCode;
}

public void setProdGazetaDclrFormCode(String prodGazetaDclrFormCode) {
	this.prodGazetaDclrFormCode = prodGazetaDclrFormCode;
}

public String getProdGraceInd() {
	return prodGraceInd;
}

public void setProdGraceInd(String prodGraceInd) {
	this.prodGraceInd = prodGraceInd;
}

public String getProdHoldMinAmt() {
	return prodHoldMinAmt;
}

public void setProdHoldMinAmt(String prodHoldMinAmt) {
	this.prodHoldMinAmt = prodHoldMinAmt;
}

public String getProdIsinCode() {
	return prodIsinCode;
}

public void setProdIsinCode(String prodIsinCode) {
	this.prodIsinCode = prodIsinCode;
}

public String getProdMinStaApplAmt() {
	return prodMinStaApplAmt;
}

public void setProdMinStaApplAmt(String prodMinStaApplAmt) {
	this.prodMinStaApplAmt = prodMinStaApplAmt;
}

public String getProdMinWthdrAmt() {
	return prodMinWthdrAmt;
}

public void setProdMinWthdrAmt(String prodMinWthdrAmt) {
	this.prodMinWthdrAmt = prodMinWthdrAmt;
}

public String getProdMovMinAmt() {
	return prodMovMinAmt;
}

public void setProdMovMinAmt(String prodMovMinAmt) {
	this.prodMovMinAmt = prodMovMinAmt;
}

public String getProdOpenEvnDate() {
	return prodOpenEvnDate;
}

public void setProdOpenEvnDate(String prodOpenEvnDate) {
	this.prodOpenEvnDate = prodOpenEvnDate;
}

public String getProdOpenTypCode() {
	return prodOpenTypCode;
}

public void setProdOpenTypCode(String prodOpenTypCode) {
	this.prodOpenTypCode = prodOpenTypCode;
}

public String getProdOrigName() {
	return prodOrigName;
}

public void setProdOrigName(String prodOrigName) {
	this.prodOrigName = prodOrigName;
}

public String getProdPerfmRate() {
	return prodPerfmRate;
}

public void setProdPerfmRate(String prodPerfmRate) {
	this.prodPerfmRate = prodPerfmRate;
}

public String getProdPrtfinvApplRate() {
	return prodPrtfinvApplRate;
}

public void setProdPrtfinvApplRate(String prodPrtfinvApplRate) {
	this.prodPrtfinvApplRate = prodPrtfinvApplRate;
}

public String getProdQuotCndmCode() {
	return prodQuotCndmCode;
}

public void setProdQuotCndmCode(String prodQuotCndmCode) {
	this.prodQuotCndmCode = prodQuotCndmCode;
}

public String getProdQuotTypeCode() {
	return prodQuotTypeCode;
}

public void setProdQuotTypeCode(String prodQuotTypeCode) {
	this.prodQuotTypeCode = prodQuotTypeCode;
}

public String getProdRstrnCode() {
	return prodRstrnCode;
}

public void setProdRstrnCode(String prodRstrnCode) {
	this.prodRstrnCode = prodRstrnCode;
}

public String getProdWthdrCrDateType() {
	return prodWthdrCrDateType;
}

public void setProdWthdrCrDateType(String prodWthdrCrDateType) {
	this.prodWthdrCrDateType = prodWthdrCrDateType;
}

public String getProdApplLiqDateType() {
	return prodApplLiqDateType;
}

public void setProdApplLiqDateType(String prodApplLiqDateType) {
	this.prodApplLiqDateType = prodApplLiqDateType;
}

public String getProdWthdrLiqDateType() {
	return prodWthdrLiqDateType;
}

public void setProdWthdrLiqDateType(String prodWthdrLiqDateType) {
	this.prodWthdrLiqDateType = prodWthdrLiqDateType;
}

public DataSet getAnbidFundClassCodeDomain() {
	return anbidFundClassCodeDomain;
}

public void setAnbidFundClassCodeDomain(DataSet anbidFundClassCodeDomain) {
	this.anbidFundClassCodeDomain = anbidFundClassCodeDomain;
}

public DataSet getAssetClassOnesrcDomain() {
	return assetClassOnesrcDomain;
}

public void setAssetClassOnesrcDomain(DataSet assetClassOnesrc) {
	this.assetClassOnesrcDomain = assetClassOnesrc;
}

public String getAssetClassOnesrc() {
	return assetClassOnesrc;
}

public void setAssetClassOnesrc(String assetClassOnesrc) {
	this.assetClassOnesrc = assetClassOnesrc;
}    
  
}