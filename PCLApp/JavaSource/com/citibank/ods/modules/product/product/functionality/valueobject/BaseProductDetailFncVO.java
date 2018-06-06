/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.modules.product.product.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProductCorpEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;

/*alterações no labels, retirada do CNPJ*/
/**
 * @author leonardo.nakada
 *  
 */
public class BaseProductDetailFncVO extends BaseODSFncVO
{
  public static final String C_SYS_SEG_CODE_DESCRIPTION = "Código do Segmento do Sistema";

  public static final String C_SYS_CODE_DESCRIPTION = "Código Sistema";

  public static final String C_PROD_PROC_SYS_CODE_DESCRIPTION = "Código Sistema Processador";

  public static final String C_PROD_PROC_SYS_SEG_CODE_DESCRIPTION = "Segmento Sistema Processador";

  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Codigo Familia Processador";

  public static final String C_PROD_CODE_DESCRIPTION = "Código do Produto";

  public static final String C_PROD_NAME_DESCRIPTION = "Nome Produto";

  public static final String C_PROD_TEXT_DESCRIPTION = "Descrição do Produto";

  public static final String C_PROD_CREATE_DATE_DESCRIPTION = "Data Criação Produto";

  public static final String C_PROD_OPERN_STA_DATE_DESCRIPTION = "Data Início Operação";

  public static final String C_PROD_SUB_FAML_CODE_DESCRIPTION = "Código Sub-Familia";
  
  public static final String C_PROD_EMISSOR_CAT_RISK_CODE = "Categoria de Risco";

  public static final String C_PROD_CR_TYPE_CLASS_CODE_DESCRIPTION = "Classificação Tipo Credito";

  public static final String C_PROD_CCY_CODE_DESCRIPTION = "Código Moeda";

  public static final String C_PROD_SELIC_CODE_DESCRIPTION = "Código Selic";

  public static final String C_PROD_BOVESPA_CODE_DESCRIPTION = "Codigo Bovespa";

  public static final String C_PROD_BMF_CODE_DESCRIPTION = "Código BMF";

  public static final String C_PROD_ISO_CODE_DESCRIPTION = "Codigo ISO";

  public static final String C_PROD_ANBID_CODE_DESCRIPTION = "Código Anbid";

  public static final String C_PROD_CETIP_CODE_DESCRIPTION = "Codigo Cetip";

  public static final String C_PROD_ADMIN_CNPJ_NBR_DESCRIPTION = "Administrador";

  public static final String C_PROD_CSTDY_CNPJ_NBR_DESCRIPTION = "Custodiante";

  public static final String C_PROD_AUDIT_CNPJ_NBR_DESCRIPTION = "Auditor";

  public static final String C_PROD_CTL_CNPJ_NBR_DESCRIPTION = "Controlador";

  public static final String C_PROD_MGMT_CNPJ_NBR_DESCRIPTION = "Gestor:";

  public static final String C_PROD_STAT_CODE = "Status no Processador";
  
  public static final String C_PROD_SENT_IA_IND = "Enviar Produto para o IA";
  
  public static final String C_ASSOC_CLASS_PROD_CODE = "Classificação Interface Global";
  
  private DataSet m_prodQlfyCodeDomain;

  private BigInteger m_prodFamlCode;

  private DataSet m_prodRiskCatCodeDomain;

  private DataSet m_prodAggrCodeDomain;

  private DataSet m_prodSubFamlCodeDomain;

  private DataSet m_prodFamlCodeDomain;

  private DataSet m_prvtProdAggrCodeDomain;

  private DataSet m_systemSegmentDomain;

  private DataSet m_citiGrpTieReltnPlcyIndDomain;

  private DataSet m_citiGrpTieRstrnPlcyIndDomain;

  private DataSet m_loadAdministratorDomain;

  private DataSet m_loadCtlDomain;

  private DataSet m_loadCustodDomain;

  private DataSet m_loadManagerDomain;

  private DataSet m_loadAuditDomain;

  private DataSet m_loadProdCrTypeClassCodeDomain;

  private DataSet m_loadProdLegalClassCodeDomain;

  private DataSet m_loadProdStatCodeDomain;
  
  private DataSet m_assetTypeCodeDomain;
  
  private DataSet m_loadEmissorDomain;

  private BigInteger m_prodSubFamlCode = null;
  
  private boolean m_subFamFromUpdate = false;
  
  // Codigo da Categoria de Risco Private do Emissor.
  private String emissorProdRiskCatCode = "";
  
  // Classificacao do produto no onesource
  private String assetClassOnesrc = "";
  
  //Codigo do Emissor
  private String emissorProdEmissorCode = "";
  
  //Dominio de Emissores
  private DataSet prodEmissorsCodeDomain;
  
  //Emissor selecionado para delete
  private String emissorSeqNbr;  
  
  private DataSet assocClassProdCodeDomain;
  
  //Classificação de produtos Onesource
  private DataSet assetClassOnesrcDomain;
  
  //Fase 3
  protected BaseTplProductCorpEntity baseTplProductCorpEntity = null;


  private String fundDistFormTypeCode;
	private String termText;
	private Date strategyStartDate;
	private Date strategyCloseDate;
	private String applicationStatCode;
	private String wthdrStatCode;
	private String perfmRateText;
	private Date closeDate;
	
	
  
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

	public Date getStrategyStartDate() {
		return strategyStartDate;
	}

	public void setStrategyStartDate(Date strategyStartDate) {
		this.strategyStartDate = strategyStartDate;
	}

	public Date getStrategyCloseDate() {
		return strategyCloseDate;
	}

