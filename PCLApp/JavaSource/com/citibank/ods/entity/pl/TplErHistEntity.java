/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplErEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErHistEntityVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class TplErHistEntity extends BaseTplErEntity
{
  /**
   * Construtor
   */
  public TplErHistEntity()
  {
	m_data = new TplErHistEntityVO();
  }

  /**
   * Construtor - Criando um objeto a partir de outro já populado
   */
  public TplErHistEntity( TplErEntity tplErEntity_, Date erRefDate_ )
  {
	m_data = new TplErHistEntityVO();
	TplErHistEntityVO erHistEntityVO = ( TplErHistEntityVO ) m_data;
	TplErEntityVO tplErEntityVO = ( TplErEntityVO ) tplErEntity_.getData();

	erHistEntityVO.setErNbr( tplErEntityVO.getErNbr() );
	erHistEntityVO.setErRefDate( erRefDate_ );
	erHistEntityVO.setErReltnTrfInd( tplErEntityVO.getErReltnTrfInd() );
	erHistEntityVO.setReltnEndReasCode( tplErEntityVO.getReltnEndReasCode() );
	erHistEntityVO.setReltnEndReasText( tplErEntityVO.getReltnEndReasText() );
	erHistEntityVO.setEquityClassCode( tplErEntityVO.getEquityClassCode() );
	erHistEntityVO.setLastUpdDate( tplErEntityVO.getLastUpdDate() );
	erHistEntityVO.setLastUpdUserId( tplErEntityVO.getLastUpdUserId() );
	erHistEntityVO.setLastAuthDate( tplErEntityVO.getLastAuthDate() );
	erHistEntityVO.setLastAuthUserId( tplErEntityVO.getLastUpdUserId() );
	erHistEntityVO.setRecStatCode( tplErEntityVO.getRecStatCode() );

  }
}