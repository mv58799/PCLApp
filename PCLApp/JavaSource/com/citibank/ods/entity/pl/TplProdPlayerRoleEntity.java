/*
 * Created on 02/04/2007
 *  
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;

/**
 * @author acacio.domingos
 *  
 */
public class TplProdPlayerRoleEntity extends BaseTplProdPlayerRoleEntity
{

  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdPlayerRoleEntity()
  {
    m_data = new TplProdPlayerRoleEntityVO();
    m_dataProduct = new TplProductEntityVO();
  }

  public TplProdPlayerRoleEntity(
                                 TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity_,
                                 Date lastAuthDate_, String lastAuthUserId_,
                                 String recStatCode_ )
  {
    m_data = new TplProdPlayerRoleEntityVO();
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity_.getData();

    m_data.setPlyrCnpjNbr( tplProdPlayerRoleMovEntityVO.getPlyrCnpjNbr() );
    m_data.setPlyrRoleTypeCode( tplProdPlayerRoleMovEntityVO.getPlyrRoleTypeCode());
    m_data.setProdCode( tplProdPlayerRoleMovEntityVO.getProdCode());
    m_data.setSysCode( tplProdPlayerRoleMovEntityVO.getSysCode());
    m_data.setSysSegCode( tplProdPlayerRoleMovEntityVO.getSysSegCode());
    m_data.setLastUpdUserId( tplProdPlayerRoleMovEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplProdPlayerRoleMovEntityVO.getLastUpdDate() );
    ( ( TplProdPlayerRoleEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplProdPlayerRoleEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplProdPlayerRoleEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }

  public boolean equals( TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ )
  {
    TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();

    if ( m_data.getPlyrCnpjNbr().equals(
                                         tplProdPlayerRoleEntityVO.getPlyrCnpjNbr() )
         && m_data.getPlyrRoleTypeCode().equals(
                                                 tplProdPlayerRoleEntityVO.getPlyrRoleTypeCode() )
         && m_data.getProdCode().equals(
                                         tplProdPlayerRoleEntityVO.getProdCode() )
         && m_data.getSysCode().equals( tplProdPlayerRoleEntityVO.getSysCode() )
         && m_data.getSysSegCode().equals(
                                           tplProdPlayerRoleEntityVO.getSysSegCode() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

}