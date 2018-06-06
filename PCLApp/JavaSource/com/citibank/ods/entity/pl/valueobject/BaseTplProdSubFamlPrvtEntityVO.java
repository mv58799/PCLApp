/*
 * Created on Mar 13, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplProdSubFamlPrvtEntityVO
{
  /**
   * Codigo da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodSubFamlCode;

  /**
   * Nome da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodSubFamlName;
  
  /**
   * Descricao da Sub-Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodSubFamlText;
  
  /**
   * Codigo da Familia de Produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodFamlCode;

  /**
   * Codigo do usuario que efetuou a ultima atualizacao no registro.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastUpdUserId;

  /**
   * Data e Hora que o usuario aprovou o registro cadastrado
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private Date m_lastUpdDate;

  
  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }
  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }
  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }
  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }
  /**
   * @return Returns the prodFamlCode.
   */
  public BigInteger getProdFamlCode()
  {
    return m_prodFamlCode;
  }
  /**
   * @param prodFamlCode_ The prodFamlCode to set.
   */
  public void setProdFamlCode( BigInteger prodFamlCode_ )
  {
    m_prodFamlCode = prodFamlCode_;
  }
  /**
   * @return Returns the prodSubFamlCode.
   */
  public BigInteger getProdSubFamlCode()
  {
    return m_prodSubFamlCode;
  }
  /**
   * @param prodSubFamlCode_ The prodSubFamlCode to set.
   */
  public void setProdSubFamlCode( BigInteger prodSubFamlCode_ )
  {
    m_prodSubFamlCode = prodSubFamlCode_;
  }
  /**
   * @return Returns the prodSubFamlName.
   */
  public String getProdSubFamlName()
  {
    return m_prodSubFamlName;
  }
  /**
   * @param prodSubFamlName_ The prodSubFamlName to set.
   */
  public void setProdSubFamlName( String prodSubFamlName_ )
  {
    m_prodSubFamlName = prodSubFamlName_;
  }
  /**
   * @return Returns the prodSubFamlText.
   */
  public String getProdSubFamlText()
  {
    return m_prodSubFamlText;
  }
  /**
   * @param prodSubFamlText_ The prodSubFamlText to set.
   */
  public void setProdSubFamlText( String prodSubFamlText_ )
  {
    m_prodSubFamlText = prodSubFamlText_;
  }
}