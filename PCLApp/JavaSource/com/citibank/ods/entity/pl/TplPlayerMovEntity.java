/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerMovEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerMovEntity extends BaseTplPlayerEntity
{
  /**
   * Constante do tamanho do nome do último usuário a utilizar
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  /**
   * Construtor padrão - sem argumentos
   */
  public TplPlayerMovEntity()
  {
    m_data = new TplPlayerMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  public TplPlayerMovEntity( TplPlayerEntity tplPlayerEntity_, String opernCode_ )
  {
    m_data = new TplPlayerMovEntityVO();
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) tplPlayerEntity_.getData();

    m_data.setPlyrCnpjNbr( tplPlayerEntityVO.getPlyrCnpjNbr() );
    m_data.setPlyrName( tplPlayerEntityVO.getPlyrName() );
    m_data.setPlyrAddrText( tplPlayerEntityVO.getPlyrAddrText() );
    m_data.setPlyrDueDlgDate( tplPlayerEntityVO.getPlyrDueDlgDate() );
    m_data.setPlyrDueDlgExecInd( tplPlayerEntityVO.getPlyrDueDlgExecInd() );
    m_data.setPlyrDueDlgEndDate( tplPlayerEntityVO.getPlyrDueDlgEndDate() );
    m_data.setPlyrDueDlgRnwDate( tplPlayerEntityVO.getPlyrDueDlgRnwDate() );
    m_data.setPlyrInvstCmtteApprvDate( tplPlayerEntityVO.getPlyrInvstCmtteApprvDate() );
    m_data.setPlyrApprvRstrnText( tplPlayerEntityVO.getPlyrApprvRstrnText() );
    m_data.setPlyrSuplServText( tplPlayerEntityVO.getPlyrSuplServText() );
    m_data.setPlyrCmntText( tplPlayerEntityVO.getPlyrCmntText() );
    m_data.setLastUpdUserId( tplPlayerEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplPlayerEntityVO.getLastUpdDate() );
    ( ( TplPlayerMovEntityVO ) m_data ).setOpernCode( opernCode_ );
    plyrRoleNames = tplPlayerEntity_.getPlyrRoleNames();
  }

}