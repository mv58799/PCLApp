/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;

/**
 * @author acacio.domingos
 *  
 */
public class TplProductMovEntity extends BaseTplProductEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProductMovEntity()
  {
    m_data = new TplProductMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  public TplProductMovEntity( TplProductEntity tplProductEntity_,
                             String opernCode_ )
  {
    m_data = new TplProductMovEntityVO();
    TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity_.getData();

    m_data.setProdCode( tplProductEntityVO.getProdCode() );
    m_data.setSysCode( tplProductEntityVO.getSysCode() );
    m_data.setSysSegCode( tplProductEntityVO.getSysSegCode() );
    m_data.setProdName( tplProductEntityVO.getProdName() );
    m_data.setProdText( tplProductEntityVO.getProdText() );
    m_data.setProdFamlCode( tplProductEntityVO.getProdFamlCode() );
    m_data.setProdCcyCode( tplProductEntityVO.getProdCcyCode() );
    m_data.setProdIsoCode( tplProductEntityVO.getProdIsoCode() );
    m_data.setProdAnbidCode( tplProductEntityVO.getProdAnbidCode() );
    m_data.setProdCetipCode( tplProductEntityVO.getProdCetipCode() );
    m_data.setProdSelicCode( tplProductEntityVO.getProdSelicCode() );
    m_data.setProdBovespaCode( tplProductEntityVO.getProdBovespaCode() );
    m_data.setProdBmfCode( tplProductEntityVO.getProdBmfCode() );
    m_data.setProdCreateDate( tplProductEntityVO.getProdCreateDate() );
    m_data.setProdStatCode( tplProductEntityVO.getProdStatCode() );
    m_data.setPrvtProdAggrCode( tplProductEntityVO.getPrvtProdAggrCode() );
    m_data.setProdCrTypeClassCode( tplProductEntityVO.getProdCrTypeClassCode() );
    m_data.setProdProcSysCode( tplProductEntityVO.getProdProcSysCode() );
    m_data.setProdProcSysSegCode( tplProductEntityVO.getProdProcSysSegCode() );
    m_data.setProdApprvDate( tplProductEntityVO.getProdApprvDate() );
    m_data.setProdOpernStaDate( tplProductEntityVO.getProdOpernStaDate() );
    m_data.setProdCstdyCnpjNbr( tplProductEntityVO.getProdCstdyCnpjNbr() );
    m_data.setProdAuditCnpjNbr( tplProductEntityVO.getProdAuditCnpjNbr() );
    m_data.setProdMgmtCnpjNbr( tplProductEntityVO.getProdMgmtCnpjNbr() );
    m_data.setProdCtlCnpjNbr( tplProductEntityVO.getProdCtlCnpjNbr() );
    m_data.setProdAdminCnpjNbr( tplProductEntityVO.getProdAdminCnpjNbr() );
    m_data.setCitiGrpTieReltnPlcyInd( tplProductEntityVO.getCitiGrpTieReltnPlcyInd() );
    m_data.setCitiGrpTieRstrnPlcyInd( tplProductEntityVO.getCitiGrpTieRstrnPlcyInd() );
    m_data.setProdRiskCatCode( tplProductEntityVO.getProdRiskCatCode() );
    m_data.setProdQlfyCode( tplProductEntityVO.getProdQlfyCode() );
    m_data.setProdSubFamlCode( tplProductEntityVO.getProdSubFamlCode() );
    m_data.setProdLegalClassCode( tplProductEntityVO.getProdLegalClassCode() );
    m_data.setLastUpdUserId( tplProductEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductEntityVO.getLastUpdDate() );    
	m_data.setAssetTypeCode( tplProductEntityVO.getAssetTypeCode() );
	m_data.setAssetClassOnesrc(tplProductEntityVO.getAssetClassOnesrc());
	
    ( ( TplProductMovEntityVO ) m_data ).setOpernCode( opernCode_ );
    
    //Emissor - Lista
    List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
    List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = new ArrayList<TplRiskFamilyProdPlayerMovEntity>();
    
    for(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity){
    	
    	TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO)tplRiskFamilyProdPlayerEntity.getData();
    	
    	TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity = new TplRiskFamilyProdPlayerMovEntity();
    	TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();
    	
    	tplRiskFamilyProdPlayerMovEntityVO.setPlyrCnpjNbr(tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
    	tplRiskFamilyProdPlayerMovEntityVO.setProdFamlCode(tplRiskFamilyProdPlayerEntityVO.getProdFamlCode());
    	tplRiskFamilyProdPlayerMovEntityVO.setProdInvstRiskCode(tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode());
    	tplRiskFamilyProdPlayerMovEntityVO.setLastUpdUserId(tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId());
    	tplRiskFamilyProdPlayerMovEntityVO.setLastUpdDate(tplRiskFamilyProdPlayerEntityVO.getLastUpdDate());
    	tplRiskFamilyProdPlayerMovEntityVO.setProdCode(tplRiskFamilyProdPlayerEntityVO.getProdCode());
    	tplRiskFamilyProdPlayerMovEntityVO.setSysCode(tplRiskFamilyProdPlayerEntityVO.getSysCode());
    	tplRiskFamilyProdPlayerMovEntityVO.setSysSegCode(tplRiskFamilyProdPlayerEntityVO.getSysSegCode());
    	tplRiskFamilyProdPlayerMovEntityVO.setPlayerEmissorText(tplRiskFamilyProdPlayerEntityVO.getPlayerEmissorText());
    	tplRiskFamilyProdPlayerMovEntityVO.setCatRiskText(tplRiskFamilyProdPlayerEntityVO.getCatRiskText());
    	
    	tplRiskFamilyProdPlayerMovEntityVO.setOpernCode(opernCode_);
    	
    	listTplRiskFamilyProdPlayerMovEntity.add(tplRiskFamilyProdPlayerMovEntity);
    }
    
    ( ( TplProductMovEntityVO ) m_data ).setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerMovEntity);    
    
    //Enviar Produto para o IA    
    m_data.setProdSentIaInd(tplProductEntityVO.getProdSentIaInd());
    
    //Classificação Interface Global
    m_data.setAssocClassProdCode(tplProductEntityVO.getAssocClassProdCode());
    m_data.setPrclasProdAssetClassCode(tplProductEntityVO.getPrclasProdAssetClassCode());
    m_data.setPrclasProdStypCode(tplProductEntityVO.getPrclasProdStypCode());
    m_data.setPrclasProdTypeCode(tplProductEntityVO.getPrclasProdTypeCode());
    
    //ISIN - fase 3    
    m_data.setProdIsinCode(tplProductEntityVO.getProdIsinCode());
    
  }
}