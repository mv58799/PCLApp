/*
 * Created on Mar 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;

/**
 * @author fernando.salgado
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdQlfyPrvtDetailFncVO extends BaseODSFncVO
{
  /**
   * Constante de descri��o do nome do campo da Qualifica��o do Produto
   */
  public static final String C_PROD_QLFY_CODE_DESCRIPTION = "C�digo da Qualifica��o";

  /**
   * Constante de descri��o do nome do campo de Descri��o da Qualifica��o do
   * Produto
   */
  public static final String C_PROD_QLFY_TEXT_DESCRIPTION = "Descri��o da Qualifica��o";

  protected BaseTplProdQlfyPrvtEntity m_baseTplProdQlfyPrvtEntity;

  /**
   * @return Returns the m_baseTplProdQlfyPrvtEntity.
   */
  public BaseTplProdQlfyPrvtEntity getBaseTplProdQlfyPrvtEntity()
  {
    return m_baseTplProdQlfyPrvtEntity;
  }

  /**
   * @param tplProdQlfyPrvtEntity The m_baseTplProdQlfyPrvtEntity to set.
   */
  public void setBaseTplProdQlfyPrvtEntity(
                                           BaseTplProdQlfyPrvtEntity tplProdQlfyPrvtEntity_ )
  {
    m_baseTplProdQlfyPrvtEntity = tplProdQlfyPrvtEntity_;
  }

}