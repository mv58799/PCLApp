package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpMovEntityVO;


public class TplProductCorpMovEntity  extends BaseTplProductCorpEntity{
	  /**
	   * Construtor padrão - sem argumentos
	   */
	  public TplProductCorpMovEntity()
	  {
	    m_data = new TplProductCorpMovEntityVO();
	  }

	  /**
	   * Construtor - Carrega os atributos de movimento com os atributos da current
	   */
	  public TplProductCorpMovEntity( TplProductCorpEntity tplProductCorpEntity_, String opernCode_ ){
		  
	    m_data = new TplProductCorpMovEntityVO();
	    TplProductCorpEntityVO tplProductCorpEntityVO = ( TplProductCorpEntityVO ) tplProductCorpEntity_.getData();
	    
	    //
	    
	    m_data.setProdCode(tplProductCorpEntityVO.getProdCode());
	    m_data.setSysCode(tplProductCorpEntityVO.getSysCode());
	    m_data.setSysSegCode(tplProductCorpEntityVO.getSysSegCode());
	    
	    m_data.setProdEvnContrbDate(tplProductCorpEntityVO.getProdEvnContrbDate());
	    m_data.setProdFundPrflTyp(tplProductCorpEntityVO.getProdFundPrflTyp());
	    m_data.setProdCnpjNbr(tplProductCorpEntityVO.getProdCnpjNbr());
	    m_data.setProdCloseTypCode(tplProductCorpEntityVO.getProdCloseTypCode());
	    m_data.setProdOpenTypCode(tplProductCorpEntityVO.getProdOpenTypCode());
	    m_data.setProdOrigName(tplProductCorpEntityVO.getProdOrigName());
	    m_data.setProdOpenEvnDate(tplProductCorpEntityVO.getProdOpenEvnDate());
	    m_data.setProdAdminRate(tplProductCorpEntityVO.getProdAdminRate());
	    m_data.setProdPerfmRate(tplProductCorpEntityVO.getProdPerfmRate());
	    m_data.setProdPrtfinvApplRate(tplProductCorpEntityVO.getProdPrtfinvApplRate());
	    m_data.setProdExitRate(tplProductCorpEntityVO.getProdExitRate());
	    m_data.setProdQuotTypeCode(tplProductCorpEntityVO.getProdQuotTypeCode());
	    m_data.setProdCloseTime(tplProductCorpEntityVO.getProdCloseTime());
	    m_data.setProdDepQuotDateType(tplProductCorpEntityVO.getProdDepQuotDateType());
	    m_data.setProdWthdrCrDateType(tplProductCorpEntityVO.getProdWthdrCrDateType());
	    m_data.setProdMinStaApplAmt(tplProductCorpEntityVO.getProdMinStaApplAmt());
	    m_data.setProdMovMinAmt(tplProductCorpEntityVO.getProdMovMinAmt());
	    m_data.setProdMinWthdrAmt(tplProductCorpEntityVO.getProdMinWthdrAmt());
	    m_data.setProdHoldMinAmt(tplProductCorpEntityVO.getProdHoldMinAmt());
	    m_data.setProdGraceInd(tplProductCorpEntityVO.getProdGraceInd());
	    m_data.setProdBalCloseDate(tplProductCorpEntityVO.getProdBalCloseDate());
	    m_data.setProdCvmDistCode(tplProductCorpEntityVO.getProdCvmDistCode());
	    m_data.setProdQuotCndmCode(tplProductCorpEntityVO.getProdQuotCndmCode());
	    m_data.setProdRstrnCode(tplProductCorpEntityVO.getProdRstrnCode());
	    m_data.setProdGazetaDclrFormCode(tplProductCorpEntityVO.getProdGazetaDclrFormCode());
	    m_data.setAnbidFundClassCode(tplProductCorpEntityVO.getAnbidFundClassCode());
	    m_data.setProdAnbidDclrCode(tplProductCorpEntityVO.getProdAnbidDclrCode());
	    m_data.setProdCvmTaxCode(tplProductCorpEntityVO.getProdCvmTaxCode());
	    m_data.setProdTermLongInd(tplProductCorpEntityVO.getProdTermLongInd());
	    m_data.setProdJrnlDclrInd(tplProductCorpEntityVO.getProdJrnlDclrInd());
	    m_data.setProdTaxType(tplProductCorpEntityVO.getProdTaxType());
	    m_data.setProdIrfExmpInd(tplProductCorpEntityVO.getProdIrfExmpInd());
	    m_data.setProdIofExmpInd(tplProductCorpEntityVO.getProdIofExmpInd());
	    m_data.setProdGraceTerm(tplProductCorpEntityVO.getProdGraceTerm());
	    m_data.setProdPerfmPayDate(tplProductCorpEntityVO.getProdPerfmPayDate());
	    m_data.setProdBnchIxText(tplProductCorpEntityVO.getProdBnchIxText());
	    m_data.setProdQuotInitAmt(tplProductCorpEntityVO.getProdQuotInitAmt());
	    m_data.setProdMarkTypeCode(tplProductCorpEntityVO.getProdMarkTypeCode());
		m_data.setProdApplLiqDateType(tplProductCorpEntityVO.getProdApplLiqDateType());
		m_data.setProdWthdrLiqDateType(tplProductCorpEntityVO.getProdWthdrLiqDateType());

	    m_data.setLastUpdDate(tplProductCorpEntityVO.getLastUpdDate());
	    m_data.setLastUpdUserId(tplProductCorpEntityVO.getLastUpdUserId());
	    
	    
	    

	    m_data.setFundDistFormTypeCode(tplProductCorpEntityVO.getFundDistFormTypeCode());
	    m_data.setTermText(tplProductCorpEntityVO.getTermText());
	    m_data.setStrategyStartDate(tplProductCorpEntityVO.getStrategyStartDate());
	    m_data.setStrategyCloseDate(tplProductCorpEntityVO.getStrategyCloseDate());
	    m_data.setApplicationStatCode(tplProductCorpEntityVO.getApplicationStatCode());
	    m_data.setWthdrStatCode(tplProductCorpEntityVO.getWthdrStatCode());
	    m_data.setPerfmRateText(tplProductCorpEntityVO.getPerfmRateText());
	    m_data.setCloseDate(tplProductCorpEntityVO.getCloseDate());
		
	    
	    ( ( TplProductCorpMovEntityVO ) m_data ).setOpernCode( opernCode_ );
	    
	  }
	  
}
