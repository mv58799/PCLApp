package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdQlfyPrvtEntityVO;

/**
 * @author fernando.salgado
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTplProdQlfyPrvtEntity extends BaseODSEntity
{
 
  /**
   * Constante do tamanho do Qualificador
   */
  public static final int C_PROD_QLFY_CODE_SIZE = 4;
  
  /**
   * Constante do tamanho da descrição do Qualificador
   */
  public static final int C_PROD_QLFY_TEXT_SIZE = 40;

	
  /**
   * Entity VO Qualificação de Produto
   */
  protected BaseTplProdQlfyPrvtEntityVO m_data;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTplProdQlfyPrvtEntityVO getData()
  {
    return m_data;
  }
}
