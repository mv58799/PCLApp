/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleHistEntityVO;

/**
 * @author angelica.almeida
 */
public class TplPlayerRoleHistEntity extends BaseTplPlayerRoleEntity
{
  /**
   * Construtor padrão - sem argumentos
   * @author angelica.almeida
   */
  public TplPlayerRoleHistEntity()
  {
    m_data = new TplPlayerRoleHistEntityVO();
  }
  
  /**
   * Construtor - cria uma entidade de histórico com os dados da current
   * 
   * @param tplPlayerRoleEntity_: entidade current
   * @param plyrRoleRefDate_: data de referencia( data em que entrou no historico)
   */
  public TplPlayerRoleHistEntity( TplPlayerRoleEntity tplPlayerRoleEntity_,
                                 Date plyrRoleRefDate_ )
  {
    m_data = new TplPlayerRoleHistEntityVO();

    TplPlayerRoleHistEntityVO tplPlayerRoleHistEntityVO = ( TplPlayerRoleHistEntityVO ) m_data;
    TplPlayerRoleEntityVO tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) tplPlayerRoleEntity_.getData();

    tplPlayerRoleHistEntityVO.setPlyrCnpjNbr( tplPlayerRoleEntityVO.getPlyrCnpjNbr() );
    tplPlayerRoleHistEntityVO.setPlyrRoleRefDate( plyrRoleRefDate_ );
    tplPlayerRoleHistEntityVO.setPlyrRoleTypeCode( tplPlayerRoleEntityVO.getPlyrRoleTypeCode() );
    tplPlayerRoleHistEntityVO.setLastUpdUserId( tplPlayerRoleEntityVO.getLastUpdUserId() );
    tplPlayerRoleHistEntityVO.setLastUpdDate( tplPlayerRoleEntityVO.getLastUpdDate() );
    tplPlayerRoleHistEntityVO.setLastAuthUserId( tplPlayerRoleEntityVO.getLastAuthUserId() );
    tplPlayerRoleHistEntityVO.setLastAuthDate( tplPlayerRoleEntityVO.getLastAuthDate() );
    tplPlayerRoleHistEntityVO.setRecStatCode( tplPlayerRoleEntityVO.getRecStatCode() );

  }

}