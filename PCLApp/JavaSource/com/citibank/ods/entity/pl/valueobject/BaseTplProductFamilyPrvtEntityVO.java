/*
 * Created on Mar 13, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseTplProductFamilyPrvtEntityVO extends BaseEntityVO
{
  /**
   * Codigo da Familia de Produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodFamlCode;

  /**
   * Nome da Familia de Produtos
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodFamlName;

  /**
   * Descricao da Familia de Produtos.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodFamlText;

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
   * @return Returns the prodFamlName.
   */
  public String getProdFamlName()
  {
    return m_prodFamlName;
  }

  /**
   * @param prodFamlName_ The prodFamlName to set.
   */
  public void setProdFamlName( String prodFamlName_ )
  {
    m_prodFamlName = prodFamlName_;
  }

  /**
   * @return Returns the prodFamlText.
   */
  public String getProdFamlText()
  {
    return m_prodFamlText;
  }

  /**
   * @param prodFamlText_ The prodFamlText to set.
   */
  public void setProdFamlText( String prodFamlText_ )
  {
    m_prodFamlText = prodFamlText_;
  }
}