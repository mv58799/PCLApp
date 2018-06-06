package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;

public class TplRiskFamilyProdPlayerEntity extends BaseTplRiskFamilyProdPlayerEntity{

	public TplRiskFamilyProdPlayerEntity(){
		data = new TplRiskFamilyProdPlayerEntityVO();
	}
	
	  /**
	   * Construtor - Carrega os atributos da current com os atributos do movimento
	   */
	  public TplRiskFamilyProdPlayerEntity( TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity,
	                          Date lastAuthDate_, String lastAuthUserId_,
	                          String recStatCode_ )
	  {
	    data = new TplRiskFamilyProdPlayerEntityVO();
	    
	    TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = ( TplRiskFamilyProdPlayerMovEntityVO ) tplRiskFamilyProdPlayerMovEntity.getData();
		
	    data.setPlyrCnpjNbr(tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr());		
	    data.setProdFamlCode(tplRiskFamilyProdPlayerMovEntityVO.getProdFamlCode());		
	    data.setProdInvstRiskCode(tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode());		
	    data.setLastUpdUserId(tplRiskFamilyProdPlayerMovEntityVO.getLastUpdUserId());		
	    data.setLastUpdDate(tplRiskFamilyProdPlayerMovEntityVO.getLastUpdDate());		
	    data.setProdCode(tplRiskFamilyProdPlayerMovEntityVO.getProdCode());		
	    data.setSysCode(tplRiskFamilyProdPlayerMovEntityVO.getSysCode());		
	    data.setSysSegCode(tplRiskFamilyProdPlayerMovEntityVO.getSysSegCode());
	    
	    data.setPlayerEmissorText(tplRiskFamilyProdPlayerMovEntityVO.getPlayerEmissorText());
	    data.setCatRiskText(tplRiskFamilyProdPlayerMovEntityVO.getCatRiskText());
  
	    ( ( TplRiskFamilyProdPlayerEntityVO ) data ).setLastAuthUserId( lastAuthUserId_ );
	    ( ( TplRiskFamilyProdPlayerEntityVO ) data ).setLastAuthDate( lastAuthDate_ );
	    ( ( TplRiskFamilyProdPlayerEntityVO ) data ).setRecStatCode( recStatCode_ );
	    
	  }	
}
