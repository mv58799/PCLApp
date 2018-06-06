/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleMovEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerRoleMovEntity extends BaseTplPlayerRoleEntity
{
  /**
   * Construtor padrão - sem argumentos
   * @author angelica.almeida
   */
  public TplPlayerRoleMovEntity()
  {
    m_data = new TplPlayerRoleMovEntityVO();
  }
  
  /**
   * Construtor - cria uma entidade com os dados de uma entidade current
   * 
   * @param tplPlayerRoleEntity_: entidade current
   * @param opernCode_: código da operação (I = Inserção, A = Alteração, E = Exclusão)
   */
  public TplPlayerRoleMovEntity( TplPlayerRoleEntity tplPlayerRoleEntity_,
                                String opernCode_ )
  {
    m_data = new TplPlayerRoleMovEntityVO();

    TplPlayerRoleMovEntityVO tplPlayerRoleMovEntityVO = ( TplPlayerRoleMovEntityVO ) m_data;
    TplPlayerRoleEntityVO tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) tplPlayerRoleEntity_.getData();
    
    tplPlayerRoleMovEntityVO.setPlyrCnpjNbr( tplPlayerRoleEntityVO.getPlyrCnpjNbr() );
    tplPlayerRoleMovEntityVO.setPlyrRoleTypeCode( tplPlayerRoleEntityVO.getPlyrRoleTypeCode() );
    tplPlayerRoleMovEntityVO.setLastUpdUserId( tplPlayerRoleEntityVO.getLastUpdUserId() );
    tplPlayerRoleMovEntityVO.setLastUpdDate( tplPlayerRoleEntityVO.getLastUpdDate() );
    tplPlayerRoleMovEntityVO.setOpernCode( opernCode_ );
  }

}