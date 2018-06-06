/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerMovEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerEntity extends BaseTplPlayerEntity
{

  /**
   * Construtor padrão - sem argumentos
   */
  public TplPlayerEntity()
  {
    m_data = new TplPlayerEntityVO();
  }

  /**
   * Construtor - Carrega os atributos da current com os atributos do movimento
   */
  public TplPlayerEntity( TplPlayerMovEntity tplPlayerMovEntity_,
                         Date lastAuthDate_, String lastAuthUserId_,
                         String recStatCode_ )
  {
    m_data = new TplPlayerEntityVO();
    TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) tplPlayerMovEntity_.getData();

    m_data.setPlyrCnpjNbr( tplPlayerMovEntityVO.getPlyrCnpjNbr() );
    m_data.setPlyrName( tplPlayerMovEntityVO.getPlyrName() );
    m_data.setPlyrAddrText( tplPlayerMovEntityVO.getPlyrAddrText() );
    m_data.setPlyrDueDlgDate( tplPlayerMovEntityVO.getPlyrDueDlgDate() );
    m_data.setPlyrDueDlgExecInd( tplPlayerMovEntityVO.getPlyrDueDlgExecInd() );
    m_data.setPlyrDueDlgEndDate( tplPlayerMovEntityVO.getPlyrDueDlgEndDate() );
    m_data.setPlyrDueDlgRnwDate( tplPlayerMovEntityVO.getPlyrDueDlgRnwDate() );
    m_data.setPlyrInvstCmtteApprvDate( tplPlayerMovEntityVO.getPlyrInvstCmtteApprvDate() );
    m_data.setPlyrApprvRstrnText( tplPlayerMovEntityVO.getPlyrApprvRstrnText() );
    m_data.setPlyrSuplServText( tplPlayerMovEntityVO.getPlyrSuplServText() );
    m_data.setPlyrCmntText( tplPlayerMovEntityVO.getPlyrCmntText() );
    m_data.setLastUpdUserId( tplPlayerMovEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplPlayerMovEntityVO.getLastUpdDate() );
    ( ( TplPlayerEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplPlayerEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplPlayerEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }

}