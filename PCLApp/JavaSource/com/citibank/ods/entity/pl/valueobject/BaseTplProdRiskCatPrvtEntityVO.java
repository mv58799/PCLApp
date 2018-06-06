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
public class BaseTplProdRiskCatPrvtEntityVO
{
  /**
   * Codigo da Categoria de Risco Private
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private BigInteger m_prodRiskCatCode;

  /**
   * Descricao da Categoria de Risco Private
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prodRiskCatText;

  /**
   * Codigo do usuario que efetuou a ultima atualizacao no registro.
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_lastUpdUserID;

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
   * @return Returns the lastUpdUserID.
   */
  public String getLastUpdUserID()
  {
    return m_lastUpdUserID;
  }

  /**
   * @param lastUpdUserID_ The lastUpdUserID to set.
   */
  public void setLastUpdUserID( String lastUpdUserID_ )
  {
    m_lastUpdUserID = lastUpdUserID_;
  }

  /**
   * @return Returns the prodRiskCatCode.
   */
  public BigInteger getProdRiskCatCode()
  {
    return m_prodRiskCatCode;
  }

  /**
   * @param prodRiskCatCode_ The prodRiskCatCode to set.
   */
  public void setProdRiskCatCode( BigInteger prodRiskCatCode_ )
  {
    m_prodRiskCatCode = prodRiskCatCode_;
  }

  /**
   * @return Returns the prodRiskCatText.
   */
  public String getProdRiskCatText()
  {
    return m_prodRiskCatText;
  }

  /**
   * @param prodRiskCatText_ The prodRiskCatText to set.
   */
  public void setProdRiskCatText( String prodRiskCatText_ )
  {
    m_prodRiskCatText = prodRiskCatText_;
  }
}