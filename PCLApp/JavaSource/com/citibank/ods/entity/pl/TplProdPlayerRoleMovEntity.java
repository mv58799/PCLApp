/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;



import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;

/**
 * @author acacio.domingos
 *  
 */

public class TplProdPlayerRoleMovEntity extends BaseTplProdPlayerRoleEntity
/**
 * Construtor padrão - sem argumentos
 */
{
  public TplProdPlayerRoleMovEntity()
  {
    m_data = new TplProdPlayerRoleMovEntityVO();
    m_dataProduct = new TplProductEntityVO();
  }
  
  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  
  public TplProdPlayerRoleMovEntity( TplProdPlayerRoleEntity tplProdPlayerRoleEntity_, String opernCode_)
  {
    m_data = new TplProdPlayerRoleMovEntityVO();
    TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();
    
    m_data.setProdCode( tplProdPlayerRoleEntityVO.getProdCode() );
    m_data.setSysCode( tplProdPlayerRoleEntityVO.getSysCode() );
    m_data.setSysSegCode( tplProdPlayerRoleEntityVO.getSysSegCode() );
    m_data.setPlyrCnpjNbr( tplProdPlayerRoleEntityVO.getPlyrCnpjNbr() );
    m_data.setPlyrRoleTypeCode( tplProdPlayerRoleEntityVO.getPlyrRoleTypeCode() );
    m_data.setLastUpdUserId( tplProdPlayerRoleEntityVO.getLastUpdUserId() );
    m_data.setLastUpdDate( tplProdPlayerRoleEntityVO.getLastUpdDate() );
    (( TplProdPlayerRoleMovEntityVO )m_data).setOpernCode( opernCode_ );
  }

}