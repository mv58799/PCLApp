/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtMovEntityVO;

/**
 * @author fernando.salgado
 *  
 */
public class TplProductFamilyPrvtMovEntity extends
    BaseTplProductFamilyPrvtEntity
{

  /**
   * Constante do tamanho do nome do último usuário a utilizar
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  /**
   * Construtor padrão - sem argumentos
   */
  public TplProductFamilyPrvtMovEntity()
  {
    m_data = new TplProductFamilyPrvtMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de instancia com os atributos de Current
   */
  public TplProductFamilyPrvtMovEntity(
                                       TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_,
                                       String opernCode_ )
  {
    m_data = new TplProductFamilyPrvtMovEntityVO();

    m_data.setProdFamlCode( tplProductFamilyPrvtEntity_.getData().getProdFamlCode() );
    m_data.setProdFamlName( tplProductFamilyPrvtEntity_.getData().getProdFamlName() );
    m_data.setProdFamlText( tplProductFamilyPrvtEntity_.getData().getProdFamlText() );
    m_data.setLastUpdUserId( tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductFamilyPrvtEntity_.getData().getLastUpdDate() );
    ( ( TplProductFamilyPrvtMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }
}