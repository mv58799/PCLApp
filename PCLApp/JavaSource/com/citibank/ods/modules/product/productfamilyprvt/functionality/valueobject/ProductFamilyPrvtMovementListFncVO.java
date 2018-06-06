/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtMovementListFncVO extends
    BaseProductFamilyPrvtListFncVO
{

  /**
   * Constante de descrição do campo do último usuario a atualizar
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usuário";

  /**
   * Codigo do usuario que efetuou a ultima atualizacao no registro.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastUpdUserIdSrc;

  /**
   * @return Returns the lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ The lastUpdUserIdSrc to set.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }
}