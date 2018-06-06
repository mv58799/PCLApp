package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplAggrProdPrvtEntityVO;

/**
 * @author User
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplAggrProdPrvtEntity extends BaseODSEntity
{
  /**
   * Constante do nome do elemento Código do Agregador
   */
  public static final int C_PRVT_PROD_AGGR_CODE_SIZE = 5;
  
  /**
   * Constante da descricao do Agregador
   */
  public static final int C_PRVT_PROD_AGGR_TEXT_SIZE = 20;
  

  
  /**
   * Entity VO do agregador de produtos
   */
  protected BaseTplAggrProdPrvtEntityVO m_data;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTplAggrProdPrvtEntityVO getData()
  {
    return m_data;
  }
}