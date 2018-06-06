package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplProductCorpEntityVO extends BaseEntityVO{
	
	private String prodCode;
	private String sysCode;
	private BigInteger sysSegCode;
	
	private Date prodEvnContrbDate;
	private BigInteger prodFundPrflTyp;
	private String prodCnpjNbr;
	private String prodCloseTypCode;
	private String prodOpenTypCode;
	private String prodOrigName;
	private Date prodOpenEvnDate;
	private BigDecimal prodAdminRate;
	private BigDecimal prodPerfmRate;
	private BigDecimal prodPrtfinvApplRate;
	private BigDecimal prodExitRate;
	private String prodQuotTypeCode;
	private Date prodCloseTime;
	private String prodDepQuotDateType;
	private String prodWthdrCrDateType;
	private BigDecimal prodMinStaApplAmt;
	private BigDecimal prodMovMinAmt;
	private BigDecimal prodMinWthdrAmt;
	private BigDecimal prodHoldMinAmt;
	private String prodGraceInd;
	private Date prodBalCloseDate;
	private BigInteger prodCvmDistCode;
	private String prodQuotCndmCode;
	private String prodRstrnCode;
	private String prodGazetaDclrFormCode;
	private BigInteger anbidFundClassCode;
	private DataSet anbidFundClassCodeDomain;
	private String prodAnbidDclrCode;
	private String prodCvmTaxCode;
	private String prodTermLongInd;
	private String prodJrnlDclrInd;
	private String prodTaxType;
	private String prodIrfExmpInd;
	private String prodIofExmpInd;
	private BigInteger prodGraceTerm;
	private Date prodPerfmPayDate;
	private String prodBnchIxText;
	private BigDecimal prodQuotInitAmt;
	private String prodMarkTypeCode;
	private String prodApplLiqDateType;
	private String prodWthdrLiqDateType;	
		
	private String recStatCode;
	private Date lastAuthDate;
	private String lastAuthUserId;
	private Date lastUpdDate;
	private String lastUpdUserId;
	
	private String fundDistFormTypeCode;
	private String termText;
	private Date strategyStartDate;
	private Date strategyCloseDate;
	private String applicationStatCode;
	private String wthdrStatCode;
	private String perfmRateText;
	private Date closeDate;
	
	public BigInteger getAnbidFundClassCode() {
		return anbidFundClassCode;
	}
	public void setAnbidFundClassCode(BigInteger anbidFundClassCode) {
		this.anbidFundClassCode = anbidFundClassCode;
	}
	public Date getLastAuthDate() {
		return lastAuthDate;
	}
	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	public String getLastAuthUserId() {
		return lastAuthUserId;
	}
	public void setLastAuthUserId(String lastAuthUserId) {
		this.lastAuthUserId = lastAuthUserId;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdUserId() {
		return lastUpdUserId;
	}
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}
	public BigDecimal getProdAdminRate() {
		return prodAdminRate;
	}
	public void setProdAdminRate(BigDecimal prodAdminRate) {
		this.prodAdminRate = prodAdminRate;
	}
	public String getProdAnbidDclrCode() {
		return prodAnbidDclrCode;
	}
	public void setProdAnbidDclrCode(String prodAnbidDclrCode) {
		this.prodAnbidDclrCode = prodAnbidDclrCode;
	}
	public Date getProdBalCloseDate() {
		return prodBalCloseDate;
	}
	public void setProdBalCloseDate(Date prodBalCloseDate) {
		this.prodBalCloseDate = prodBalCloseDate;
	}
	public String getProdBnchIxText() {
		return prodBnchIxText;
	}
	public void setProdBnchIxText(String prodBnchIxText) {
		this.prodBnchIxText = prodBnchIxText;
	}
	public Date getProdCloseTime() {
		return prodCloseTime;
	}
	public void setProdCloseTime(Date prodCloseTime) {
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
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public BigInteger getProdCvmDistCode() {
		return prodCvmDistCode;
	}
	public void setProdCvmDistCode(BigInteger prodCvmDistCode) {
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
	public Date getProdEvnContrbDate() {
		return prodEvnContrbDate;
	}
	public void setProdEvnContrbDate(Date prodEvnContrbDate) {
		this.prodEvnContrbDate = prodEvnContrbDate;
	}
	public BigDecimal getProdExitRate() {
		return prodExitRate;
	}
	public void setProdExitRate(BigDecimal prodExitRate) {
		this.prodExitRate = prodExitRate;
	}
	public BigInteger getProdFundPrflTyp() {
		return prodFundPrflTyp;
	}
	public void setProdFundPrflTyp(BigInteger prodFundPrflTyp) {
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
	public BigInteger getProdGraceTerm() {
		return prodGraceTerm;
	}
	public void setProdGraceTerm(BigInteger prodGraceTerm) {
		this.prodGraceTerm = prodGraceTerm;
	}
	public BigDecimal getProdHoldMinAmt() {
		return prodHoldMinAmt;
	}
	public void setProdHoldMinAmt(BigDecimal prodHoldMinAmt) {
		this.prodHoldMinAmt = prodHoldMinAmt;
	}
	public String getProdIofExmpInd() {
		return prodIofExmpInd;
	}
	public void setProdIofExmpInd(String prodIofExmpInd) {
		this.prodIofExmpInd = prodIofExmpInd;
	}
	public String getProdIrfExmpInd() {
		return prodIrfExmpInd;
	}
	public void setProdIrfExmpInd(String prodIrfExmpInd) {
		this.prodIrfExmpInd = prodIrfExmpInd;
	}
	public String getProdJrnlDclrInd() {
		return prodJrnlDclrInd;
	}
	public void setProdJrnlDclrInd(String prodJrnlDclrInd) {
		this.prodJrnlDclrInd = prodJrnlDclrInd;
	}
	public String getProdMarkTypeCode() {
		return prodMarkTypeCode;
	}
	public void setProdMarkTypeCode(String prodMarkTypeCode) {
		this.prodMarkTypeCode = prodMarkTypeCode;
	}
	public BigDecimal getProdMinStaApplAmt() {
		return prodMinStaApplAmt;
	}
	public void setProdMinStaApplAmt(BigDecimal prodMinStaApplAmt) {
		this.prodMinStaApplAmt = prodMinStaApplAmt;
	}
	public BigDecimal getProdMinWthdrAmt() {
		return prodMinWthdrAmt;
	}
	public void setProdMinWthdrAmt(BigDecimal prodMinWthdrAmt) {
		this.prodMinWthdrAmt = prodMinWthdrAmt;
	}
	public BigDecimal getProdMovMinAmt() {
		return prodMovMinAmt;
	}
	public void setProdMovMinAmt(BigDecimal prodMovMinAmt) {
		this.prodMovMinAmt = prodMovMinAmt;
	}
	public Date getProdOpenEvnDate() {
		return prodOpenEvnDate;
	}
	public void setProdOpenEvnDate(Date prodOpenEvnDate) {
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
	public Date getProdPerfmPayDate() {
		return prodPerfmPayDate;
	}
	public void setProdPerfmPayDate(Date prodPerfmPayDate) {
		this.prodPerfmPayDate = prodPerfmPayDate;
	}
	public BigDecimal getProdPerfmRate() {
		return prodPerfmRate;
	}
	public void setProdPerfmRate(BigDecimal prodPerfmRate) {
		this.prodPerfmRate = prodPerfmRate;
	}
	public BigDecimal getProdPrtfinvApplRate() {
		return prodPrtfinvApplRate;
	}
	public void setProdPrtfinvApplRate(BigDecimal prodPrtfinvApplRate) {
		this.prodPrtfinvApplRate = prodPrtfinvApplRate;
	}
	public String getProdQuotCndmCode() {
		return prodQuotCndmCode;
	}
	public void setProdQuotCndmCode(String prodQuotCndmCode) {
		this.prodQuotCndmCode = prodQuotCndmCode;
	}
	public BigDecimal getProdQuotInitAmt() {
		return prodQuotInitAmt;
	}
	public void setProdQuotInitAmt(BigDecimal prodQuotInitAmt) {
		this.prodQuotInitAmt = prodQuotInitAmt;
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
	public String getProdTaxType() {
		return prodTaxType;
	}
	public void setProdTaxType(String prodTaxType) {
		this.prodTaxType = prodTaxType;
	}
	public String getProdTermLongInd() {
		return prodTermLongInd;
	}
	public void setProdTermLongInd(String prodTermLongInd) {
		this.prodTermLongInd = prodTermLongInd;
	}
	public String getProdWthdrCrDateType() {
		return prodWthdrCrDateType;
	}
	public void setProdWthdrCrDateType(String prodWthdrCrDateType) {
		this.prodWthdrCrDateType = prodWthdrCrDateType;
	}
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public BigInteger getSysSegCode() {
		return sysSegCode;
	}
	public void setSysSegCode(BigInteger sysSegCode) {
		this.sysSegCode = sysSegCode;
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

	
}
