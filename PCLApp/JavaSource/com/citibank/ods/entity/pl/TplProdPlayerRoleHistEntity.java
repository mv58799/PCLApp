/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleHistEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @author acacio.domingos
 */

public class TplProdPlayerRoleHistEntity extends BaseTplProdPlayerRoleEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdPlayerRoleHistEntity()
  {
    m_data = new TplProdPlayerRoleHistEntityVO();
  }

  public TplProdPlayerRoleHistEntity(
                                     TplProdPlayerRoleEntity tplProdPlayerRoleEntity_,
                                     Date prodPlyrDateRef_ )
  {
    m_data = new TplProdPlayerRoleHistEntityVO();

    TplProdPlayerRoleHistEntityVO tplProdPlayerRoleHistEntityVO = ( TplProdPlayerRoleHistEntityVO ) m_data;
    TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();

    tplProdPlayerRoleHistEntityVO.setPlyrCnpjNbr( tplProdPlayerRoleEntityVO.getPlyrCnpjNbr() );
    tplProdPlayerRoleHistEntityVO.setprodPlyrRefDate( prodPlyrDateRef_ );
    tplProdPlayerRoleHistEntityVO.setPlyrRoleTypeCode( tplProdPlayerRoleEntityVO.getPlyrRoleTypeCode() );
    tplProdPlayerRoleHistEntityVO.setProdCode( tplProdPlayerRoleEntityVO.getProdCode() );
    tplProdPlayerRoleHistEntityVO.setSysCode( tplProdPlayerRoleEntityVO.getSysCode() );
    tplProdPlayerRoleHistEntityVO.setSysSegCode( tplProdPlayerRoleEntityVO.getSysSegCode() );
    tplProdPlayerRoleHistEntityVO.setLastUpdUserId( tplProdPlayerRoleEntityVO.getLastUpdUserId() );
    tplProdPlayerRoleHistEntityVO.setLastUpdDate( tplProdPlayerRoleEntityVO.getLastUpdDate() );
    tplProdPlayerRoleHistEntityVO.setlastAuthUserId( tplProdPlayerRoleEntityVO.getLastAuthUserId() );
    tplProdPlayerRoleHistEntityVO.setlastAuthDate( tplProdPlayerRoleEntityVO.getLastAuthDate() );
    tplProdPlayerRoleHistEntityVO.setrecStatCode( tplProdPlayerRoleEntityVO.getRecStatCode() );
  }

}