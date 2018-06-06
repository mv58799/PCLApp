/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerHistEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerHistEntity extends BaseTplPlayerEntity
{
  public TplPlayerHistEntity()
  {
    m_data = new TplPlayerHistEntityVO();
  }

  public TplPlayerHistEntity( TplPlayerEntity tplPlayerEntity_,
                             Date plyrDateRef_ )
  {
    m_data = new TplPlayerHistEntityVO();

    TplPlayerHistEntityVO tplPlayerHistEntityVO = ( TplPlayerHistEntityVO ) m_data;
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity_.getData();

    tplPlayerHistEntityVO.setPlyrCnpjNbr( tplPlayerEntityVO.getPlyrCnpjNbr() );
    tplPlayerHistEntityVO.setPlyrRefDate(plyrDateRef_);
    tplPlayerHistEntityVO.setPlyrName( tplPlayerEntityVO.getPlyrName() );
    tplPlayerHistEntityVO.setPlyrAddrText( tplPlayerEntityVO.getPlyrAddrText() );
    tplPlayerHistEntityVO.setPlyrDueDlgDate( tplPlayerEntityVO.getPlyrDueDlgDate() );
    tplPlayerHistEntityVO.setPlyrDueDlgExecInd( tplPlayerEntityVO.getPlyrDueDlgExecInd() );
    tplPlayerHistEntityVO.setPlyrDueDlgEndDate( tplPlayerEntityVO.getPlyrDueDlgEndDate() );
    tplPlayerHistEntityVO.setPlyrDueDlgRnwDate( tplPlayerEntityVO.getPlyrDueDlgRnwDate() );
    tplPlayerHistEntityVO.setPlyrInvstCmtteApprvDate( tplPlayerEntityVO.getPlyrInvstCmtteApprvDate() );
    tplPlayerHistEntityVO.setPlyrApprvRstrnText( tplPlayerEntityVO.getPlyrApprvRstrnText() );
    tplPlayerHistEntityVO.setPlyrSuplServText( tplPlayerEntityVO.getPlyrSuplServText() );
    tplPlayerHistEntityVO.setPlyrCmntText( tplPlayerEntityVO.getPlyrCmntText() );
    tplPlayerHistEntityVO.setLastUpdUserId( tplPlayerEntityVO.getLastUpdUserId() );
    tplPlayerHistEntityVO.setLastUpdDate( tplPlayerEntityVO.getLastUpdDate() );
    tplPlayerHistEntityVO.setLastAuthUserId( tplPlayerEntityVO.getLastAuthUserId() );
    tplPlayerHistEntityVO.setLastAuthDate( tplPlayerEntityVO.getLastAuthDate() );
    tplPlayerHistEntityVO.setRecStatCode( tplPlayerEntityVO.getRecStatCode() );
  }

}