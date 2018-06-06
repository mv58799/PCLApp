package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerHistEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * 
 * @author aribas
 *
 */
public class TplShortNamePlayerEntity {
	protected TplShortNamePlayerEntityVO mData;
	
	public TplShortNamePlayerEntity(){
		mData = new TplShortNamePlayerEntityVO();
	}
	
	public TplShortNamePlayerEntity(ShortNameVO shortNameVO){
		TplShortNamePlayerEntityVO tplShortNamePlayerEntityVO = new TplShortNamePlayerEntityVO();
		tplShortNamePlayerEntityVO.setPlyrCnpjNbr(shortNameVO.getPlyrCnpjNbr());
		tplShortNamePlayerEntityVO.setLastUpdUserId(shortNameVO.getLastUpdUserId());
		tplShortNamePlayerEntityVO.setIssueShortName(shortNameVO.getIssueShortName());
		tplShortNamePlayerEntityVO.setLastUpdDate(shortNameVO.getLastUpdDate());
		tplShortNamePlayerEntityVO.setRecStatCode(shortNameVO.getRecStatCode());
		tplShortNamePlayerEntityVO.setLastAuthDate(shortNameVO.getLastAuthDate());
		tplShortNamePlayerEntityVO.setLastAuthUserId(shortNameVO.getLastAuthUserId());
		mData = tplShortNamePlayerEntityVO;	
	}
	
	public TplShortNamePlayerEntityVO getData(){
		return mData;
	}
}
