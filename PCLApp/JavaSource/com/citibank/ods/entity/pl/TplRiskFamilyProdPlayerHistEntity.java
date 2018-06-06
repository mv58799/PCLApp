package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerHistEntityVO;

public class TplRiskFamilyProdPlayerHistEntity extends BaseTplRiskFamilyProdPlayerEntity{
	
	public TplRiskFamilyProdPlayerHistEntity(){
		data = new TplRiskFamilyProdPlayerHistEntityVO();
	}
	
	public TplRiskFamilyProdPlayerHistEntity( TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity, Date prodRefDate_ ){
		
		data = new TplRiskFamilyProdPlayerHistEntityVO();
		
		TplRiskFamilyProdPlayerHistEntityVO tplRiskFamilyProdPlayerHistEntityVO = ( TplRiskFamilyProdPlayerHistEntityVO ) data;
		TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = ( TplRiskFamilyProdPlayerEntityVO ) tplRiskFamilyProdPlayerEntity.getData();
		
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
		tplRiskFamilyProdPlayerHistEntityVO.setLastAuthDate(tplRiskFamilyProdPlayerEntityVO.getLastAuthDate());
		tplRiskFamilyProdPlayerHistEntityVO.setRecStatCode(tplRiskFamilyProdPlayerEntityVO.getRecStatCode());	
		
	}	
	
}
