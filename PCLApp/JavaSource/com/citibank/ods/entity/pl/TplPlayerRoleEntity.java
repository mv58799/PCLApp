/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleMovEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class TplPlayerRoleEntity extends BaseTplPlayerRoleEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplPlayerRoleEntity()
  {
    m_data = new TplPlayerRoleEntityVO();
  }
  
  /**
   * Construtor - cria uma entidade current com os dados de movimento
   * @param tplPlayerRoleMovEntity_: entidade movimento
   * @param lastAuthDate_: data/hora de aprovação
   * @param lastAuthUserId_: usuário de aprovação
   * @param recStatCode_: status do registro (A = Ativo, I = Inativo, P = Pendente)
   */
  public TplPlayerRoleEntity( TplPlayerRoleMovEntity tplPlayerRoleMovEntity_,
                             Date lastAuthDate_, String lastAuthUserId_,
                             String recStatCode_ )
  {
    m_data = new TplPlayerRoleEntityVO();

    TplPlayerRoleEntityVO tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) m_data;
    TplPlayerRoleMovEntityVO tplPlayerRoleMovEntityVO = ( TplPlayerRoleMovEntityVO ) tplPlayerRoleMovEntity_.getData();
    
    tplPlayerRoleEntityVO.setPlyrCnpjNbr( tplPlayerRoleMovEntityVO.getPlyrCnpjNbr() );
    tplPlayerRoleEntityVO.setPlyrRoleTypeCode( tplPlayerRoleMovEntityVO.getPlyrRoleTypeCode() );
    tplPlayerRoleEntityVO.setLastUpdUserId( tplPlayerRoleMovEntityVO.getLastUpdUserId() );
    tplPlayerRoleEntityVO.setLastUpdDate( tplPlayerRoleMovEntityVO.getLastUpdDate() );
    tplPlayerRoleEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplPlayerRoleEntityVO.setLastAuthDate( lastAuthDate_ );
    tplPlayerRoleEntityVO.setRecStatCode( recStatCode_ );
  }

}