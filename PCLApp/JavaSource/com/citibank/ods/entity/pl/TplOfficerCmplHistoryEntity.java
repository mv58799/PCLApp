/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplHistoryEntityVO;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TplOfficerCmplHistoryEntity extends BaseTplOfficerCmplEntity
{
  public TplOfficerCmplHistoryEntity()
  {
    m_data = new TplOfficerCmplHistoryEntityVO();
  }

  public TplOfficerCmplHistoryEntity(
                                     TplOfficerCmplEntity tplOfficerCmplEntity,
                                     Date refDate )
  {
    m_data = new TplOfficerCmplHistoryEntityVO();

    TplOfficerCmplEntityVO tplOfficerCmplEntityVO = ( TplOfficerCmplEntityVO ) tplOfficerCmplEntity.getData();
    TplOfficerCmplHistoryEntityVO cmplHistoryEntityVO = ( TplOfficerCmplHistoryEntityVO ) m_data;

    cmplHistoryEntityVO.setLastAuthDate( tplOfficerCmplEntityVO.getLastAuthDate() );
    cmplHistoryEntityVO.setLastAuthUserId( tplOfficerCmplEntityVO.getLastAuthUserId() );
    cmplHistoryEntityVO.setLastUpdDate( tplOfficerCmplEntityVO.getLastUpdDate() );
    cmplHistoryEntityVO.setLastUpdUserId( tplOfficerCmplEntityVO.getLastUpdUserId() );
    cmplHistoryEntityVO.setOffcrIntlNbr( tplOfficerCmplEntityVO.getOffcrIntlNbr() );
    cmplHistoryEntityVO.setOffcrNbr( tplOfficerCmplEntityVO.getOffcrNbr() );
    cmplHistoryEntityVO.setOffcrRefDate( refDate );
    cmplHistoryEntityVO.setOffcrTypeCode( tplOfficerCmplEntityVO.getOffcrTypeCode() );
    cmplHistoryEntityVO.setRecStatCode( tplOfficerCmplEntityVO.getRecStatCode() );
  }
}