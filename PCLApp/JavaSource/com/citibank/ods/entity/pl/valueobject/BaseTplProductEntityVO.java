package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
import com.citibank.ods.entity.pl.BaseTplRiskFamilyProdPlayerEntity;
/**
 * Classe que instancia os valores correspondente a um registro da tabela :
 * BaseTplProduct
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public class BaseTplProductEntityVO extends BaseEntityVO
{

  private String m_citiGrpTieReltnPlcyInd;

  private String m_citiGrpTieRstrnPlcyInd;

  private Date m_lastUpdDate;

  private String m_lastUpdUserId;

  private String m_prodAdminCnpjNbr;

  private String m_prodAnbidCode;

  private Date m_prodApprvDate;

  private String m_prodAuditCnpjNbr;

  private String m_prodBmfCode;

  private String m_prodBovespaCode;

  private String m_prodCcyCode;

  private String m_prodCetipCode;

  private String m_prodCode;

  private Date m_prodCreateDate;

  private String m_prodCrTypeClassCode;

  private String m_prodCstdyCnpjNbr;

  private String m_prodCtlCnpjNbr;

  private String m_prodFamlCode;

  private String m_prodIsoCode;

  private String m_prodMgmtCnpjNbr;

  private String m_prodName;

  private Date m_prodOpernStaDate;

  private String m_prodProcSysCode;

  private BigInteger m_prodProcSysSegCode;

  private BigInteger m_prodQlfyCode;

  private BigInteger m_prodRiskCatCode;

  private String m_prodSelicCode;

  private String m_prodStatCode;

  private BigInteger m_prodSubFamlCode;

  private String m_prodText;

  private String m_prvtProdAggrCode;

  private String m_sysCode;

  private BigInteger m_sysSegCode;

  private String m_prodLegalClassCode;

  private String m_prodAcctCode;
  
  private BigInteger m_assetTypeCode;
  
  //Indicacao se envia a IA
  private String prodSentIaInd; 
  
  //Classificacao Global
  private String assocClassProdCode;  
  private String prclasProdAssetClassCode;
  private String prclasProdTypeCode;
  private String prclasProdStypCode;
  
  //Fase 3
  private String prodIsinCode;
  
  //Classificacao do produto no onesource
  private String assetClassOnesrc;

  

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

	public String getApplicationStatCode() {
		return applicationStatCode;
	}

	public void setApplicationStatCode(String applicationStatCode) {
		this.applicationStatCode = applicationStatCode;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
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

/**
   * @return Returns the citiGrpTieReltnPlcyInd.
   */
  public String getCitiGrpTieReltnPlcyInd()
  {
    return m_citiGrpTieReltnPlcyInd;
  }

  /**
   * @param citiGrpTieReltnPlcyInd_ The citiGrpTieReltnPlcyInd to set.
   */
  public void setCitiGrpTieReltnPlcyInd( String citiGrpTieReltnPlcyInd_ )
  {
    m_citiGrpTieReltnPlcyInd = citiGrpTieReltnPlcyInd_;
  }

  /**
   * @return Returns the citiGrpTieRstrnPlcyInd.
   */
  public String getCitiGrpTieRstrnPlcyInd()
  {
    return m_citiGrpTieRstrnPlcyInd;
  }

  /**
   * @param citiGrpTieRstrnPlcyInd_ The citiGrpTieRstrnPlcyInd to set.
   */
  public void setCitiGrpTieRstrnPlcyInd( String citiGrpTieRstrnPlcyInd_ )
  {
    m_citiGrpTieRstrnPlcyInd = citiGrpTieRstrnPlcyInd_;
  }

  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns the prodAdminCnpjNbr.
   */
  public String getProdAdminCnpjNbr()
  {
    return m_prodAdminCnpjNbr;
  }

  /**
   * @param prodAdminCnpjNbr_ The prodAdminCnpjNbr to set.
   */
  public void setProdAdminCnpjNbr( String prodAdminCnpjNbr_ )
  {
    m_prodAdminCnpjNbr = prodAdminCnpjNbr_;
  }

  /**
   * @return Returns the prodAnbidCode.
   */
  public String getProdAnbidCode()
  {
    return m_prodAnbidCode;
  }

  /**
   * @param prodAnbidCode_ The prodAnbidCode to set.
   */
  public void setProdAnbidCode( String prodAnbidCode_ )
  {
    m_prodAnbidCode = prodAnbidCode_;
  }

  /**
   * @return Returns the prodApprvDate.
   */
  public Date getProdApprvDate()
  {
    return m_prodApprvDate;
  }

  /**
   * @param prodApprvDate_ The prodApprvDate to set.
   */
  public void setProdApprvDate( Date prodApprvDate_ )
  {
    m_prodApprvDate = prodApprvDate_;
  }

  /**
   * @return Returns the prodAuditCnpjNbr.
   */
  public String getProdAuditCnpjNbr()
  {
    return m_prodAuditCnpjNbr;
  }

  /**
   * @param prodAuditCnpjNbr_ The prodAuditCnpjNbr to set.
   */
  public void setProdAuditCnpjNbr( String prodAuditCnpjNbr_ )
  {
    m_prodAuditCnpjNbr = prodAuditCnpjNbr_;
  }

  /**
   * @return Returns the prodBmfCode.
   */
  public String getProdBmfCode()
  {
    return m_prodBmfCode;
  }

  /**
   * @param prodBmfCode_ The prodBmfCode to set.
   */
  public void setProdBmfCode( String prodBmfCode_ )
  {
    m_prodBmfCode = prodBmfCode_;
  }

  /**
   * @return Returns the prodBovespaCode.
   */
  public String getProdBovespaCode()
  {
    return m_prodBovespaCode;
  }

  /**
   * @param prodBovespaCode_ The prodBovespaCode to set.
   */
  public void setProdBovespaCode( String prodBovespaCode_ )
  {
    m_prodBovespaCode = prodBovespaCode_;
  }

  /**
   * @return Returns the prodCcyCode.
   */
  public String getProdCcyCode()
  {
    return m_prodCcyCode;
  }

  /**
   * @param prodCcyCode_ The prodCcyCode to set.
   */
  public void setProdCcyCode( String prodCcyCode_ )
  {
    m_prodCcyCode = prodCcyCode_;
  }

  /**
   * @return Returns the prodCetipCode.
   */
  public String getProdCetipCode()
  {
    return m_prodCetipCode;
  }

  /**
   * @param prodCetipCode_ The prodCetipCode to set.
   */
  public void setProdCetipCode( String prodCetipCode_ )
  {
    m_prodCetipCode = prodCetipCode_;
  }

  /**
   * @return Returns the prodCode.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * @param prodCode_ The prodCode to set.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }

  /**
   * @return Returns the prodCreateDate.
   */
  public Date getProdCreateDate()
  {
    return m_prodCreateDate;
  }

  /**
   * @param prodCreateDate_ The prodCreateDate to set.
   */
  public void setProdCreateDate( Date prodCreateDate_ )
  {
    m_prodCreateDate = prodCreateDate_;
  }

  /**
   * @return Returns the prodCrTypeClassCode.
   */
  public String getProdCrTypeClassCode()
  {
    return m_prodCrTypeClassCode;
  }

  /**
   * @param prodCrTypeClassCode_ The prodCrTypeClassCode to set.
   */
  public void setProdCrTypeClassCode( String prodCrTypeClassCode_ )
  {
    m_prodCrTypeClassCode = prodCrTypeClassCode_;
  }

  /**
   * @return Returns the prodCstdyCnpjNbr.
   */
  public String getProdCstdyCnpjNbr()
  {
    return m_prodCstdyCnpjNbr;
  }

  /**
   * @param prodCstdyCnpjNbr_ The prodCstdyCnpjNbr to set.
   */
  public void setProdCstdyCnpjNbr( String prodCstdyCnpjNbr_ )
  {
    m_prodCstdyCnpjNbr = prodCstdyCnpjNbr_;
  }

  /**
   * @return Returns the prodCtlCnpjNbr.
   */
  public String getProdCtlCnpjNbr()
  {
    return m_prodCtlCnpjNbr;
  }

  /**
   * @param prodCtlCnpjNbr_ The prodCtlCnpjNbr to set.
   */
  public void setProdCtlCnpjNbr( String prodCtlCnpjNbr_ )
  {
    m_prodCtlCnpjNbr = prodCtlCnpjNbr_;
  }

  /**
   * @return Returns the prodFamlCode.
   */
  public String getProdFamlCode()
  {
    return m_prodFamlCode;
  }

  /**
   * @param prodFamlCode_ The prodFamlCode to set.
   */
  public void setProdFamlCode( String prodFamlCode_ )
  {
    m_prodFamlCode = prodFamlCode_;
  }

  /**
   * @return Returns the prodIsoCode.
   */
  public String getProdIsoCode()
  {
    return m_prodIsoCode;
  }

  /**
   * @param prodIsoCode_ The prodIsoCode to set.
   */
  public void setProdIsoCode( String prodIsoCode_ )
  {
    m_prodIsoCode = prodIsoCode_;
  }

  /**
   * @return Returns the prodMgmtCnpjNbr.
   */
  public String getProdMgmtCnpjNbr()
  {
    return m_prodMgmtCnpjNbr;
  }

  /**
   * @param prodMgmtCnpjNbr_ The prodMgmtCnpjNbr to set.
   */
  public void setProdMgmtCnpjNbr( String prodMgmtCnpjNbr_ )
  {
    m_prodMgmtCnpjNbr = prodMgmtCnpjNbr_;
  }

  /**
   * @return Returns the prodName.
   */
  public String getProdName()
  {
    return m_prodName;
  }

  /**
   * @param prodName_ The prodName to set.
   */
  public void setProdName( String prodName_ )
  {
    m_prodName = prodName_;
  }

  /**
   * @return Returns the prodOpernStaDate.
   */
  public Date getProdOpernStaDate()
  {
    return m_prodOpernStaDate;
  }

  /**
   * @param prodOpernStaDate_ The prodOpernStaDate to set.
   */
  public void setProdOpernStaDate( Date prodOpernStaDate_ )
  {
    m_prodOpernStaDate = prodOpernStaDate_;
  }

  /**
   * @return Returns the prodProcSysCode.
   */
  public String getProdProcSysCode()
  {
    return m_prodProcSysCode;
  }

  /**
   * @param prodProcSysCode_ The prodProcSysCode to set.
   */
  public void setProdProcSysCode( String prodProcSysCode_ )
  {
    m_prodProcSysCode = prodProcSysCode_;
  }

  /**
   * @return Returns the prodProcSysSegCode.
   */
  public BigInteger getProdProcSysSegCode()
  {
    return m_prodProcSysSegCode;
  }

  /**
   * @param prodProcSysSegCode_ The prodProcSysSegCode to set.
   */
  public void setProdProcSysSegCode( BigInteger prodProcSysSegCode_ )
  {
    m_prodProcSysSegCode = prodProcSysSegCode_;
  }

  /**
   * @return Returns the prodQlfyCode.
   */
  public BigInteger getProdQlfyCode()
  {
    return m_prodQlfyCode;
  }

  /**
   * @param prodQlfyCode_ The prodQlfyCode to set.
   */
  public void setProdQlfyCode( BigInteger prodQlfyCode_ )
  {
    m_prodQlfyCode = prodQlfyCode_;
  }

  /**
   * @return Returns the prodRiskCatCode.
   */
  public BigInteger getProdRiskCatCode()
  {
    return m_prodRiskCatCode;
  }

  /**
   * @param prodRiskCatCode_ The prodRiskCatCode to set.
   */
  public void setProdRiskCatCode( BigInteger prodRiskCatCode_ )
  {
    m_prodRiskCatCode = prodRiskCatCode_;
  }

  /**
   * @return Returns the prodSelicCode.
   */
  public String getProdSelicCode()
  {
    return m_prodSelicCode;
  }

  /**
   * @param prodSelicCode_ The prodSelicCode to set.
   */
  public void setProdSelicCode( String prodSelicCode_ )
  {
    m_prodSelicCode = prodSelicCode_;
  }

  /**
   * @return Returns the prodStatCode.
   */
  public String getProdStatCode()
  {
    return m_prodStatCode;
  }

  /**
   * @param prodStatCode_ The prodStatCode to set.
   */
  public void setProdStatCode( String prodStatCode_ )
  {
    m_prodStatCode = prodStatCode_;
  }

  /**
   * @return Returns the prodSubFamlCode.
   */
  public BigInteger getProdSubFamlCode()
  {
    return m_prodSubFamlCode;
  }

  /**
   * @param prodSubFamlCode_ The prodSubFamlCode to set.
   */
  public void setProdSubFamlCode( BigInteger prodSubFamlCode_ )
  {
    m_prodSubFamlCode = prodSubFamlCode_;
  }

  /**
   * @return Returns the prodText.
   */
  public String getProdText()
  {
    return m_prodText;
  }

  /**
   * @param prodText_ The prodText to set.
   */
  public void setProdText( String prodText_ )
  {
    m_prodText = prodText_;
  }

  /**
   * @return Returns the prvtProdAggrCode.
   */
  public String getPrvtProdAggrCode()
  {
    return m_prvtProdAggrCode;
  }

  /**
   * @param prvtProdAggrCode_ The prvtProdAggrCode to set.
   */
  public void setPrvtProdAggrCode( String prvtProdAggrCode_ )
  {
    m_prvtProdAggrCode = prvtProdAggrCode_;
  }

  /**
   * @return Returns the sysCode.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * @param sysCode_ The sysCode to set.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * @return Returns the sysSegCode.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * @param sysSegCode_ The sysSegCode to set.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
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


  /**
   * @return
   */
  public BigInteger getAssetTypeCode()
  {
	return m_assetTypeCode;
  }
  

  /**
   * @param integer
   */
  public void setAssetTypeCode(BigInteger assetTypeCode) {
	  m_assetTypeCode = assetTypeCode;
  }

	public String getAssocClassProdCode() {
		return assocClassProdCode;
	}
	
	public void setAssocClassProdCode(String assocClassProdCode) {
		this.assocClassProdCode = assocClassProdCode;
		
        String codeAssocClass = null;
        String codeType = null;
        String codeSubType = null;		
		
	    //Se existir valor deste combo retirar cada codigo (<CLASS>_<tipo>_<subTipo>) exemplo 1_3_4
	    //Classificação Interface Global
	    if(assocClassProdCode != null && !assocClassProdCode.trim().equals("")){
	        String[] codes = null;
	        codes = assocClassProdCode.split( "_" );
	        
	        codeAssocClass = codes[0];
	        codeSubType = codes[1];
	        codeType = codes[2];
	        
	    }

	    this.prclasProdAssetClassCode = codeAssocClass;
        this.prclasProdStypCode = codeSubType;
        this.prclasProdTypeCode = codeType;	    
	}
	
	public String getProdSentIaInd() {
		return prodSentIaInd;
	}
	
	public void setProdSentIaInd(String prodSentIaInd) {
		this.prodSentIaInd = prodSentIaInd;
	}

	public String getPrclasProdAssetClassCode() {
		return prclasProdAssetClassCode;
	}

	public void setPrclasProdAssetClassCode(String prclasProdAssetClassCode) {
		this.prclasProdAssetClassCode = prclasProdAssetClassCode;
	}

	public String getPrclasProdStypCode() {
		return prclasProdStypCode;
	}

	public void setPrclasProdStypCode(String prclasProdStypCode) {
		this.prclasProdStypCode = prclasProdStypCode;
	}

	public String getPrclasProdTypeCode() {
		return prclasProdTypeCode;
	}

	public void setPrclasProdTypeCode(String prclasProdTypeCode) {
		this.prclasProdTypeCode = prclasProdTypeCode;
	}

	public String getProdIsinCode() {
		return prodIsinCode;
	}

	public void setProdIsinCode(String prodIsinCode) {
		this.prodIsinCode = prodIsinCode;
	}

	public String getAssetClassOnesrc() {
		return assetClassOnesrc;
	}

	public void setAssetClassOnesrc(String assetClassOnesrc) {
		this.assetClassOnesrc = assetClassOnesrc;
	}
		
 }