/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerHistEntityVO;

/**
 * @author acacio.domingos
 * 
 *            
 */
public class TplProductHistEntity extends BaseTplProductEntity
{
  public TplProductHistEntity()
  {
    m_data = new TplProductHistEntityVO();
  }

  public TplProductHistEntity( TplProductEntity tplProductEntity_,
                              Date prodRefDate_ )
  {
    m_data = new TplProductHistEntityVO();

    TplProductHistEntityVO tplProductHistEntityVO = ( TplProductHistEntityVO ) m_data;
    TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity_.getData();

    tplProductHistEntityVO.setProdCode( tplProductEntityVO.getProdCode() );
    tplProductHistEntityVO.setSysCode( tplProductEntityVO.getSysCode() );
    tplProductHistEntityVO.setSysSegCode( tplProductEntityVO.getSysSegCode() );
    tplProductHistEntityVO.setProdRefDate( prodRefDate_ );
    tplProductHistEntityVO.setProdName( tplProductEntityVO.getProdName() );
    tplProductHistEntityVO.setProdText( tplProductEntityVO.getProdText() );
    tplProductHistEntityVO.setProdFamlCode( tplProductEntityVO.getProdFamlCode() );
    tplProductHistEntityVO.setProdCcyCode( tplProductEntityVO.getProdCcyCode() );
    tplProductHistEntityVO.setProdIsoCode( tplProductEntityVO.getProdIsoCode() );
    tplProductHistEntityVO.setProdAnbidCode( tplProductEntityVO.getProdAnbidCode() );
    tplProductHistEntityVO.setProdCetipCode( tplProductEntityVO.getProdCetipCode() );
    tplProductHistEntityVO.setProdSelicCode( tplProductEntityVO.getProdSelicCode() );
    tplProductHistEntityVO.setProdBovespaCode( tplProductEntityVO.getProdBovespaCode() );
    tplProductHistEntityVO.setProdBmfCode( tplProductEntityVO.getProdBmfCode() );
    tplProductHistEntityVO.setProdCreateDate( tplProductEntityVO.getProdCreateDate() );
    tplProductHistEntityVO.setProdStatCode( tplProductEntityVO.getProdStatCode() );
    tplProductHistEntityVO.setProdCrTypeClassCode( tplProductEntityVO.getProdCrTypeClassCode() );
    tplProductHistEntityVO.setPrvtProdAggrCode( tplProductEntityVO.getPrvtProdAggrCode() );
    tplProductHistEntityVO.setProdProcSysCode( tplProductEntityVO.getProdProcSysCode() );
    tplProductHistEntityVO.setProdProcSysSegCode( tplProductEntityVO.getProdProcSysSegCode() );
    tplProductHistEntityVO.setProdApprvDate( tplProductEntityVO.getProdApprvDate() );
    tplProductHistEntityVO.setProdOpernStaDate( tplProductEntityVO.getProdOpernStaDate() );
    tplProductHistEntityVO.setProdCstdyCnpjNbr( tplProductEntityVO.getProdCstdyCnpjNbr() );
    tplProductHistEntityVO.setProdAuditCnpjNbr( tplProductEntityVO.getProdAuditCnpjNbr() );
    tplProductHistEntityVO.setProdMgmtCnpjNbr( tplProductEntityVO.getProdMgmtCnpjNbr() );
    tplProductHistEntityVO.setProdCtlCnpjNbr( tplProductEntityVO.getProdCtlCnpjNbr() );
    tplProductHistEntityVO.setProdAdminCnpjNbr( tplProductEntityVO.getProdAdminCnpjNbr() );
    tplProductHistEntityVO.setCitiGrpTieReltnPlcyInd( tplProductEntityVO.getCitiGrpTieReltnPlcyInd() );
    tplProductHistEntityVO.setCitiGrpTieRstrnPlcyInd( tplProductEntityVO.getCitiGrpTieRstrnPlcyInd() );
    tplProductHistEntityVO.setProdRiskCatCode( tplProductEntityVO.getProdRiskCatCode() );
    tplProductHistEntityVO.setProdQlfyCode( tplProductEntityVO.getProdQlfyCode() );
    tplProductHistEntityVO.setProdSubFamlCode( tplProductEntityVO.getProdSubFamlCode() );
    tplProductHistEntityVO.setProdLegalClassCode( tplProductEntityVO.getProdLegalClassCode() );
    tplProductHistEntityVO.setLastUpdUserId( tplProductEntityVO.getLastUpdUserId() );
    tplProductHistEntityVO.setLastUpdDate( tplProductEntityVO.getLastUpdDate() );
    tplProductHistEntityVO.setLastAuthUserId( tplProductEntityVO.getLastAuthUserId() );
    tplProductHistEntityVO.setLastAuthDate( tplProductEntityVO.getLastAuthDate() );
    tplProductHistEntityVO.setRecStatCode( tplProductEntityVO.getRecStatCode() );
	tplProductHistEntityVO.setAssetTypeCode(tplProductEntityVO.getAssetTypeCode());
	
    //Emissor - Lista
    List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
    List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity = new ArrayList<TplRiskFamilyProdPlayerHistEntity>();
    
    
    for(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity){
    	
    	TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO)tplRiskFamilyProdPlayerEntity.getData();
    	
    	TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity = new TplRiskFamilyProdPlayerHistEntity();
    	TplRiskFamilyProdPlayerHistEntityVO tplRiskFamilyProdPlayerHistEntityVO = (TplRiskFamilyProdPlayerHistEntityVO) tplRiskFamilyProdPlayerHistEntity.getData();
    	
    	tplRiskFamilyProdPlayerHistEntityVO.setPlyrCnpjNbr(tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());
    	tplRiskFamilyProdPlayerHistEntityVO.setProdFamlCode(tplRiskFamilyProdPlayerEntityVO.getProdFamlCode());
    	tplRiskFamilyProdPlayerHistEntityVO.setProdInvstRiskCode(tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode());
    	tplRiskFamilyProdPlayerHistEntityVO.setLastUpdUserId(tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId());
    	tplRiskFamilyProdPlayerHistEntityVO.setLastUpdDate(tplRiskFamilyProdPlayerEntityVO.getLastUpdDate());
    	tplRiskFamilyProdPlayerHistEntityVO.setProdCode(tplRiskFamilyProdPlayerEntityVO.getProdCode());
    	tplRiskFamilyProdPlayerHistEntityVO.setSysCode(tplRiskFamilyProdPlayerEntityVO.getSysCode());
    	tplRiskFamilyProdPlayerHistEntityVO.setSysSegCode(tplRiskFamilyProdPlayerEntityVO.getSysSegCode());
    	tplRiskFamilyProdPlayerHistEntityVO.setPlayerEmissorText(tplRiskFamilyProdPlayerEntityVO.getPlayerEmissorText());
    	tplRiskFamilyProdPlayerHistEntityVO.setCatRiskText(tplRiskFamilyProdPlayerEntityVO.getCatRiskText());
    	
    	tplRiskFamilyProdPlayerHistEntityVO.setLastAuthUserId(tplRiskFamilyProdPlayerEntityVO.getLastAuthUserId() );
    	tplRiskFamilyProdPlayerHistEntityVO.setLastAuthDate(tplRiskFamilyProdPlayerEntityVO.getLastAuthDate() );
    	tplRiskFamilyProdPlayerHistEntityVO.setRecStatCode(tplRiskFamilyProdPlayerEntityVO.getRecStatCode() );
    	
    	tplRiskFamilyProdPlayerHistEntityVO.setRiskFamlProdPlyrRefDate(prodRefDate_);
    	
    	listTplRiskFamilyProdPlayerHistEntity.add(tplRiskFamilyProdPlayerHistEntity);
    }
    
    tplProductHistEntityVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerHistEntity);	
    
    //Enviar Produto para o IA    
    tplProductHistEntityVO.setProdSentIaInd(tplProductEntityVO.getProdSentIaInd());
    
    //Classificação Interface Global
    tplProductHistEntityVO.setAssocClassProdCode(tplProductEntityVO.getAssocClassProdCode());
    tplProductHistEntityVO.setPrclasProdAssetClassCode(tplProductEntityVO.getPrclasProdAssetClassCode());
    tplProductHistEntityVO.setPrclasProdStypCode(tplProductEntityVO.getPrclasProdStypCode());
    tplProductHistEntityVO.setPrclasProdTypeCode(tplProductEntityVO.getPrclasProdTypeCode());
    
    //ISIN - Fase 3    
    tplProductHistEntityVO.setProdIsinCode(tplProductEntityVO.getProdIsinCode());

  }

}