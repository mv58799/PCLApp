/*
 * Created on Dec 13, 2006
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdSubFamlPrvtEntityVO;

/**
 * @author User
 * 
 */
public class BaseTplProdSubFamlPrvtEntity extends BaseODSEntity
{
  public static final int C_PROD_SUB_FAML_CODE_SIZE = 6;
  public static final int C_PROD_SUB_FAML_NAME_SIZE = 40;
  public static final int C_PROD_SUB_FAML_TEXT_SIZE = 70;
  public static final int C_PROD_FAML_CODE_SIZE = 6;

  /**
   * Entity VO de Sub Família de Produtos
   */
  protected BaseTplProdSubFamlPrvtEntityVO m_data;

  /**
   * Retorna o entity VO de Sub Família de Produtos
   * 
   * @return
   */
  public BaseTplProdSubFamlPrvtEntityVO getData()
  {
    return m_data;
  }
}