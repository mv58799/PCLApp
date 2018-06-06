/*
 * Created on Dec 13, 2006
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdRiskCatPrvtEntityVO;

/**
 * @author User
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplProdRiskCatPrvtEntity extends BaseODSEntity
{
  /**
   * Constante do nome do elemento Código
   * 
   * 20110321 apos a alteracao do compo de risco PROD_RISK_CAT_CODE -> PROD_INVST_RISK_CODE
   * a constante mudou de 4 para 2
   */
  public static final int C_PROD_INVST_RISK_CODE_SIZE = 2;
  
  /**
   * Constante da descricao da categoria de risco
   */
  public static final int C_PROD_RISK_CAT_TEXT_SIZE = 30;
  
  /**
   * Entity VO da categoria de risco
   */
  protected BaseTplProdRiskCatPrvtEntityVO m_data;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTplProdRiskCatPrvtEntityVO getData()
  {
    return m_data;
  }
}