	public void setStrategyCloseDate(Date strategyCloseDate) {
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

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
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

  protected BaseTplProductEntity m_baseTplProductEntity = null;

  private String m_sysCodeSrc;

  //Flag - alteração de sub-família
  private boolean m_isFirstLoaded = false;

  /**
   * @return Returns the baseTplProductEntity.
   */
  public BaseTplProductEntity getBaseTplProductEntity()
  {
    return m_baseTplProductEntity;
  }

  /**
   * @param baseTplProductEntity_ The baseTplProductEntity to set.
   */
  public void setBaseTplProductEntity(
                                      BaseTplProductEntity baseTplProductEntity_ )
  {
    m_baseTplProductEntity = baseTplProductEntity_;
  }

  /**
   * @return Returns prodAggrCodeDomain.
   */
  public DataSet getProdAggrCodeDomain()
  {
    return m_prodAggrCodeDomain;
  }

  /**
   * @param prodAggrCodeDomain_ Field prodAggrCodeDomain to be setted.
   */
  public void setProdAggrCodeDomain( DataSet prodAggrCodeDomain_ )
  {
    m_prodAggrCodeDomain = prodAggrCodeDomain_;
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
   * @return Returns prodRiskCatCodeDomain.
   */
  public DataSet getProdRiskCatCodeDomain()
  {
    return m_prodRiskCatCodeDomain;
  }

  /**
   * @param prodRiskCodeDomain_ Field prodRiskCodeDomain to be setted.
   */
  public void setProdRiskCatCodeDomain( DataSet prodRiskCodeDomain_ )
  {
    m_prodRiskCatCodeDomain = prodRiskCodeDomain_;
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
   * @return Returns segmentDomain.
   */
  public DataSet getSystemSegmentDomain()
  {
    return m_systemSegmentDomain;
  }

  /**
   * @param segmentDomain_ Field segmentDomain to be setted.
   */
  public void setSystemSegmentDomain( DataSet systemSegmentDomain_ )
  {
    m_systemSegmentDomain = systemSegmentDomain_;
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
  public void setLoadProdCrTypeClassCodeDomain(
                                               DataSet loadProdCrTypeClassCodeDomain_ )
  {
    m_loadProdCrTypeClassCodeDomain = loadProdCrTypeClassCodeDomain_;
  }
  
  /**
   * @return Returns loadProdLegalClassCodeDomain.
   */
  public DataSet getLoadProdLegalClassCodeDomain()
  {
    return m_loadProdLegalClassCodeDomain;
  }
  
  /**
   * @param loadProdLegalClassCodeDomain_ Field loadProdLegalClassCodeDomain
   *          to be setted.
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
   * @return Returns prodFamlCode.
   */
  public BigInteger getProdFamlCode()
  {
    return m_prodFamlCode;
  }

  /**
   * @param prodFamlCode_ Field prodFamlCode to be setted.
   */
  public void setProdFamlCode( BigInteger prodFamlCode_ )
  {
    m_prodFamlCode = prodFamlCode_;
  }

  /**
   * @return Returns prodSubFamlCode.
   */
  public BigInteger getProdSubFamlCode()
  {
    return m_prodSubFamlCode;
  }

  /**
   * @param prodSubFamlCode_ Field prodSubFamlCode to be setted.
   */
  public void setProdSubFamlCode( BigInteger prodSubFamlCode_ )
  {
    m_prodSubFamlCode = prodSubFamlCode_;
  }

  /**
   * @return Returns isFirstLoaded.
   */
  public boolean isFirstLoaded()
  {
    return m_isFirstLoaded;
  }

  /**
   * @param isFirstLoaded_ Field isFirstLoaded to be setted.
   */
  public void setFirstLoaded( boolean isFirstLoaded_ )
  {
    m_isFirstLoaded = isFirstLoaded_;
  }

  /**
   * @return
   */
  public boolean isSubFamFromUpdate() {
	return m_subFamFromUpdate;
  }

  /**
  * @param b
  */
  public void setSubFamFromUpdate(boolean m_subFamFromUpdate_) {
	m_subFamFromUpdate = m_subFamFromUpdate_;
  }

  /**
   * @param set
   */
  public void setAssetTypeCodeDomain(DataSet assetTypeCodeDomain) {
	  m_assetTypeCodeDomain = assetTypeCodeDomain;
  }


	public String getEmissorProdEmissorCode() {
		return emissorProdEmissorCode;
	}

	public void setEmissorProdEmissorCode(String emissorProdEmissorCode) {
		this.emissorProdEmissorCode = emissorProdEmissorCode;
	}

	public String getEmissorProdRiskCatCode() {
		return emissorProdRiskCatCode;
	}

	public void setEmissorProdRiskCatCode(String emissorProdRiskCatCode) {
		this.emissorProdRiskCatCode = emissorProdRiskCatCode;
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

	public DataSet getAssocClassProdCodeDomain() {
		return assocClassProdCodeDomain;
	}

	public void setAssocClassProdCodeDomain(DataSet assocClassProdCodeDomain) {
		this.assocClassProdCodeDomain = assocClassProdCodeDomain;
	}

	/**
	 * @return
	 */
	public DataSet getAssetTypeCodeDomain() {
		return m_assetTypeCodeDomain;
	}
	
	public void setLoadEmissorDomain(DataSet m_loadEmissorDomain){
		this.m_loadEmissorDomain = m_loadEmissorDomain;
	}
	
	public DataSet getLoadEmissorDomain(){
		return m_loadEmissorDomain;
	}

	public BaseTplProductCorpEntity getBaseTplProductCorpEntity() {
		return baseTplProductCorpEntity;
	}

	public void setBaseTplProductCorpEntity(
			BaseTplProductCorpEntity baseTplProductCorpEntity) {
		this.baseTplProductCorpEntity = baseTplProductCorpEntity;
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