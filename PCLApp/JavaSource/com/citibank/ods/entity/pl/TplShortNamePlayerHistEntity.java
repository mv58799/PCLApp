package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerHistEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

public class TplShortNamePlayerHistEntity {
	
	protected TplShortNamePlayerHistEntityVO mData;
	
	public TplShortNamePlayerHistEntity(){
		mData = new TplShortNamePlayerHistEntityVO();
	}
	
	public TplShortNamePlayerHistEntity(ShortNameVO shortNameVO, Date issueShortRefDate){
		TplShortNamePlayerHistEntityVO tplShortNamePlayerHistEntityVO = new TplShortNamePlayerHistEntityVO();
		tplShortNamePlayerHistEntityVO.setPlyrCnpjNbr(shortNameVO.getPlyrCnpjNbr());
		tplShortNamePlayerHistEntityVO.setIssueShortRefDate(issueShortRefDate);		
		tplShortNamePlayerHistEntityVO.setLastUpdUserId(shortNameVO.getLastUpdUserId());
		tplShortNamePlayerHistEntityVO.setIssueShortName(shortNameVO.getIssueShortName());
		tplShortNamePlayerHistEntityVO.setLastUpdDate(shortNameVO.getLastUpdDate());
		tplShortNamePlayerHistEntityVO.setRecStatCode(shortNameVO.getRecStatCode());
		tplShortNamePlayerHistEntityVO.setLastAuthDate(shortNameVO.getLastAuthDate());
		tplShortNamePlayerHistEntityVO.setLastAuthUserId(shortNameVO.getLastAuthUserId());
		mData = tplShortNamePlayerHistEntityVO;		
	}
	
	public TplShortNamePlayerHistEntityVO getData(){
		return mData;
	}

}
