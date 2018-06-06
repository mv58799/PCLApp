/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplEntityVO;

/**
 * @author bruno.zanetti
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TplOfficerCmplEntity extends BaseTplOfficerCmplEntity
{

  public TplOfficerCmplEntity()
  {
    m_data = new TplOfficerCmplEntityVO();
  }

  public TplOfficerCmplEntity(
                              TplOfficerCmplMovementEntity cmplMovementEntity_,
                              Date lastAuthDate_, 
                              String lastAuthUserID_,
                              String recStatCode_ )
  {
    m_data = new TplOfficerCmplEntityVO();

    m_data.setOffcrNbr(cmplMovementEntity_.getData().getOffcrNbr());
    m_data.setOffcrTypeCode( cmplMovementEntity_.getData().getOffcrTypeCode() );
    m_data.setOffcrIntlNbr( cmplMovementEntity_.getData().getOffcrIntlNbr() );
    m_data.setLastUpdUserId( cmplMovementEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( cmplMovementEntity_.getData().getLastUpdDate() );
    ( ( TplOfficerCmplEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplOfficerCmplEntityVO ) m_data ).setLastAuthUserId( lastAuthUserID_ );
    ( ( TplOfficerCmplEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }
}