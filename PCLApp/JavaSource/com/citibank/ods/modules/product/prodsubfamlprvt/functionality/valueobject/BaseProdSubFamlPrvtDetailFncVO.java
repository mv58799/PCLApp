/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseProdSubFamlPrvtDetailFncVO extends BaseODSFncVO
{
  public static final String C_PROD_SUB_FAML_CODE_DESCRIPTION = "Código da Sub-Família";
  public static final String C_PROD_SUB_FAML_NAME_DESCRIPTION = "Nome da Sub-Família";
  public static final String C_PROD_SUB_FAML_TEXT_DESCRIPTION = "Desc. da Sub-Família";
  public static final String C_PROD_FAML_CODE_DESCRIPTION = "Codigo da Familia";

  /**
   * A Entity desta transação
   */
  protected BaseTplProdSubFamlPrvtEntity m_baseTplProductSubFamilyPrvtEntity = null;

  /**
   * Domain da família de produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_prodFamlCodeDomain;
  
  /**
   * @return Returns the baseTplProductSubFamilyPrvtEntity.
   */
  public BaseTplProdSubFamlPrvtEntity getBaseTplProductSubFamilyPrvtEntity()
  {
    return m_baseTplProductSubFamilyPrvtEntity;
  }

  /**
   * @param baseTplProductSubFamilyPrvtEntity_ The baseTplProductSubFamilyPrvtEntity to set.
   */
  public void setBaseTplProductSubFamilyPrvtEntity(
                                                   BaseTplProdSubFamlPrvtEntity baseTplProductSubFamilyPrvtEntity_ )
  {
    m_baseTplProductSubFamilyPrvtEntity = baseTplProductSubFamilyPrvtEntity_;
  }
  
  /**
   * @return Returns the prodFamlDomain.
   */
  public DataSet getProdFamlCodeDomain()
  {
    return m_prodFamlCodeDomain;
  }
  /**
   * @param prodFamlDomain_ The prodFamlDomain to set.
   */
  public void setProdFamlCodeDomain( DataSet prodFamlDomain_ )
  {
    m_prodFamlCodeDomain = prodFamlDomain_;
  }
}
