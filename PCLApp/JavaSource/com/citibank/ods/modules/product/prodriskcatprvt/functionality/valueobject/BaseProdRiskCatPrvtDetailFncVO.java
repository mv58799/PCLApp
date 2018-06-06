package com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdRiskCatPrvtDetailFncVO extends BaseODSFncVO
{
  /**
   * Constante do nome do elemento Código
   */
  public static final String C_PROD_RISK_CAT_CODE_DESCRIPTION = "Código da Categoria de Risco";

  /**
   * Constante da descricao da categoria de risco
   */
  public static final String C_PROD_RISK_CAT_TEXT_DESCRIPTION = "Descrição da Categoria de Risco";

  /**
   * Entity
   */
  protected BaseTplProdRiskCatPrvtEntity m_baseTplProdRiskCatPrvtEntity;

  /**
   * @return Returns the baseTplProdRiskCatPrvtEntity.
   */
  public BaseTplProdRiskCatPrvtEntity getBaseTplProdRiskCatPrvtEntity()
  {
    return m_baseTplProdRiskCatPrvtEntity;
  }

  /**
   * @param baseTplProdRiskCatPrvtEntity_ The baseTplProdRiskCatPrvtEntity to
   *          set.
   */
  public void setBaseTplProdRiskCatPrvtEntity(
                                              BaseTplProdRiskCatPrvtEntity baseTplProdRiskCatPrvtEntity_ )
  {
    m_baseTplProdRiskCatPrvtEntity = baseTplProdRiskCatPrvtEntity_;
  }

}