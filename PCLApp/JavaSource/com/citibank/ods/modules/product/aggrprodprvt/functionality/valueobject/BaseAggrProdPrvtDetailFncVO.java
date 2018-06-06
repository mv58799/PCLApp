/*
 * Created on Mar 12, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity;

/**
 * @author leonardo.nakada
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseAggrProdPrvtDetailFncVO extends BaseODSFncVO
{
  protected BaseTplAggrProdPrvtEntity m_aggrProdPrvtEntity;
  
  /**
   * Constante do nome do elemento C�digo
   */
  public static final String C_PRVT_PROD_AGGR_CODE_DESCRIPTION = "C�digo do Agregador";
  
  /**
   * Constante da descricao da categoria de risco
   */
  public static final String C_PRVT_PROD_AGGR_TEXT_DESCRIPTION = "Descri��o do Agregador";
  
  /**
   * @return Returns the aggrProdPrvtEntity.
   */
  public BaseTplAggrProdPrvtEntity getAggrProdPrvtEntity()
  {
    return m_aggrProdPrvtEntity;
  }
  
  /**
   * @param aggrProdPrvtEntity_ The aggrProdPrvtEntity to set.
   */
  public void setAggrProdPrvtEntity(
                                    BaseTplAggrProdPrvtEntity aggrProdPrvtEntity_ )
  {
    m_aggrProdPrvtEntity = aggrProdPrvtEntity_;
  }
}
