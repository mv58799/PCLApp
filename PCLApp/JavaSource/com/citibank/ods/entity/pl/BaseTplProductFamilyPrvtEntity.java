/*
 * Created on Dec 13, 2006
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductFamilyPrvtEntityVO;

/**
 * @author User
 * 
 */
public class BaseTplProductFamilyPrvtEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do c�digo da fam�lia
   */
  public static final int C_PROD_FAML_CODE_SIZE = 6;
  
  /**
   * Constante do tamanho do nome da fam�lia de produtos
   */
  public static final int C_PROD_FAML_NAME_SIZE  = 40;
  
  /**
   * Constante do tamanho da descricaoda fam�lia de produtos
   */
  public static final int C_PROD_FAML_TEXT_SIZE  = 70;
  
  /**
   * Entity VO de Fam�lia de Produtos
   */
  protected BaseTplProductFamilyPrvtEntityVO m_data;

  /**
   * Retorna o entity VO de Fam�lia de Produtos
   * 
   * @return
   */
  public BaseTplProductFamilyPrvtEntityVO getData()
  {
    return m_data;
  }
}