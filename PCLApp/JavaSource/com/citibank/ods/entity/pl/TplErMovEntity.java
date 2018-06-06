/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplErMovEntityVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class TplErMovEntity extends BaseTplErEntity
{
	
	public TplErMovEntity(){
		m_data = new TplErMovEntityVO(); 
	}
	
	public TplErMovEntity( TplErEntity entityCur ,String  opernCode )
	{
	 	m_data = new TplErMovEntityVO();					 

		m_data.setErNbr(entityCur.getData().getErNbr());
		m_data.setErReltnTrfInd(entityCur.getData().getErReltnTrfInd());
		m_data.setReltnEndReasCode(entityCur.getData().getReltnEndReasCode());
		m_data.setReltnEndReasText(entityCur.getData().getReltnEndReasText());
		m_data.setEquityClassCode(entityCur.getData().getEquityClassCode());
		m_data.setLastUpdDate(entityCur.getData().getLastUpdDate());
		m_data.setLastUpdUserId(entityCur.getData().getLastUpdUserId());
		m_data.setRecStatCode( entityCur.getData().getRecStatCode() );
		m_data.setLastAuthDate(entityCur.getData().getLastAuthDate());
		m_data.setLastAuthUserId(entityCur.getData().getLastAuthUserId());
			( ( TplErMovEntityVO ) m_data ).setOpernCode( opernCode );
		
	}
  


}
