package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;

public class TplRiskFamilyProdPlayerMovEntity extends BaseTplRiskFamilyProdPlayerEntity{
	
	public TplRiskFamilyProdPlayerMovEntity(){
		data = new TplRiskFamilyProdPlayerMovEntityVO();
	}
	
  /**
	 * Construtor - Carrega os atributos de movimento com os atributos da current
	 */
	public TplRiskFamilyProdPlayerMovEntity(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity, String opernCode_) {
		
		data = new TplRiskFamilyProdPlayerMovEntityVO();
		TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();
		
	    data.setPlyrCnpjNbr(tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr());		
	    data.setProdFamlCode(tplRiskFamilyProdPlayerEntityVO.getProdFamlCode());		
	    data.setProdInvstRiskCode(tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode());		
	    data.setLastUpdUserId(tplRiskFamilyProdPlayerEntityVO.getLastUpdUserId());		
	    data.setLastUpdDate(tplRiskFamilyProdPlayerEntityVO.getLastUpdDate());		
	    data.setProdCode(tplRiskFamilyProdPlayerEntityVO.getProdCode());		
	    data.setSysCode(tplRiskFamilyProdPlayerEntityVO.getSysCode());		
	    data.setSysSegCode(tplRiskFamilyProdPlayerEntityVO.getSysSegCode());
	    
	    data.setPlayerEmissorText(tplRiskFamilyProdPlayerEntityVO.getPlayerEmissorText());
	    data.setCatRiskText(tplRiskFamilyProdPlayerEntityVO.getCatRiskText());	    

	    ((TplRiskFamilyProdPlayerMovEntityVO) data).setOpernCode(opernCode_);

	}	
}
