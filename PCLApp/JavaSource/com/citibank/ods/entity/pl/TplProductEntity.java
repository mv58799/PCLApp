/*
 * Created on Aprr 14, 2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;

/**
 * @author acacio.domingos
 *  
 */
public class TplProductEntity extends BaseTplProductEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProductEntity()
  {
    m_data = new TplProductEntityVO();
  }

  /**
   * Constantes para popular o combo de produto nas telas de contratos.
   */
  //PMA FAKE
  public static final String C_PROD_CODE_PMA_FAKE = "PxA";

  //PMA ADVISOR
  public static final String C_PROD_CODE_PMA = "PMA";

  public static final String C_PROD_CODE_PMA_1 = "PMA1";

  public static final String C_PROD_CODE_PMA_2 = "PMA2";

  public static final String C_PROD_CODE_PMA_3 = "PMA3";

  public static final String C_PROD_CODE_PMA_4 = "PMA4";

  public static final String C_PROD_CODE_PMA_5 = "PMA5";

  //PMA Execution
  public static final String C_PROD_CODE_PEA = "PEA";

  public static final String C_PROD_CODE_PEA_1 = "PEA1";

  public static final String C_PROD_CODE_PEA_2 = "PEA2";

  public static final String C_PROD_CODE_PEA_3 = "PEA3";

  public static final String C_PROD_CODE_PEA_4 = "PEA4";

  public static final String C_PROD_CODE_PEA_5 = "PEA5";

  public static final String C_SYS_CODE = "DV";

  public static final int C_SYS_SEG_CODE_CORPORATE = 2;

  /**
   * Construtor - Carrega os atributos da current com os atributos do movimento
   */
  public TplProductEntity( TplProductMovEntity tplProductMovEntity_,
                          Date lastAuthDate_, String lastAuthUserId_,
                          String recStatCode_ )
  {
    m_data = new TplProductEntityVO();
    TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) tplProductMovEntity_.getData();

    m_data.setProdCode( tplProductMovEntityVO.getProdCode() );
    m_data.setSysCode( tplProductMovEntityVO.getSysCode() );
    m_data.setSysSegCode( tplProductMovEntityVO.getSysSegCode() );
    m_data.setProdName( tplProductMovEntityVO.getProdName() );
    m_data.setProdText( tplProductMovEntityVO.getProdText() );
    m_data.setProdFamlCode( tplProductMovEntityVO.getProdFamlCode() );
    m_data.setProdCcyCode( tplProductMovEntityVO.getProdCcyCode() );
    m_data.setProdIsoCode( tplProductMovEntityVO.getProdIsoCode() );
    m_data.setProdAnbidCode( tplProductMovEntityVO.getProdAnbidCode() );
    m_data.setProdCetipCode( tplProductMovEntityVO.getProdCetipCode() );
    m_data.setProdSelicCode( tplProductMovEntityVO.getProdSelicCode() );
    m_data.setProdBovespaCode( tplProductMovEntityVO.getProdBovespaCode() );
    m_data.setProdBmfCode( tplProductMovEntityVO.getProdBmfCode() );
    m_data.setProdCreateDate( tplProductMovEntityVO.getProdCreateDate() );
    m_data.setProdStatCode( tplProductMovEntityVO.getProdStatCode() );
    m_data.setPrvtProdAggrCode( tplProductMovEntityVO.getPrvtProdAggrCode() );
    m_data.setProdCrTypeClassCode( tplProductMovEntityVO.getProdCrTypeClassCode() );
    m_data.setProdProcSysCode( tplProductMovEntityVO.getProdProcSysCode() );
    m_data.setProdProcSysSegCode( tplProductMovEntityVO.getProdProcSysSegCode() );
    m_data.setProdApprvDate( tplProductMovEntityVO.getProdApprvDate() );
    m_data.setProdOpernStaDate( tplProductMovEntityVO.getProdOpernStaDate() );
    m_data.setProdCstdyCnpjNbr( tplProductMovEntityVO.getProdCstdyCnpjNbr() );
    m_data.setProdAuditCnpjNbr( tplProductMovEntityVO.getProdAuditCnpjNbr() );
    m_data.setProdMgmtCnpjNbr( tplProductMovEntityVO.getProdMgmtCnpjNbr() );
    m_data.setProdCtlCnpjNbr( tplProductMovEntityVO.getProdCtlCnpjNbr() );
    m_data.setProdAdminCnpjNbr( tplProductMovEntityVO.getProdAdminCnpjNbr() );
    m_data.setCitiGrpTieReltnPlcyInd( tplProductMovEntityVO.getCitiGrpTieReltnPlcyInd() );
    m_data.setCitiGrpTieRstrnPlcyInd( tplProductMovEntityVO.getCitiGrpTieRstrnPlcyInd() );
    m_data.setProdRiskCatCode( tplProductMovEntityVO.getProdRiskCatCode() );
    m_data.setProdQlfyCode( tplProductMovEntityVO.getProdQlfyCode() );
    m_data.setProdSubFamlCode( tplProductMovEntityVO.getProdSubFamlCode() );
    m_data.setProdLegalClassCode( tplProductMovEntityVO.getProdLegalClassCode() );
    m_data.setLastUpdUserId( tplProductMovEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductMovEntityVO.getLastUpdDate() );
    m_data.setLastUpdDate( tplProductMovEntityVO.getLastUpdDate() );
	m_data.setAssetTypeCode(tplProductMovEntityVO.getAssetTypeCode());    
	m_data.setAssetClassOnesrc(tplProductMovEntityVO.getAssetClassOnesrc());
	

	m_data.setFundDistFormTypeCode(tplProductMovEntityVO.getFundDistFormTypeCode());
	m_data.setTermText(tplProductMovEntityVO.getTermText());
	m_data.setStrategyStartDate(tplProductMovEntityVO.getStrategyStartDate());
	m_data.setStrategyCloseDate(tplProductMovEntityVO.getStrategyCloseDate());
	m_data.setApplicationStatCode(tplProductMovEntityVO.getApplicationStatCode());
	m_data.setWthdrStatCode(tplProductMovEntityVO.getWthdrStatCode());
	m_data.setPerfmRateText(tplProductMovEntityVO.getPerfmRateText());
	m_data.setCloseDate(tplProductMovEntityVO.getCloseDate());
	
	
    ( ( TplProductEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplProductEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplProductEntityVO ) m_data ).setRecStatCode( recStatCode_ );
    
    //Emissor - Lista
    
    List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
    List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = new ArrayList<TplRiskFamilyProdPlayerEntity>();
    
    
    for(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listTplRiskFamilyProdPlayerMovEntity){
    	
    	TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity = new TplRiskFamilyProdPlayerEntity();
    	TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();
    	
    	
    	tplRiskFamilyProdPlayerEntityVO.setPlyrCnpjNbr(tplRiskFamilyProdPlayerMovEntity.getData().getPlyrCnpjNbr());
    	tplRiskFamilyProdPlayerEntityVO.setProdFamlCode(tplRiskFamilyProdPlayerMovEntity.getData().getProdFamlCode());
    	tplRiskFamilyProdPlayerEntityVO.setProdInvstRiskCode(tplRiskFamilyProdPlayerMovEntity.getData().getProdInvstRiskCode());
    	tplRiskFamilyProdPlayerEntityVO.setLastUpdUserId(tplRiskFamilyProdPlayerMovEntity.getData().getLastUpdUserId());
    	tplRiskFamilyProdPlayerEntityVO.setLastUpdDate(tplRiskFamilyProdPlayerMovEntity.getData().getLastUpdDate());
    	tplRiskFamilyProdPlayerEntityVO.setProdCode(tplRiskFamilyProdPlayerMovEntity.getData().getProdCode());
    	tplRiskFamilyProdPlayerEntityVO.setSysCode(tplRiskFamilyProdPlayerMovEntity.getData().getSysCode());
    	tplRiskFamilyProdPlayerEntityVO.setSysSegCode(tplRiskFamilyProdPlayerMovEntity.getData().getSysSegCode());
    	tplRiskFamilyProdPlayerEntityVO.setPlayerEmissorText(tplRiskFamilyProdPlayerMovEntity.getData().getPlayerEmissorText());
    	tplRiskFamilyProdPlayerEntityVO.setCatRiskText(tplRiskFamilyProdPlayerMovEntity.getData().getCatRiskText());
    	
    	tplRiskFamilyProdPlayerEntityVO.setLastAuthUserId( lastAuthUserId_ );
    	tplRiskFamilyProdPlayerEntityVO.setLastAuthDate( lastAuthDate_ );
    	tplRiskFamilyProdPlayerEntityVO.setRecStatCode( recStatCode_ );
    	
    	listTplRiskFamilyProdPlayerEntity.add(tplRiskFamilyProdPlayerEntity);
    }
    
    ( ( TplProductEntityVO ) m_data ).setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerEntity);
    
    //Enviar Produto para o IA    
    m_data.setProdSentIaInd(tplProductMovEntityVO.getProdSentIaInd());
    
    //Classificação Interface Global
    m_data.setAssocClassProdCode(tplProductMovEntityVO.getAssocClassProdCode());
    m_data.setPrclasProdAssetClassCode(tplProductMovEntityVO.getPrclasProdAssetClassCode());
    m_data.setPrclasProdStypCode(tplProductMovEntityVO.getPrclasProdStypCode());
    m_data.setPrclasProdTypeCode(tplProductMovEntityVO.getPrclasProdTypeCode());   
    
    //Fase 3 - ISIN
    m_data.setProdIsinCode(tplProductMovEntityVO.getProdIsinCode());
    
  }

}