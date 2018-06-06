package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerMovEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * 
 * @author aribas
 *
 */
public class TplShortNamePlayerMovEntity {
	
	protected TplShortNamePlayerMovEntityVO mData;
	
	public TplShortNamePlayerMovEntity(){
		mData = new TplShortNamePlayerMovEntityVO();
	}
	
	public TplShortNamePlayerMovEntity(ShortNameVO shortNameVO){
		TplShortNamePlayerMovEntityVO tplShortNamePlayerMovEntityVO = new TplShortNamePlayerMovEntityVO();
		tplShortNamePlayerMovEntityVO.setPlyrCnpjNbr(shortNameVO.getPlyrCnpjNbr());
		tplShortNamePlayerMovEntityVO.setLastUpdUserId(shortNameVO.getLastUpdUserId());
		tplShortNamePlayerMovEntityVO.setIssueShortName(shortNameVO.getIssueShortName());
		tplShortNamePlayerMovEntityVO.setLastUpdDate(shortNameVO.getLastUpdDate());
		tplShortNamePlayerMovEntityVO.setOpernCode(shortNameVO.getOpernCode());
		mData = tplShortNamePlayerMovEntityVO;		
	}
	
	public TplShortNamePlayerMovEntityVO getData(){
		return mData;
	}
	
	

}
