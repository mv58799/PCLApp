/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplErEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErMovEntityVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class TplErEntity extends BaseTplErEntity
{

  public TplErEntity()
  {
	m_data = new TplErEntityVO();
  }

  public TplErEntity( TplErMovEntity tplErMovEntity,
					   String lastAuthUserId_, Date lastAuthDate_,
					   String recStatCode_ )
  {
	m_data = new TplErEntityVO();
	TplErEntityVO tplErEntityVO = ( TplErEntityVO ) m_data;
	TplErMovEntityVO tplErMovEntityVO = ( TplErMovEntityVO ) tplErMovEntity.getData();

	tplErEntityVO.setErNbr( tplErMovEntityVO.getErNbr() );
	tplErEntityVO.setErReltnTrfInd( tplErMovEntityVO.getErReltnTrfInd() );
	tplErEntityVO.setReltnEndReasCode( tplErMovEntityVO.getReltnEndReasCode() );
	tplErEntityVO.setReltnEndReasText( tplErMovEntityVO.getReltnEndReasText() );
	tplErEntityVO.setEquityClassCode( tplErMovEntityVO.getEquityClassCode() );
	tplErEntityVO.setLastUpdDate( tplErMovEntityVO.getLastUpdDate() );
	tplErEntityVO.setLastUpdUserId( tplErMovEntityVO.getLastUpdUserId() );
	tplErEntityVO.setLastAuthDate( lastAuthDate_ );
	tplErEntityVO.setLastAuthUserId( lastAuthUserId_ );
	tplErEntityVO.setRecStatCode( recStatCode_ );

  }
}