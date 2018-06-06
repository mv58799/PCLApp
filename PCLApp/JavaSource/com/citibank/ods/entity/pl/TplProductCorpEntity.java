package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpMovEntityVO;


public class TplProductCorpEntity  extends BaseTplProductCorpEntity{
	  /**
	   * Construtor padrão - sem argumentos
	   */
	  public TplProductCorpEntity()
	  {
	    m_data = new TplProductCorpEntityVO();
	  }
	  
	  /**
	   * Construtor - Carrega os atributos da current com os atributos do movimento
	   */
	  public TplProductCorpEntity( TplProductCorpMovEntity tplProductCorpMovEntity_,
	                          Date lastAuthDate_, String lastAuthUserId_,
	                          String recStatCode_ )
	  {
	    m_data = new TplProductCorpEntityVO();
	    TplProductCorpMovEntityVO tplProductCorpMovEntityVO = ( TplProductCorpMovEntityVO ) tplProductCorpMovEntity_.getData();
	    
	    
	    m_data.setProdCode(tplProductCorpMovEntityVO.getProdCode());
	    m_data.setSysCode(tplProductCorpMovEntityVO.getSysCode());
	    m_data.setSysSegCode(tplProductCorpMovEntityVO.getSysSegCode());
	    
	    m_data.setProdEvnContrbDate(tplProductCorpMovEntityVO.getProdEvnContrbDate());
	    m_data.setProdFundPrflTyp(tplProductCorpMovEntityVO.getProdFundPrflTyp());
	    m_data.setProdCnpjNbr(tplProductCorpMovEntityVO.getProdCnpjNbr());
	    m_data.setProdCloseTypCode(tplProductCorpMovEntityVO.getProdCloseTypCode());
	    m_data.setProdOpenTypCode(tplProductCorpMovEntityVO.getProdOpenTypCode());
	    m_data.setProdOrigName(tplProductCorpMovEntityVO.getProdOrigName());
	    m_data.setProdOpenEvnDate(tplProductCorpMovEntityVO.getProdOpenEvnDate());
	    m_data.setProdAdminRate(tplProductCorpMovEntityVO.getProdAdminRate());
	    m_data.setProdPerfmRate(tplProductCorpMovEntityVO.getProdPerfmRate());
	    m_data.setProdPrtfinvApplRate(tplProductCorpMovEntityVO.getProdPrtfinvApplRate());
	    m_data.setProdExitRate(tplProductCorpMovEntityVO.getProdExitRate());
	    m_data.setProdQuotTypeCode(tplProductCorpMovEntityVO.getProdQuotTypeCode());
	    m_data.setProdCloseTime(tplProductCorpMovEntityVO.getProdCloseTime());
	    m_data.setProdDepQuotDateType(tplProductCorpMovEntityVO.getProdDepQuotDateType());
	    m_data.setProdWthdrCrDateType(tplProductCorpMovEntityVO.getProdWthdrCrDateType());
	    m_data.setProdMinStaApplAmt(tplProductCorpMovEntityVO.getProdMinStaApplAmt());
	    m_data.setProdMovMinAmt(tplProductCorpMovEntityVO.getProdMovMinAmt());
	    m_data.setProdMinWthdrAmt(tplProductCorpMovEntityVO.getProdMinWthdrAmt());
	    m_data.setProdHoldMinAmt(tplProductCorpMovEntityVO.getProdHoldMinAmt());
	    m_data.setProdGraceInd(tplProductCorpMovEntityVO.getProdGraceInd());
	    m_data.setProdBalCloseDate(tplProductCorpMovEntityVO.getProdBalCloseDate());
	    m_data.setProdCvmDistCode(tplProductCorpMovEntityVO.getProdCvmDistCode());
	    m_data.setProdQuotCndmCode(tplProductCorpMovEntityVO.getProdQuotCndmCode());
	    m_data.setProdRstrnCode(tplProductCorpMovEntityVO.getProdRstrnCode());
	    m_data.setProdGazetaDclrFormCode(tplProductCorpMovEntityVO.getProdGazetaDclrFormCode());
	    m_data.setAnbidFundClassCode(tplProductCorpMovEntityVO.getAnbidFundClassCode());
	    m_data.setProdAnbidDclrCode(tplProductCorpMovEntityVO.getProdAnbidDclrCode());
	    m_data.setProdCvmTaxCode(tplProductCorpMovEntityVO.getProdCvmTaxCode());
	    m_data.setProdTermLongInd(tplProductCorpMovEntityVO.getProdTermLongInd());
	    m_data.setProdJrnlDclrInd(tplProductCorpMovEntityVO.getProdJrnlDclrInd());
	    m_data.setProdTaxType(tplProductCorpMovEntityVO.getProdTaxType());
	    m_data.setProdIrfExmpInd(tplProductCorpMovEntityVO.getProdIrfExmpInd());
	    m_data.setProdIofExmpInd(tplProductCorpMovEntityVO.getProdIofExmpInd());
	    m_data.setProdGraceTerm(tplProductCorpMovEntityVO.getProdGraceTerm());
	    m_data.setProdPerfmPayDate(tplProductCorpMovEntityVO.getProdPerfmPayDate());
	    m_data.setProdBnchIxText(tplProductCorpMovEntityVO.getProdBnchIxText());
	    m_data.setProdQuotInitAmt(tplProductCorpMovEntityVO.getProdQuotInitAmt());
	    m_data.setProdMarkTypeCode(tplProductCorpMovEntityVO.getProdMarkTypeCode());
	    
	    m_data.setProdApplLiqDateType(tplProductCorpMovEntityVO.getProdApplLiqDateType());
	    m_data.setProdWthdrLiqDateType(tplProductCorpMovEntityVO.getProdWthdrLiqDateType());

	    m_data.setLastUpdDate(tplProductCorpMovEntityVO.getLastUpdDate());
	    m_data.setLastUpdUserId(tplProductCorpMovEntityVO.getLastUpdUserId());



	    m_data.setFundDistFormTypeCode(tplProductCorpMovEntityVO.getFundDistFormTypeCode());
	    m_data.setTermText(tplProductCorpMovEntityVO.getTermText());
	    m_data.setStrategyStartDate(tplProductCorpMovEntityVO.getStrategyStartDate());
	    m_data.setStrategyCloseDate(tplProductCorpMovEntityVO.getStrategyCloseDate());
	    m_data.setApplicationStatCode(tplProductCorpMovEntityVO.getApplicationStatCode());
	    m_data.setWthdrStatCode(tplProductCorpMovEntityVO.getWthdrStatCode());
	    m_data.setPerfmRateText(tplProductCorpMovEntityVO.getPerfmRateText());
	    m_data.setCloseDate(tplProductCorpMovEntityVO.getCloseDate());
		
	    
	    ( ( TplProductCorpEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
	    ( ( TplProductCorpEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
	    ( ( TplProductCorpEntityVO ) m_data ).setRecStatCode( recStatCode_ );
	    
	  }
}
