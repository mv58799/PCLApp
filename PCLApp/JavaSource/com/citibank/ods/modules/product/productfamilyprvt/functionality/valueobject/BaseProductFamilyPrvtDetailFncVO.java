/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;

/**
 * @author leonardo.nakada
 *
 */
public class BaseProductFamilyPrvtDetailFncVO extends BaseODSFncVO
{
	
  /**
   * Constante do nome do código da família
   */
  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Código da Família";
  
  /**
   * Constante do nome da família
   */
  public static final String C_PROD_FAML_NAME_DESCRIPTION = "Nome da Família";
  
  /**
   * Constante da descricao da família
   */
  public static final String C_PROD_FAML_TEXT_DESCRIPTION = "Descrição da Família";
	
  /**
   * Entity
   */
  protected BaseTplProductFamilyPrvtEntity m_baseTplProductFamilyPrvtEntity;

  /**
   * @return Returns the baseTplProductFamilyPrvtEntity.
   */
  public BaseTplProductFamilyPrvtEntity getBaseTplProductFamilyPrvtEntity()
  {
    return m_baseTplProductFamilyPrvtEntity;
  }
  
  /**
   * @param baseTplProductFamilyPrvtEntity_ The baseTplProductFamilyPrvtEntity to set.
   */
  public void setBaseTplProductFamilyPrvtEntity(
                                                BaseTplProductFamilyPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {
    m_baseTplProductFamilyPrvtEntity = baseTplProductFamilyPrvtEntity_;
  }
}