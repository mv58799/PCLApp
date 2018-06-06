package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpHistEntityVO;

public class TplProductCorpHistEntity extends BaseTplProductCorpEntity {
	/**
	 * Construtor padrão - sem argumentos
	 */
	public TplProductCorpHistEntity() {
		m_data = new TplProductCorpEntityVO();
	}

	/**
	 * Construtor - Carrega os atributos da current com os atributos do movimento
	 */
	public TplProductCorpHistEntity(TplProductCorpEntity tplProductCorpEntity_, Date prodRefDate_) {
		m_data = new TplProductCorpHistEntityVO();

		TplProductCorpHistEntityVO tplProductHistEntityVO = (TplProductCorpHistEntityVO) m_data;
		TplProductCorpEntityVO tplProductCorpEntityVO = (TplProductCorpEntityVO) tplProductCorpEntity_.getData();

		tplProductHistEntityVO.setProdCode(tplProductCorpEntityVO.getProdCode());
		tplProductHistEntityVO.setSysCode(tplProductCorpEntityVO.getSysCode());
		tplProductHistEntityVO.setSysSegCode(tplProductCorpEntityVO.getSysSegCode());
		tplProductHistEntityVO.setProdRefDate(prodRefDate_);

		tplProductHistEntityVO.setProdEvnContrbDate(tplProductCorpEntityVO.getProdEvnContrbDate());
		tplProductHistEntityVO.setProdFundPrflTyp(tplProductCorpEntityVO.getProdFundPrflTyp());
		tplProductHistEntityVO.setProdCnpjNbr(tplProductCorpEntityVO.getProdCnpjNbr());
		tplProductHistEntityVO.setProdCloseTypCode(tplProductCorpEntityVO.getProdCloseTypCode());
		tplProductHistEntityVO.setProdOpenTypCode(tplProductCorpEntityVO.getProdOpenTypCode());
		tplProductHistEntityVO.setProdOrigName(tplProductCorpEntityVO.getProdOrigName());
		tplProductHistEntityVO.setProdOpenEvnDate(tplProductCorpEntityVO.getProdOpenEvnDate());
		tplProductHistEntityVO.setProdAdminRate(tplProductCorpEntityVO.getProdAdminRate());
		tplProductHistEntityVO.setProdPerfmRate(tplProductCorpEntityVO.getProdPerfmRate());
		tplProductHistEntityVO.setProdPrtfinvApplRate(tplProductCorpEntityVO.getProdPrtfinvApplRate());
		tplProductHistEntityVO.setProdExitRate(tplProductCorpEntityVO.getProdExitRate());
		tplProductHistEntityVO.setProdQuotTypeCode(tplProductCorpEntityVO.getProdQuotTypeCode());
		tplProductHistEntityVO.setProdCloseTime(tplProductCorpEntityVO.getProdCloseTime());
		tplProductHistEntityVO.setProdDepQuotDateType(tplProductCorpEntityVO.getProdDepQuotDateType());
		tplProductHistEntityVO.setProdWthdrCrDateType(tplProductCorpEntityVO.getProdWthdrCrDateType());
		tplProductHistEntityVO.setProdMinStaApplAmt(tplProductCorpEntityVO.getProdMinStaApplAmt());
		tplProductHistEntityVO.setProdMovMinAmt(tplProductCorpEntityVO.getProdMovMinAmt());
		tplProductHistEntityVO.setProdMinWthdrAmt(tplProductCorpEntityVO.getProdMinWthdrAmt());
		tplProductHistEntityVO.setProdHoldMinAmt(tplProductCorpEntityVO.getProdHoldMinAmt());
		tplProductHistEntityVO.setProdGraceInd(tplProductCorpEntityVO.getProdGraceInd());
		tplProductHistEntityVO.setProdBalCloseDate(tplProductCorpEntityVO.getProdBalCloseDate());
		tplProductHistEntityVO.setProdCvmDistCode(tplProductCorpEntityVO.getProdCvmDistCode());
		tplProductHistEntityVO.setProdQuotCndmCode(tplProductCorpEntityVO.getProdQuotCndmCode());
		tplProductHistEntityVO.setProdRstrnCode(tplProductCorpEntityVO.getProdRstrnCode());
		tplProductHistEntityVO.setProdGazetaDclrFormCode(tplProductCorpEntityVO.getProdGazetaDclrFormCode());
		tplProductHistEntityVO.setAnbidFundClassCode(tplProductCorpEntityVO.getAnbidFundClassCode());
		tplProductHistEntityVO.setProdAnbidDclrCode(tplProductCorpEntityVO.getProdAnbidDclrCode());
		tplProductHistEntityVO.setProdCvmTaxCode(tplProductCorpEntityVO.getProdCvmTaxCode());
		tplProductHistEntityVO.setProdTermLongInd(tplProductCorpEntityVO.getProdTermLongInd());
		tplProductHistEntityVO.setProdJrnlDclrInd(tplProductCorpEntityVO.getProdJrnlDclrInd());
		tplProductHistEntityVO.setProdTaxType(tplProductCorpEntityVO.getProdTaxType());
		tplProductHistEntityVO.setProdIrfExmpInd(tplProductCorpEntityVO.getProdIrfExmpInd());
		tplProductHistEntityVO.setProdIofExmpInd(tplProductCorpEntityVO.getProdIofExmpInd());
		tplProductHistEntityVO.setProdGraceTerm(tplProductCorpEntityVO.getProdGraceTerm());
		tplProductHistEntityVO.setProdPerfmPayDate(tplProductCorpEntityVO.getProdPerfmPayDate());
		tplProductHistEntityVO.setProdBnchIxText(tplProductCorpEntityVO.getProdBnchIxText());
		tplProductHistEntityVO.setProdQuotInitAmt(tplProductCorpEntityVO.getProdQuotInitAmt());
		tplProductHistEntityVO.setProdMarkTypeCode(tplProductCorpEntityVO.getProdMarkTypeCode());
		
		tplProductHistEntityVO.setProdApplLiqDateType(tplProductCorpEntityVO.getProdApplLiqDateType());
		tplProductHistEntityVO.setProdWthdrLiqDateType(tplProductCorpEntityVO.getProdWthdrLiqDateType());


		tplProductHistEntityVO.setLastUpdDate(tplProductCorpEntityVO.getLastUpdDate());
		tplProductHistEntityVO.setLastUpdUserId(tplProductCorpEntityVO.getLastUpdUserId());

		tplProductHistEntityVO.setLastAuthUserId(tplProductCorpEntityVO.getLastAuthUserId());
		tplProductHistEntityVO.setLastAuthDate(tplProductCorpEntityVO.getLastAuthDate());
		tplProductHistEntityVO.setRecStatCode(tplProductCorpEntityVO.getRecStatCode());


		tplProductHistEntityVO.setFundDistFormTypeCode(tplProductCorpEntityVO.getFundDistFormTypeCode());
		tplProductHistEntityVO.setTermText(tplProductCorpEntityVO.getTermText());
		tplProductHistEntityVO.setStrategyStartDate(tplProductCorpEntityVO.getStrategyStartDate());
		tplProductHistEntityVO.setStrategyCloseDate(tplProductCorpEntityVO.getStrategyCloseDate());
		tplProductHistEntityVO.setApplicationStatCode(tplProductCorpEntityVO.getApplicationStatCode());
		tplProductHistEntityVO.setWthdrStatCode(tplProductCorpEntityVO.getWthdrStatCode());
		tplProductHistEntityVO.setPerfmRateText(tplProductCorpEntityVO.getPerfmRateText());
		tplProductHistEntityVO.setCloseDate(tplProductCorpEntityVO.getCloseDate());
		
	}
}
