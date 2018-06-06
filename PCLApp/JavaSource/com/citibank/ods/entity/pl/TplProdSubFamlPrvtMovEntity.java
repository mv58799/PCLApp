/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtMovEntityVO;

/**
 * @author fernando.salgado
 * 
 */
public class TplProdSubFamlPrvtMovEntity extends BaseTplProdSubFamlPrvtEntity
{

  /**
   * Constante do tamanho do nome do último usuário a utilizar
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdSubFamlPrvtMovEntity()
  {
    m_data = new TplProdSubFamlPrvtMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de instancia com os atributos de Current
   */
  public TplProdSubFamlPrvtMovEntity(
                                     TplProdSubFamlPrvtEntity tplProductFamilyPrvtEntity_,
                                     String opernCode_ )
  {
    m_data = new TplProdSubFamlPrvtMovEntityVO();

    m_data.setProdSubFamlCode( tplProductFamilyPrvtEntity_.getData().getProdSubFamlCode() );
    m_data.setProdSubFamlName( tplProductFamilyPrvtEntity_.getData().getProdSubFamlName() );
    m_data.setProdSubFamlText( tplProductFamilyPrvtEntity_.getData().getProdSubFamlText() );
    m_data.setProdFamlCode( tplProductFamilyPrvtEntity_.getData().getProdFamlCode() );
    m_data.setLastUpdUserId( tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductFamilyPrvtEntity_.getData().getLastUpdDate() );
    ( ( TplProdSubFamlPrvtMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }
}