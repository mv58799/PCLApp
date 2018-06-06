/*
 * Created on Mar 1, 2007
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtMovEntityVO;

/**
 * @author fernando.salgado
 * 
 */
public class TplProdRiskCatPrvtMovEntity extends BaseTplProdRiskCatPrvtEntity
{
  
  /**
   * Constante do tamanho do nome do último usuário a utilizar
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;
  
  /*
   * Construtor sem parametros
   */
  public TplProdRiskCatPrvtMovEntity()
  {
    m_data = new TplProdRiskCatPrvtMovEntityVO();
  }

  /*
   * Construtor parametro current
   */
  public TplProdRiskCatPrvtMovEntity( TplProdRiskCatPrvtEntity prvtEntity_,
                                     String opernCode_ )
  {
    m_data = new TplProdRiskCatPrvtMovEntityVO();

    m_data.setProdRiskCatCode( prvtEntity_.getData().getProdRiskCatCode() );
    m_data.setProdRiskCatText( prvtEntity_.getData().getProdRiskCatText() );
    m_data.setLastUpdUserID( prvtEntity_.getData().getLastUpdUserID() );
    m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
    ( ( TplProdRiskCatPrvtMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }
}